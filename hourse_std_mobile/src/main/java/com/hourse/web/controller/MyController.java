package com.hourse.web.controller;

import com.hourse.web.model.Hourse;
import com.hourse.web.model.User;
import com.hourse.web.service.IHourseService;
import com.hourse.web.service.IUserService;
import com.hourse.web.util.common.Constant;
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

@Controller
public class MyController {

    private static Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private IHourseService hourseService;
    @Autowired
    private IUserService iUserService;

    @RequestMapping("my")
    public ModelAndView my() {
        ModelAndView modelAndView = new ModelAndView("my");
        return modelAndView;
    }

    @RequestMapping("myRent")
    public ModelAndView myRent() {
        ModelAndView modelAndView = new ModelAndView("my-rent");
        return modelAndView;
    }

    @RequestMapping("personInfo")
    public ModelAndView personInfo() {
        ModelAndView modelAndView = new ModelAndView("personal-data");
        return modelAndView;
    }

    @RequestMapping(value = "queryHourseByisRentAndState")
    @ResponseBody
    public Map<String, Object> queryHourseByisRentAndState(Hourse hourse){
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            List<Hourse> hourseList = hourseService.queryHourseByisRentAndState(hourse);
            resMap.put("hourseList", hourseList);
            resMap.put(Constant.ERROR_NO, "0");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取已出租房屋信息失败", e);
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "程序异常");
        }
        return resMap;
    }

    @RequestMapping(value = "getUserInfo")
    @ResponseBody
    public Map<String, Object> getUserInfo(User user){
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            List<User> userList = iUserService.getUserById(user);
            resMap.put("userList", userList);
            resMap.put(Constant.ERROR_NO, "0");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取用户信息失败", e);
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "程序异常");
        }

        return resMap;
    }
}
