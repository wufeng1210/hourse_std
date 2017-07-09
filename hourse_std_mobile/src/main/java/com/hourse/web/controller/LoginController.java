package com.hourse.web.controller;

import com.hourse.web.model.User;
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
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wufeng on 2017/4/10.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService iUserService;


    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");
        String code = request.getParameter("code");
        modelAndView.addObject("code", code);
        logger.info(code);
        request.setAttribute("securityName","12212");
        return modelAndView;
    }

    /**
     * 登录并跳转到主页
     * @param user
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public Map<String, Object> login(User user, HttpServletResponse response){
        Map<String, Object> resMap = new HashMap<String, Object>();

        List<User> userList =iUserService.getUserByUserName(user);
        if(userList.isEmpty()){
            user.setSecretKey("1");
            user.setUserType("1");
            int userNo = iUserService.interUserInfo(user);
            if(userNo == 0){
                resMap.put(Constant.ERROR_NO, -1);
                resMap.put(Constant.ERROR_INFO, "添加用户失败");
                return resMap;
            }
        }
        List<User> userInfoList =iUserService.getUser(user);
        if(!userInfoList.isEmpty()){
            CookieUtil.setObjectCookie(response, userInfoList.get(0), "hoursestd", -1, PropertiesUtils.get("domain"));
            resMap.put(Constant.ERROR_NO, "0");
        }else{
            resMap.put(Constant.ERROR_NO, -1);
            resMap.put(Constant.ERROR_INFO, "用户名或密码不正确");
            return resMap;
        }
        return resMap;
    }

//    private User creatUser (List<User> userList){
//        User user = new User();
//        user.setUserId(userList[]);
//        return user;
//    }
    @RequestMapping(value = "goLogin")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "home")
    public String home() {
        return "home";
    }
}
