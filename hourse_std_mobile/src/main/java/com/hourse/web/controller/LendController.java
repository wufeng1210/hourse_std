package com.hourse.web.controller;

import com.hourse.web.model.Hourse;
import com.hourse.web.model.ImageInfo;
import com.hourse.web.service.IHourseService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class LendController {

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
    public Map<String, Object> addHourseInfo(Hourse hourse, ImageInfo imageInfo){
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            int hourseId = iHourseService.insert(hourse);
            resMap.put("hourseId", hourseId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resMap;
    }
}
