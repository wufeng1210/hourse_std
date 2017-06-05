package com.hourse.web.mapper;

import com.hourse.web.model.Hourse;
import com.hourse.web.provider.HourseProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wufeng on 2017/4/11.
 */
@Repository
public interface HourseMapper {
    @Select("select * from hourse_info where province like '%#{province}%'")
    List<Hourse> getHourseInfo(Hourse hourse);

    /**
     * 根据用户经纬度获取房屋信息列表，再根据距离和房屋地址排序
     * @param hourse
     * @return
     */
    @SelectProvider(type = HourseProvider.class, method = "queryHourseByParam")
    List<Hourse> queryHourseByParam(Hourse hourse);
    /**
     * 根据用户经纬度计算距离，统计相同距离的房屋数量
     * @param hourse
     * @return
     */
    @SelectProvider(type = HourseProvider.class, method = "queryRecommendHourseByParamForMap")
    List<Hourse> queryRecommendHourseByParamForMap(Hourse hourse);

    /**
     * 根据用户经纬度计算距离，统计相同距离的房屋数量
     * @param hourse
     * @return
     */
    @SelectProvider(type = HourseProvider.class, method = "queryHourseByParamForMap")
    List<Hourse> queryHourseByParamForMap(Hourse hourse);

    /**
     * 增加房屋信息，返回房屋id
     * @param hourse
     * @return
     */
    @InsertProvider(method = "insert", type = HourseProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "hourseId")
    int insert(Hourse hourse);

    /**
     * 根据房屋是否已出租字段和状态获取房屋信息列表
     * @param hourse
     * @return
     */
    @SelectProvider(type = HourseProvider.class, method = "queryHourseByisRentAndState")
    List<Hourse> queryHourseByisRentAndState(Hourse hourse);
}
