package com.hourse.web.service;


import com.hourse.web.model.User;

import java.util.List;

public interface IUserService {
    public List<User> getUserById(User user);
}
