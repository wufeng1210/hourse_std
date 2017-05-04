package com.hourse.web.service.impl;

import com.hourse.web.mapper.ActivityMapper;
import com.hourse.web.mapper.UserMapper;
import com.hourse.web.model.ActivityInfo;
import com.hourse.web.model.User;
import com.hourse.web.service.IActivityService;
import com.hourse.web.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wufeng on 2017/4/11.
 */
@Service
@Transactional
public class ActivityServiceImpl implements IActivityService {

    @Resource
    private ActivityMapper activityMapper;

    public List<ActivityInfo> getActivityInfoByState(ActivityInfo activityInfo) {
        return activityMapper.getActivityInfo(activityInfo);
    }
}
