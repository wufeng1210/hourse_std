package com.hourse.web.provider;

import com.hourse.web.model.Hourse;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * Created by dell on 2017/5/14.
 */
public class HourseProvider {
    private final String TBL_ORDER = "hourse_info AS t";

    public String queryHourseByParam(Hourse hourse) {
        SQL sql = new SQL().SELECT("t.*").FROM(TBL_ORDER);
        if (StringUtils.hasText(hourse.getProvince())) {
            sql.WHERE("t.province LIKE CONCAT('%',#{province},'%' )");
        }
        if (StringUtils.hasText(hourse.getCity())) {
            sql.WHERE("t.city LIKE CONCAT('%',#{city},'%' )");
        }

        return sql.toString();
    }
}
