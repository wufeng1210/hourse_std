package com.hourse.web.mapper;

import com.hourse.web.model.CollectInfo;
import com.hourse.web.model.ImageInfo;
import com.hourse.web.provider.CollectInfoProvider;
import com.hourse.web.provider.HourseProvider;
import com.hourse.web.provider.ImageProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wufeng on 2017/4/11.
 */
@Repository
public interface CollectInfoMapper {
    @Select("select * from collect_info where hourseId = #{hourseId} and userId = #{userId}")
    List<CollectInfo> getCollectInfo(CollectInfo collectInfo);

    /**
     * 增加用户收藏信息
     * @param collectInfo
     * @return
     */
    @InsertProvider(method = "insert", type = CollectInfoProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "collectId")
    int insert(CollectInfo collectInfo);

    @UpdateProvider(method = "update", type = CollectInfoProvider.class)
    int update(CollectInfo collectInfo);
}
