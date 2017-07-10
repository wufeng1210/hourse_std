package com.hourse.web.util;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by dell on 2017/4/20.
 */
public class WeChatHttpPostUtil {

    private static Logger logger = LoggerFactory.getLogger(WeChatHttpPostUtil.class);
    /**
     *
     */

    public static String getAuthId(){
        String authId = "";
        String url = PropertiesUtils.get("open.wechat.url", "https://open.weixin.qq.com");
        if(!url.endsWith("/")){
            url = url + "/";
        }
        String appId = PropertiesUtils.get("wechat.appId", "wx30914ca2b7ff409a");
        url = url + "connect/oauth2/authorize?appid="+appId+"&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect&redirect_uri="+ URLEncoder.encode("http://www.jygozuba.com/");
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.fromObject(message);
            System.out.println("JSON字符串："+demoJson);
            authId = demoJson.getString("access_token");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authId;
    }

    /**
     * 获取OpenId
     * https://mp.weixin.qq.com/wiki/15/54ce45d8d30b6bf6758f68d2e95bc627.html
     * @return
     */
    public static JSONObject getOpenId(String refresh_token){
        String value = "";
        JSONObject demoJson = new JSONObject();
        String url = PropertiesUtils.get("wechat.url", "https://api.weixin.qq.com");
        if(!url.endsWith("/")){
            url = url + "/";
        }
        String appId = PropertiesUtils.get("wechat.appId", "wx30914ca2b7ff409a");
        String secret = PropertiesUtils.get("wechat.appsecret", "70b086f6135427cea308a392c66600e7"); ////第三方用户唯一凭证密钥，即appsecret

        url = url + "sns/oauth2/refresh_token?appid="+appId+"&grant_type=refresh_token&refresh_token="+refresh_token;
        logger.info("url get openId>>"+url);
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            demoJson = JSONObject.fromObject(message);
            System.out.println("JSON字符串："+demoJson);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return demoJson;
    }
    //UnionID
    /**
     * 获取UnionID
     * https://mp.weixin.qq.com/wiki/15/54ce45d8d30b6bf6758f68d2e95bc627.html
     * @return
     */
    public static JSONObject getUserInfo(String access_token, String openid){
        JSONObject demoJson = new JSONObject();
        String value = "";
        String url = PropertiesUtils.get("wechat.url", "https://api.weixin.qq.com");
        if(!url.endsWith("/")){
            url = url + "/";
        }
        url = url + "sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            demoJson = JSONObject.fromObject(message);
            System.out.println("JSON字符串："+demoJson);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return demoJson;
    }

    /**
     * 获取accesstoken
     * https://mp.weixin.qq.com/wiki/15/54ce45d8d30b6bf6758f68d2e95bc627.html
     * @return
     */
    public static String getAccessToken(){
        String access_token = "";
        String url = PropertiesUtils.get("wechat.url", "https://api.weixin.qq.com");
        if(!url.endsWith("/")){
            url = url + "/";
        }
        String grant_type = "client_credential";
        String appId = PropertiesUtils.get("wechat.appId", "wx30914ca2b7ff409a");
        String secret = PropertiesUtils.get("wechat.appsecret", "70b086f6135427cea308a392c66600e7"); ////第三方用户唯一凭证密钥，即appsecret
        url = url + "cgi-bin/token?grant_type="+grant_type+"&appid="+appId+"&secret="+secret;
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.fromObject(message);
            logger.info("JSON字符串："+demoJson);
            access_token = demoJson.getString("access_token");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return access_token;
    }
    /**
     * 获取accesstoken
     * https://mp.weixin.qq.com/wiki/15/54ce45d8d30b6bf6758f68d2e95bc627.html
     * @return
     */
    public static JSONObject getAuthAccessToken(String code){
        String access_token = "";
        String url = PropertiesUtils.get("wechat.url", "https://api.weixin.qq.com");
        if(!url.endsWith("/")){
            url = url + "/";
        }
        JSONObject demoJson = new JSONObject();
        String grant_type = "client_credential";
        String appId = PropertiesUtils.get("wechat.appId", "wx30914ca2b7ff409a");
        String secret = PropertiesUtils.get("wechat.appsecret", "70b086f6135427cea308a392c66600e7"); ////第三方用户唯一凭证密钥，即appsecret
        url = url + "sns/oauth2/access_token?appid="+appId+"&secret="+secret+"&code="+code+"&grant_type=authorization_code ";
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            demoJson = JSONObject.fromObject(message);
            logger.info("JSON字符串："+demoJson);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return demoJson;
    }


    /**
     * 用拿到的access_token 采用http GET方式请求获得jsapi_ticket
     * （有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket）：
     * https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
     * @param access_token
     * @return
     */
    public static String getTicket(String access_token) {
        String ticket = null;
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ access_token +"&type=jsapi";//这个url链接和参数不能变
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.fromObject(message);
            logger.info("JSON字符串："+demoJson);
            ticket = demoJson.getString("ticket");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    /**
     * 签名生成规则如下：参与签名的字段包括noncestr（随机字符串）,
     * 有效的jsapi_ticket, timestamp（时间戳）, url（当前网页的URL，不包含#及其后面部分） 。
     * 对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）后，
     * 使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1。
     * 这里需要注意的是所有参数名均为小写字符。对string1作sha1加密，字段名和字段值都采用原始值，不进行URL 转义
     * @param decript
     * @return
     */
    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param content
     * @return
     */
    public static String byteToStr(String content) {
        String strDigest = "";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对拼接后的字符串进行 sha1 加密
            byte[] byteArray = md.digest(content.toString().getBytes());

            for (int i = 0; i < byteArray.length; i++) {
                strDigest += byteToHexStr(byteArray[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    public static void main(String[] args){
        //1、获取access_token
        String access_token = getAccessToken();
        // 获取ticket
        String jsapi_ticket = getTicket(access_token);
        //3、时间戳和随机字符串
        String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
        //5、将参数排序并拼接字符串
        String url = PropertiesUtils.get("oos.webaddr", "");
        String str = "jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;
        //6、将字符串进行sha1加密
        String signature =SHA1(str);
        signature = getAuthId();
        System.out.println("参数："+str+"\n签名："+signature);
    }
}
