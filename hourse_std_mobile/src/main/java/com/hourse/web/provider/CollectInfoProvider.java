package com.hourse.web.provider;

import com.hourse.web.model.CollectInfo;
import com.hourse.web.model.ImageInfo;
import com.hourse.web.util.SqlProviderUtil;

/**
 * Created by 吴峰 on 2017/5/14.
 */
public class CollectInfoProvider {
    private final String TBL_ORDER = "collect_info AS t ";

    public static String insert(CollectInfo collectInfo){
        return SqlProviderUtil.provideInsertNotBlank(collectInfo, "collect_info");
    }

    public static String update(CollectInfo collectInfo){
        StringBuffer sql = new StringBuffer("UPDATE collect_info ");
        sql.append(SqlProviderUtil.provideSetterNotBlank(collectInfo));
        sql.append(" WHERE ");
        sql.append("collectId=#{collectId}");
        return sql.toString();
    }
}
