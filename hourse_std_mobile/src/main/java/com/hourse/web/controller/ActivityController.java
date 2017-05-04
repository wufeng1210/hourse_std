package com.hourse.web.controller;

import com.hourse.web.service.IActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2017/4/26.
 */
@Controller
public class ActivityController {

    private static Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private IActivityService activityService;

    @RequestMapping(value = "getActivity")
    @ResponseBody
    public Map<String, String> getActivity(){
        Map<String, String> resMap = new HashMap<String, String>();

        return resMap;
    }
}
