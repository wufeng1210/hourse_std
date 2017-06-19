package com.hourse.web.controller;

import com.hourse.web.model.Hourse;
import com.hourse.web.model.User;
import com.hourse.web.service.IHourseService;
import com.hourse.web.service.IUserService;
import com.hourse.web.util.CookieUtil;
import com.hourse.web.util.PropertiesUtils;
import com.hourse.web.util.common.Constant;
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

    @RequestMapping("notice")
    public ModelAndView notice() {
        ModelAndView modelAndView = new ModelAndView("notice");
        return modelAndView;
    }

    @RequestMapping("personInfo")
    public ModelAndView personInfo() {
        ModelAndView modelAndView = new ModelAndView("personal-data");
        return modelAndView;
    }

    @RequestMapping("noticeTip")
    public ModelAndView noticeTip() {
        ModelAndView modelAndView = new ModelAndView("notice-tip");
        modelAndView.addObject("notice_tip", PropertiesUtils.get("notice.tip", ""));
        return modelAndView;
    }

    @RequestMapping("modifyInfo")
    public ModelAndView modifyInfo() {
        ModelAndView modelAndView = new ModelAndView("modify-info");
        return modelAndView;
    }

    /**
     * 查询用户出租房源
     */
    @RequestMapping(value = "queryHourseByisRentAndState")
    @ResponseBody
    public Map<String, Object> queryHourseByisRentAndState(Hourse hourse, HttpServletRequest request){
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            User user = CookieUtil.getUserInfo(request);
            if(user.getUserId() == null){
                resMap.put(Constant.ERROR_NO, "-1");
                resMap.put(Constant.ERROR_INFO, "用户未登录");
                return resMap;
            }
            hourse.setUserId(user.getUserId());
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

    /**
     * 获取用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "getUserInfo")
    @ResponseBody
    public Map<String, Object> getUserInfo(User user, HttpServletRequest request){
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            user = CookieUtil.getUserInfo(request);
            if(user.getUserId() == null){
                resMap.put(Constant.ERROR_NO, "-1");
                resMap.put(Constant.ERROR_INFO, "用户未登录");
                return resMap;
            }
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

    /**
     * 修改房屋信息
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("updateUserInfo")
    public Map<String, Object> updateHourse( HttpServletRequest request, User user) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        User user_info = CookieUtil.getUserInfo(request);
        if(user_info.getUserId() == null){
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "用户未登录");
            return resMap;
        }
        user.setUserId(user_info.getUserId());
        try {
            int userInfo = iUserService.updateUserInfo(user);
            if(userInfo == 1){
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
