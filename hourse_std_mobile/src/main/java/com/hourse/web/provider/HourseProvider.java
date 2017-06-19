package com.hourse.web.provider;

import com.hourse.web.model.Hourse;
import com.hourse.web.util.SqlProviderUtil;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * Created by dell on 2017/5/14.
 */
public class HourseProvider {
    private final String TBL_ORDER = "hourse_info AS t ";

    /**
     * 根据用户经纬度获取房屋信息列表，再根据距离和房屋地址排序
     * @param hourse
     * @return
     */
    public String queryHourseByParam(Hourse hourse) {
        StringBuffer sql = new StringBuffer("select *, " +
                "ROUND(6378.138*2*ASIN(SQRT(POW(SIN((#{latitude}*PI()/180-latitude*PI()/180)/2),2)+COS(#{latitude}*PI()/180)*COS(latitude*PI()/180)*POW(SIN((#{longitude}*PI()/180-longitude*PI()/180)/2),2)))*1000) as distance ");
        sql.append(" from " + TBL_ORDER);
        sql.append( " where 1=1 ");
        if (StringUtils.hasText(hourse.getProvince())) {
            sql.append("and t.province LIKE CONCAT('%',#{province},'%' )");
        }
        if (StringUtils.hasText(hourse.getCity())) {
            sql.append("and t.city LIKE CONCAT('%',#{city},'%' )");
        }
        sql.append(" and status = 1");
        sql.append(" LIMIT 100");

        return sql.toString();
    }

    /**
     * 根据用户经纬度计算距离，统计相同距离的房屋数量
     * @param hourse
     * @return
     */
    public String queryHourseByParamForMap(Hourse hourse) {
        StringBuffer sql = new StringBuffer("select *,count(*) as hourseNum , " +
                "ROUND(6378.138*2*ASIN(SQRT(POW(SIN((#{latitude}*PI()/180-latitude*PI()/180)/2),2)+COS(#{latitude}*PI()/180)*COS(latitude*PI()/180)*POW(SIN((#{longitude}*PI()/180-longitude*PI()/180)/2),2)))*1000) as distance ");
        sql.append(" from " + TBL_ORDER);
        sql.append( " where 1=1 ");
        if (StringUtils.hasText(hourse.getProvince())) {
            sql.append("and t.province LIKE CONCAT('%',#{province},'%' )");
        }
        if (StringUtils.hasText(hourse.getCity())) {
            sql.append("and t.city LIKE CONCAT('%',#{city},'%' )");
        }
        sql.append(" and status = 1");
        sql.append(" group BY distance ORDER BY distance asc ");

        return sql.toString();
    }

    /**
     * 根据用户经纬度计算距离，统计相同距离的房屋数量
     * @param hourse
     * @return
     */
    public String queryRecommendHourseByParamForMap(Hourse hourse) {
        StringBuffer sql = new StringBuffer("select *, " +
                "ROUND(6378.138*2*ASIN(SQRT(POW(SIN((#{latitude}*PI()/180-latitude*PI()/180)/2),2)+COS(#{latitude}*PI()/180)*COS(latitude*PI()/180)*POW(SIN((#{longitude}*PI()/180-longitude*PI()/180)/2),2)))*1000) as distance ");
        sql.append(" from " + TBL_ORDER);
        sql.append( " where 1=1 ");
        if (StringUtils.hasText(hourse.getProvince())) {
            sql.append("and t.province LIKE CONCAT('%',#{province},'%' )");
        }
        if (StringUtils.hasText(hourse.getCity())) {
            sql.append("and t.city LIKE CONCAT('%',#{city},'%' )");
        }
        sql.append(" and status = 1");
        sql.append(" and recommend = '1' ORDER BY distance DESC LIMIT 100");

        return sql.toString();
    }

    /**
     * 根据房屋是否已出租字段和状态获取房屋信息列表
     * @param hourse
     * @return
     */
    public String queryHourseByisRentAndState(Hourse hourse){
        StringBuffer sql = new StringBuffer("select * " );
        sql.append(" from " + TBL_ORDER + "where ");

        sql.append(SqlProviderUtil.provideConditionNotBlank(hourse));

        return sql.toString();
    }

    /**
     * 增加房屋信息，返回房屋id
     * @param hourse
     * @return
     */
    public String insert(Hourse hourse) {
        return SqlProviderUtil.provideInsertNotBlank(hourse, "hourse_info");
    }

    /**
     * 修改房屋对象
     * @param hourse
     * @return
     */
    public String update(Hourse hourse) {
        StringBuffer sql = new StringBuffer("UPDATE hourse_info ");
        sql.append(SqlProviderUtil.provideSetterNotBlank(hourse));
        sql.append(" WHERE ");

        sql.append("hourseId=#{hourseId}");
        return sql.toString();
    }
}
