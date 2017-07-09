package com.hourse.web.mapper;

import com.hourse.web.model.Hourse;
import com.hourse.web.model.User;
import com.hourse.web.provider.HourseProvider;
import com.hourse.web.provider.UserProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wufeng on 2017/4/11.
 */
@Repository
public interface UserMapper {
    @Select("select * from user_info where userId = #{userId}")
    List<User> getUserInfo(User user);

    @Select("select * from user_info where userName = #{userName}")
    List<User> getUserByUserName(User user);

    @Select("select * from user_info where userName = #{userName} and userPassWord = #{userPassWord}")
    List<User> getUser(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @UpdateProvider(method = "update", type = UserProvider.class)
    int update(User user);

    /**
     * 增加用户信息
     * @param user
     * @return
     */
    @InsertProvider(method = "insert", type = UserProvider.class)
    int insert(User user);
}
