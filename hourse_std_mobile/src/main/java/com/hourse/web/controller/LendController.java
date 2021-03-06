package com.hourse.web.controller;

import com.hourse.web.http.HttpPostHandle;
import com.hourse.web.model.Hourse;
import com.hourse.web.model.ImageInfo;
import com.hourse.web.model.User;
import com.hourse.web.service.IHourseService;
import com.hourse.web.service.IImageInfoService;
import com.hourse.web.util.CookieUtil;
import com.hourse.web.util.common.Constant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


@Controller
public class LendController {

    private static Logger logger = LoggerFactory.getLogger(LendController.class);
    @Autowired
    private IHourseService iHourseService;
    @Autowired
    private IImageInfoService iImageInfoService;

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

    /**
     * 添加房屋信息
     * @param hourse
     * @param imageInfo
     * @param request
     * @return
     */
    @RequestMapping("addHourse")
    @ResponseBody
    public Map<String, Object> addHourseInfo(Hourse hourse, ImageInfo imageInfo,HttpServletRequest request){
        Map<String, Object> resMap = new HashMap<String, Object>();
        User user = CookieUtil.getUserInfo(request);
        if(user.getUserId() == null){
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "用户未登录");
            return resMap;
        }
        try {
            String address = request.getParameter("address");
            String cidAddress = request.getParameter("cidAddr");
            String imageBases = request.getParameter("imageBases");
            if(StringUtils.isEmpty(imageBases) || imageBases.split(",").length == 0){
                    resMap.put(Constant.ERROR_NO, "-1");
                    resMap.put(Constant.ERROR_INFO, "图片不能为空，请上传图片");
                    return resMap;
            }
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
            hourse.setUserId(user.getUserId());
            HashMap<String, Object> p = new HashMap<String, Object>();
            p.put("address", address.trim());
            String jsonStr = HttpPostHandle.httpGetDirectionOfGaode(p);
            logger.info(jsonStr);
            JSONObject cityJson = JSONObject.fromObject(jsonStr);
            if (cityJson != null && 1 == cityJson.getInt("status")) {
                JSONArray jsonArray = cityJson.optJSONArray("geocodes");
                String[] location = jsonArray.getJSONObject(0).getString("location").split(",");
                hourse.setLongitude(String.valueOf(location[0]));
                hourse.setLatitude(String.valueOf(location[1]));
            }else{
                resMap.put(Constant.ERROR_NO, "-1");
                resMap.put(Constant.ERROR_INFO, "地址解析失败");
                return resMap;
            }
            iHourseService.insert(hourse);
            imageInfo.setHourseId(hourse.getHourseId());
            iImageInfoService.insertImageInfo(imageBases, imageInfo);
            resMap.put(Constant.ERROR_NO, "0");
            resMap.put("hourseId", hourse.getHourseId());
        }catch (Exception e){
            logger.error("添加房屋信息失败：", e);
            e.printStackTrace();
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "程序异常");
        }
        return resMap;
    }

    @ResponseBody
    @RequestMapping("upload")
    public Map<String, Object> uploadImage(HttpServletRequest request, @RequestParam MultipartFile myFiles){
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            logger.info(myFiles.getOriginalFilename());
            logger.info("上传成功");
        }catch (Exception e){
            e.printStackTrace();
        }

        return resMap;
    }

    /**
     * 修改房屋信息
     * @param hourse
     * @return
     */
    @ResponseBody
    @RequestMapping("updateHourse")
    public Map<String, Object> updateHourse(Hourse hourse, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        User user = CookieUtil.getUserInfo(request);
        if(user.getUserId() == null){
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "用户未登录");
            return resMap;
        }
        hourse.setUserId(user.getUserId());
        try {
            int hourses = iHourseService.update(hourse);
            if(hourses == 1){
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
