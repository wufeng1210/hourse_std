package com.hourse.web.service.impl;

import com.hourse.web.mapper.UserMapper;
import com.hourse.web.model.User;
import com.hourse.web.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wufeng on 2017/4/11.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    public List<User> getUserById(User user) {
        return  userMapper.getUserInfo(user);
    }

    public List<User> getUserByUserName(User user) {
        return  userMapper.getUserByUserName(user);
    }

    public List<User> getUser(User user){
        return userMapper.getUser(user);
    }
    public int updateUserInfo(User user) {
        return userMapper.update(user);
    }

    public int interUserInfo(User user) {
        return userMapper.insert(user);
    }
}
