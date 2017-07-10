package com.hourse.web.controller;

import com.hourse.web.util.PropertiesUtils;
import com.hourse.web.util.RedisClientUtil;
import com.hourse.web.util.WeChatHttpPostUtil;
import com.hourse.web.util.common.Constant;
import freemarker.template.utility.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class WeChatController {

    private Logger logger = LoggerFactory.getLogger(WeChatController.class);

    /**
     * 获取随机串，签名，appid信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getSignature")
    public Map<String, Object> getSignature(HttpServletRequest request){
        String url = URLDecoder.decode(request.getParameter("url"));
        String code = request.getParameter("code");
        logger.info("url>>>"+url);
        Map<String, Object> resMap = new HashMap<String, Object>() ;
        try {
            // 1、获取授权access_token
            String authAccessToken = RedisClientUtil.get("authAccessToken");
            String refresh_token = RedisClientUtil.get("refresh_token");
            logger.info("authAccessToken>>>"+authAccessToken);
            logger.info("refresh_token>>>"+refresh_token);
//            if(StringUtils.isEmpty(authAccessToken) || StringUtils.isEmpty(refresh_token)){
                JSONObject jsonObject= WeChatHttpPostUtil.getAuthAccessToken(code);
                authAccessToken = jsonObject.getString("access_token");
                refresh_token = jsonObject.getString("refresh_token");
                RedisClientUtil.set("authAccessToken", authAccessToken, 2*60*60);
                RedisClientUtil.set("refresh_token", refresh_token, 30*24*60*60);
//            }

            logger.info("code>>>>"+code);
            String redisCode = RedisClientUtil.get("code");
            logger.info("redisCode>>>>"+redisCode);
            jsonObject = WeChatHttpPostUtil.getOpenId(refresh_token);
            String openId = jsonObject.getString("openid");
            resMap.put("openId", openId);
            JSONObject userInfoJsonObject= WeChatHttpPostUtil.getUserInfo(authAccessToken, openId);
            String nickname = userInfoJsonObject.getString("nickname");
            resMap.put("nickName", nickname);
            // 1、获取普通access_token
            String accessToken = RedisClientUtil.get("accessToken");
            logger.info("accessToken>>>"+accessToken);
            if(StringUtils.isEmpty(accessToken)){
                accessToken = WeChatHttpPostUtil.getAccessToken();
                RedisClientUtil.set("accessToken", accessToken, 2*60*60);
            }
            //GET https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
            // 2、根据access_token获取jsapi_ticket
            String jsapiTicket =  RedisClientUtil.get("jsapiTicket");
            if(StringUtils.isEmpty(jsapiTicket)){
                jsapiTicket = WeChatHttpPostUtil.getTicket(accessToken);
                RedisClientUtil.set("jsapiTicket", jsapiTicket, 2*60*60);
            }
            //3、时间戳和随机字符串
            String nonceStr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
            //5、将参数排序并拼接字符串
//        String url = PropertiesUtils.get("oos.webaddr", "");
            logger.info("jsapiTicket>>>"+jsapiTicket);
            logger.info("nonceStr>>>>"+nonceStr);
            logger.info("timestamp>>>>"+timestamp);
            logger.info("url>>>>>"+url);
            String[] paramArr = new String[] { "jsapi_ticket=" + jsapiTicket,
                    "timestamp=" + timestamp, "noncestr=" + nonceStr, "url=" + url };
            Arrays.sort(paramArr);
            // 将排序后的结果拼接成一个字符串
            String content = paramArr[0].concat("&"+paramArr[1]).concat("&"+paramArr[2])
                    .concat("&"+paramArr[3]);
//        String str = "jsapi_ticket="+jsapiTicket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url="+url;
            //6、将字符串进行sha1加密
            String signature = WeChatHttpPostUtil.byteToStr(content);
            resMap.put("appId", PropertiesUtils.get("wechat.appId", ""));
            resMap.put("timestamp", timestamp);
            resMap.put("nonceStr", nonceStr);
            resMap.put("signature",signature);
            resMap.put(Constant.ERROR_NO, 0);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return resMap;
    }
}
