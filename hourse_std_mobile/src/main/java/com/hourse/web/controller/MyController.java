package com.hourse.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {


    @RequestMapping("my")
    public ModelAndView my() {
        ModelAndView modelAndView = new ModelAndView("my");
//        modelAndView.addObject("securityName","1233");
        return modelAndView;
    }
}
