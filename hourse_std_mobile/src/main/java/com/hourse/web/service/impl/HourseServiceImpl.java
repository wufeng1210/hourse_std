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

    public List<Hourse> getHourseInfo(Hourse hourse) {
        List<Hourse> hoursesList = hourseMapper.queryHourseByParam(hourse);
        for(int i=0; i<hoursesList.size(); i++){
            Hourse hourseInfo = hoursesList.get(i);
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setHourseId(hourseInfo.getHourseId());
            List<ImageInfo> imageInfoList = imageInfoMapper.getImageInfo(imageInfo);
//            if(imageInfoList.size()>0){
//                ImageInfo imageInfos = imageInfoList.get(0);
//                List<ImageInfo> imageInfoLists = new ArrayList<ImageInfo>();
//                imageInfoLists.add(imageInfos);
//                hourseInfo.setImageInfoList(imageInfoLists);
//            }else{
//                hourseInfo.setImageInfoList(imageInfoList);
//            }
            hourseInfo.setImageInfoList(imageInfoList);
        }
        return hoursesList;
    }
}
