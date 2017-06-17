package com.hourse.web.controller;

import com.hourse.web.http.HttpPostHandle;
import com.hourse.web.model.Hourse;
import com.hourse.web.model.User;
import com.hourse.web.service.IHourseService;
import com.hourse.web.util.CookieUtil;
import com.hourse.web.util.common.Constant;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 我要租房
@Controller
public class RentController {

    private static Logger logger = LoggerFactory.getLogger(RentController.class);
    @Autowired
    private IHourseService hourseService;

    @RequestMapping("rent")
    public ModelAndView rent() {
        ModelAndView modelAndView = new ModelAndView("rent");
        return modelAndView;
    }

    @RequestMapping("collect")
    public ModelAndView collect() {
        ModelAndView modelAndView = new ModelAndView("collect");
        return modelAndView;
    }

    @RequestMapping("homeRent")
    public ModelAndView homeRent() {
        ModelAndView modelAndView = new ModelAndView("home-rent");
        return modelAndView;
    }

    @RequestMapping("rentDetail")
    public ModelAndView rentDetail() {
        ModelAndView modelAndView = new ModelAndView("rent-detail");
        return modelAndView;
    }

    /**
     * 获取当前位置的可租房源
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("getHourseInfo")
    public Map<String, Object> getHourseInfo(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Hourse hourse = new Hourse();
        User user = CookieUtil.getUserInfo(request);
        if(user.getUserId() == null){
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "用户未登录");
            return resMap;
        }
        hourse.setUserId(user.getUserId());
        String lon = request.getParameter("lon");
        String lat = request.getParameter("lat");
//        if(lon == null || lat == null){
//            resMap.put(Constant.ERROR_NO, "-1");
//            resMap.put(Constant.ERROR_INFO, "获取经纬度失败");
//            return resMap;
//        }
//        String position = lon + "," + lat;
        String city = "";
        String province = "";
        String position = "30.205181,120.210487";
        try {
            HashMap<String, Object> p = new HashMap<String, Object>();
            p.put("location", position);
            p.put("pois", "0");
            String jsonStr = HttpPostHandle.httpGetAddress(p);
            logger.info(jsonStr);
            JSONObject cityJson = JSONObject.fromObject(jsonStr);
            if (cityJson != null && 0 == cityJson.getInt("status")) {
                province = cityJson.optJSONObject("result").getJSONObject("addressComponent").getString("province");
                city = cityJson.optJSONObject("result").getJSONObject("addressComponent").getString("city");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "程序异常");
        }
        hourse.setProvince(province);
        hourse.setCity(city);
        hourse.setLongitude(30.205181);
        hourse.setLatitude(120.210487);
        List<Hourse> hourses = hourseService.getHourseInfo(hourse);
        resMap.put("hourseList", hourses);
        resMap.put(Constant.ERROR_NO, "0");
        return resMap;
    }

    /**
     * 获取房源详情
     * @param hourse
     * @return
     */
    @ResponseBody
    @RequestMapping("getHourseDetail")
    public Map<String, Object> getHourseDetail(Hourse hourse, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        User user = CookieUtil.getUserInfo(request);
        if(user.getUserId() == null){
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "用户未登录");
            return resMap;
        }
        hourse.setUserId(user.getUserId());
        try {
            List<Hourse> hourses = hourseService.getHourseDetail(hourse);
            resMap.put("hourseList", hourses);
            resMap.put(Constant.ERROR_NO, "0");
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "程序异常");
        }
        return resMap;
    }

    /**
     * 获取已收藏房源
     * @param hourse
     * @return
     */
    @ResponseBody
    @RequestMapping("getCollectHourse")
    public Map<String, Object> getCollectHourse(Hourse hourse, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        User user = CookieUtil.getUserInfo(request);
        if(user.getUserId() == null){
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "用户未登录");
            return resMap;
        }
        hourse.setUserId(user.getUserId());
        try {
            List<Hourse> hourses = hourseService.getCollectHourse(hourse);
            resMap.put("hourseList", hourses);
            resMap.put(Constant.ERROR_NO, "0");
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "程序异常");
        }
        return resMap;
    }


}
