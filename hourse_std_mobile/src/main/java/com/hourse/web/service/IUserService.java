package com.hourse.web.service;


import com.hourse.web.model.User;

import java.util.List;

public interface IUserService {
    public List<User> getUserById(User user);

    public List<User> getUserByUserName(User user);

    public List<User> getUser(User user);


    int updateUserInfo(User user);

    public int interUserInfo(User user);
}
