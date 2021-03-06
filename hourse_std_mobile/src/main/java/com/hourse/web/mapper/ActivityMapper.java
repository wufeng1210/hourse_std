package com.hourse.web.mapper;

import com.hourse.web.model.ActivityInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wufeng on 2017/4/11.
 */
@Repository
public interface ActivityMapper {
    @Select("select * from activity_info where status = #{status}")
    List<ActivityInfo> getActivityInfo(ActivityInfo activityInfo);
}
