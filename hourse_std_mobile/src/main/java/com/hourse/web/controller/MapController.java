package com.hourse.web.controller;

import com.hourse.web.http.HttpPostHandle;
import com.hourse.web.model.Hourse;
import com.hourse.web.service.IHourseService;
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

/**
 * 获取数据显示在高德地图中
 */
@Controller
public class MapController {

    private static Logger logger = LoggerFactory.getLogger(RentController.class);
    @Autowired
    private IHourseService hourseService;
    @RequestMapping("map")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("map");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("getMapInfo")
    public Map<String, Object> getHourseInfo(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Hourse hourse = new Hourse();
        String lon = request.getParameter("lon");
        String lat = request.getParameter("lat");
//        String position = lon + "," + lat;
        lon = "120.310423";
        lat = "30.305123";
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
            return resMap;
        }
        hourse.setProvince(province);
        hourse.setCity(city);
        hourse.setLongitude(Double.parseDouble(lon));
        hourse.setLatitude(Double.parseDouble(lat));
        List<Hourse> hourses = hourseService.getMapInfo(hourse);
        resMap.put("hourseList", hourses);
        resMap.put(Constant.ERROR_NO, "0");
        return resMap;
    }
}
