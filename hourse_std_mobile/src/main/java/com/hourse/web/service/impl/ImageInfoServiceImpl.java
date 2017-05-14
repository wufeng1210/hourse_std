package com.hourse.web.service.impl;

import com.hourse.web.mapper.HourseMapper;
import com.hourse.web.mapper.ImageInfoMapper;
import com.hourse.web.model.Hourse;
import com.hourse.web.model.ImageInfo;
import com.hourse.web.service.IHourseService;
import com.hourse.web.service.IImageInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wufeng on 2017/4/11.
 */
@Service
@Transactional
public class ImageInfoServiceImpl implements IImageInfoService {

    @Resource
    private ImageInfoMapper imageInfoMapper;

    public List<ImageInfo> getImageInfo(ImageInfo imageInfo) {
        return imageInfoMapper.getImageInfo(imageInfo);
    }

}
