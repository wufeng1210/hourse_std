package com.hourse.web.provider;

import com.hourse.web.model.Hourse;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * Created by dell on 2017/5/14.
 */
public class HourseProvider {
    private final String TBL_ORDER = "hourse_info AS t ";

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
        sql.append(" GROUP BY distance DESC LIMIT 100");

        return sql.toString();
    }

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
        sql.append(" ORDER BY distance DESC LIMIT 100");

        return sql.toString();
    }
}
