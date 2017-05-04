package com.hourse.web.service;


import com.hourse.web.model.ActivityInfo;
import com.hourse.web.model.User;

import java.util.List;

public interface IActivityService {
    public List<ActivityInfo> getActivityInfoByState(ActivityInfo activityInfo);
}
