package com.hourse.web.provider;

import com.hourse.web.model.Hourse;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * Created by dell on 2017/5/14.
 */
public class HourseProvider {
    private final String TBL_ORDER = "hourse_info";

    public String queryHourseByParam(Hourse hourse) {
        SQL sql = new SQL().SELECT("*").FROM(TBL_ORDER);
        String province = hourse.getProvince();
        if (StringUtils.hasText(province)) {
            sql.WHERE("province LIKE CONCAT('%',#{province},'%' )");
        }

        return sql.toString();
    }
}
