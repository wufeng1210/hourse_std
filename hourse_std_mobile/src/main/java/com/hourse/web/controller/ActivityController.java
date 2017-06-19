package com.hourse.web.controller;

import com.hourse.web.http.HttpPostHandle;
import com.hourse.web.model.ActivityInfo;
import com.hourse.web.model.Hourse;
import com.hourse.web.model.User;
import com.hourse.web.service.IActivityService;
import com.hourse.web.service.IHourseService;
import com.hourse.web.util.CookieUtil;
import com.hourse.web.util.TriDES;
import com.hourse.web.util.common.Constant;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/4/26.
 */
@Controller
public class ActivityController {

    private static Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private IActivityService activityService;
    @Autowired
    private IHourseService hourseService;

    /**
     * 获取活动和推荐的房屋信息
     * @param activityInfo
     * @return
     */
    @RequestMapping(value = "getActivity")
    @ResponseBody
    public Map<String, Object> getActivity(ActivityInfo activityInfo, HttpServletRequest request){
        Map<String, Object> resMap = new HashMap<String, Object>();

        try {
            activityInfo.setStatus("1");
            List<ActivityInfo> activity = activityService.getActivityInfoByState(activityInfo);
            resMap.put("activity", activity);
            Hourse hourse = new Hourse();
            String lon = request.getParameter("lon");
            String lat = request.getParameter("lat");
//        String position = lon + "," + lat;
            lon = "120.310423";
            lat = "30.305123";
            String city = "";
            String province = "";
            String position = "120.210487,30.205181";
            try {
                HashMap<String, Object> p = new HashMap<String, Object>();
                p.put("location", position);
                p.put("pois", "0");
                String jsonStr = HttpPostHandle.httpGetAddress(p);
                logger.info(jsonStr);
                JSONObject cityJson = JSONObject.fromObject(jsonStr);
                if (cityJson != null && 1 == cityJson.getInt("status")) {
                    JSONObject jsonObject = cityJson.optJSONObject("regeocode").optJSONObject("addressComponent");
                    province = jsonObject.getString("province");
                    city = jsonObject.getString("city");
//                String district = jsonObject.getString("district");
                }
            } catch (Exception e) {
                e.printStackTrace();
                resMap.put(Constant.ERROR_NO, "-1");
                resMap.put(Constant.ERROR_INFO, "程序异常");
                return resMap;
            }
            hourse.setProvince(province);
            hourse.setCity(city);
            hourse.setLongitude(lon);
            hourse.setLatitude(lat);
            List<Hourse> hourses = hourseService.getRecommendHourseInfo(hourse);
            resMap.put("hourseList", hourses);
            resMap.put(Constant.ERROR_NO, "0");
        }catch (Exception e){
            e.printStackTrace();
            resMap.put(Constant.ERROR_NO, "-1");
            resMap.put(Constant.ERROR_INFO, "程序异常");
        }
        return resMap;
    }


}
