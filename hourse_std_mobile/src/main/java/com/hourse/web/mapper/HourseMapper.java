package com.hourse.web.mapper;

import com.hourse.web.model.Hourse;
import com.hourse.web.provider.HourseProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wufeng on 2017/4/11.
 */
@Repository
public interface HourseMapper {
    @Select("select * from hourse_info where province like '%#{province}%'")
    List<Hourse> getHourseInfo(Hourse hourse);

    @SelectProvider(type = HourseProvider.class, method = "queryHourseByParam")
    List<Hourse> queryHourseByParam(Hourse hourse);

    @SelectProvider(type = HourseProvider.class, method = "queryHourseByParamForMap")
    List<Hourse> queryHourseByParamForMap(Hourse hourse);
}
