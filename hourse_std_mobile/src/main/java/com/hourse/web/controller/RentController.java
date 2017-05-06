package com.hourse.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RentController {

    private static Logger logger = LoggerFactory.getLogger(RentController.class);

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
}
