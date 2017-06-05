package com.hourse.web.service.impl;

import com.hourse.web.mapper.HourseMapper;
import com.hourse.web.mapper.ImageInfoMapper;
import com.hourse.web.model.Hourse;
import com.hourse.web.model.ImageInfo;
import com.hourse.web.service.IHourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wufeng on 2017/4/11.
 */
@Service
@Transactional
public class HourseServiceImpl implements IHourseService {

    @Resource
    private HourseMapper hourseMapper;
    @Resource
    private ImageInfoMapper imageInfoMapper;

    /**
     * 根据用户经纬度获取房屋信息列表，再根据距离和房屋地址排序
     * @param hourse
     * @return
     */
    public List<Hourse> getHourseInfo(Hourse hourse) {
        List<Hourse> hoursesList = hourseMapper.queryHourseByParam(hourse);
        for(int i=0; i<hoursesList.size(); i++){
            Hourse hourseInfo = hoursesList.get(i);
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setHourseId(hourseInfo.getHourseId());
            List<ImageInfo> imageInfoList = imageInfoMapper.getImageInfo(imageInfo);
            hourseInfo.setImageInfoList(imageInfoList);
        }
        return hoursesList;
    }

    /**
     * 根据用户经纬度计算距离，统计相同距离的房屋数量
     * @param hourse
     * @return
     */

    public List<Hourse> getMapInfo(Hourse hourse) {
        List<Hourse> hoursesList = hourseMapper.queryHourseByParamForMap(hourse);
        return hoursesList;
    }

    public List<Hourse> getRecommendHourseInfo(Hourse hourse) {
        List<Hourse> hoursesList = hourseMapper.queryRecommendHourseByParamForMap(hourse);
        for(int i=0; i<hoursesList.size(); i++){
            Hourse hourseInfo = hoursesList.get(i);
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setHourseId(hourseInfo.getHourseId());
            List<ImageInfo> imageInfoList = imageInfoMapper.getImageInfo(imageInfo);
            hourseInfo.setImageInfoList(imageInfoList);
        }
        return hoursesList;
    }

    public List<Hourse> queryHourseByisRentAndState(Hourse hourse) {
        List<Hourse> hoursesList = hourseMapper.queryHourseByisRentAndState(hourse);
        for(int i=0; i<hoursesList.size(); i++){
            Hourse hourseInfo = hoursesList.get(i);
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setHourseId(hourseInfo.getHourseId());
            List<ImageInfo> imageInfoList = imageInfoMapper.getImageInfo(imageInfo);
            hourseInfo.setImageInfoList(imageInfoList);
        }
        return hoursesList;
    }

    /**
     * 增加房屋信息，返回房屋id
     * @param hourse
     * @return
     */
    public int insert(Hourse hourse){
       return hourseMapper.insert(hourse);
    }
}
