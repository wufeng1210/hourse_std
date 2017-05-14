package com.hourse.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LendController {

    @RequestMapping("lend")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("lend");
//        modelAndView.addObject("securityName","1233");
        return modelAndView;
    }

    @RequestMapping("homeLend")
    public ModelAndView homeLend(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("home-lend");
//        modelAndView.addObject("securityName","1233");
        return modelAndView;
    }
}
