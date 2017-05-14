package com.hourse.web.controller;

import com.hourse.web.model.Hourse;
import com.hourse.web.service.IHourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
//        modelAndView.addObject("securityName","1233");
        return modelAndView;
    }

    @RequestMapping("homeRent")
    public ModelAndView homeRent() {
        ModelAndView modelAndView = new ModelAndView("home-rent");
//        modelAndView.addObject("securityName","1233");
        return modelAndView;
    }

    @RequestMapping("rentDetail")
    public ModelAndView rentDetail() {
        ModelAndView modelAndView = new ModelAndView("rent-detail");
//        modelAndView.addObject("securityName","1233");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("getRent")
    public Map<String, Object> getHourseInfo() {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Hourse hourse = new Hourse();
        hourse.setProvince("杭州市");
        List<Hourse> hourses = hourseService.getHourseInfo(hourse);
        resMap.put("hourse", hourses);
        return resMap;
    }
}
