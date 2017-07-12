package com.hourse.web.controller;

import com.hourse.web.model.CollectInfo;
import com.hourse.web.model.User;
import com.hourse.web.service.ICollectInfoService;
import com.hourse.web.util.CookieUtil;
import com.hourse.web.util.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wufeng on 2017/7/12.
 */
@Controller
public class CollectController {

    @Autowired
    private ICollectInfoService iCollectInfoService;
    /**
     * 修改房屋信息
     * @param collectInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("updateCollect")
    public Map<String, Object> updateCollect(CollectInfo collectInfo, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        User user = CookieUtil.getUserInfo(request);
        if(user.getUserId() == null){
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "用户未登录");
            return resMap;
        }
        collectInfo.setUserId(user.getUserId());
        try {
            int hourses = iCollectInfoService.update(collectInfo);
            if(hourses == 1){
                resMap.put(Constant.ERROR_NO, "0");
            } else {
                resMap.put(Constant.ERROR_NO, "0");
                resMap.put(Constant.ERROR_INFO, "修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "程序异常");
        }
        return resMap;
    }
}
