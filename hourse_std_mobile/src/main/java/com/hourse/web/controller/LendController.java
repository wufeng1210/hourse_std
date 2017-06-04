package com.hourse.web.controller;

import com.hourse.web.http.HttpPostHandle;
import com.hourse.web.model.Hourse;
import com.hourse.web.model.ImageInfo;
import com.hourse.web.service.IHourseService;
import com.hourse.web.util.common.Constant;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Controller
public class LendController {

    private static Logger logger = LoggerFactory.getLogger(LendController.class);
    @Autowired
    private IHourseService iHourseService;

    @RequestMapping("lend")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("lend");
        return modelAndView;
    }

    @RequestMapping("homeLend")
    public ModelAndView homeLend(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("home-lend");
        return modelAndView;
    }

    @RequestMapping("addHourse")
    @ResponseBody
    public Map<String, Object> addHourseInfo(Hourse hourse, ImageInfo imageInfo,HttpServletRequest request){
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            String address = request.getParameter("address");
            String cidAddress = request.getParameter("cidAddr");
            String[] addressArr = address.split(",");
            hourse.setProvince(addressArr[0]);
            hourse.setCity(addressArr[1]);
            if(addressArr.length == 3){
                hourse.setArea(addressArr[2].trim());
            }else{
                hourse.setArea("");
            }
            address = hourse.getProvince() + hourse.getCity() + hourse.getArea() + cidAddress;
            hourse.setHourseAddr(address);
            HashMap<String, Object> p = new HashMap<String, Object>();
            p.put("address", address.trim());
            p.put("city", hourse.getCity());
            p.put("pois", "0");
            String jsonStr = HttpPostHandle.httpGetAddress(p);
            logger.info(jsonStr);
            JSONObject cityJson = JSONObject.fromObject(jsonStr);
            if (cityJson != null && 0 == cityJson.getInt("status")) {
                double lng = cityJson.optJSONObject("result").getJSONObject("location").getDouble("lng");
                double lat = cityJson.optJSONObject("result").getJSONObject("location").getDouble("lat");
                hourse.setLongitude(new   BigDecimal(lng).setScale(7,   BigDecimal.ROUND_HALF_UP).doubleValue());
                hourse.setLatitude(new   BigDecimal(lat).setScale(7,   BigDecimal.ROUND_HALF_UP).doubleValue());
            }else{
                resMap.put(Constant.ERROR_NO, "-1");
                resMap.put(Constant.ERROR_INFO, "地址解析失败");
                return resMap;
            }
            int hourseId = iHourseService.insert(hourse);
            resMap.put("hourseId", hourseId);
        }catch (Exception e){
            logger.error("添加房屋信息失败：", e);
            e.printStackTrace();
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "程序异常");
        }
        return resMap;
    }
}
