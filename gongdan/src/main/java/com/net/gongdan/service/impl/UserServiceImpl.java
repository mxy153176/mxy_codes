package com.net.gongdan.service.impl;

import com.net.gongdan.dao.UserMapper;
import com.net.gongdan.model.UserModel;
import com.net.gongdan.query.UserQuery;
import com.net.gongdan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserModel varifyUserInfo(UserQuery userQuery) {
        return userMapper.findUserByStaffIdAndPassword(userQuery);
    }

}
