package com.hourse.web.service.impl;

import com.hourse.web.mapper.CollectInfoMapper;
import com.hourse.web.mapper.ImageInfoMapper;
import com.hourse.web.model.CollectInfo;
import com.hourse.web.model.ImageInfo;
import com.hourse.web.service.ICollectInfoService;
import com.hourse.web.service.IImageInfoService;
import com.hourse.web.util.ImageBase64Util;
import com.hourse.web.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wufeng on 2017/4/11.
 */
@Service
@Transactional
public class CollectInfoServiceImpl implements ICollectInfoService {

    private static Logger logger = LoggerFactory.getLogger(CollectInfoServiceImpl.class);

    @Resource
    private CollectInfoMapper collectInfoMapper;

    public List<CollectInfo> getCollectInfo(CollectInfo collectInfo) {
        return collectInfoMapper.getCollectInfo(collectInfo);
    }


    public int insertCollectInfo(CollectInfo collectInfo) {

        return collectInfoMapper.insert(collectInfo);
    }

    public int update(CollectInfo collectInfo) {
        return collectInfoMapper.update(collectInfo);
    }
}
