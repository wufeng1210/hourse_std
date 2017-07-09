package com.hourse.web.provider;

import com.hourse.web.model.User;
import com.hourse.web.util.SqlProviderUtil;

/**
 * Created by dell on 2017/6/18.
 */
public class UserProvider {
    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public String update(User user) {
        StringBuffer sql = new StringBuffer("UPDATE user_info ");
        sql.append(SqlProviderUtil.provideSetterNotBlank(user));
        sql.append(" WHERE ");
        sql.append("userId=#{userId}");
        return sql.toString();
    }

    /**
     * 增加用户信息
     * @param user
     * @return
     */
    public String insert(User user) {
        return SqlProviderUtil.provideInsertNotBlank(user, "user_info");
    }
}
