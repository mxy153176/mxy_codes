package com.net.gongdan.dao;

import com.net.gongdan.model.UserModel;
import com.net.gongdan.query.UserQuery;

import java.util.List;

public interface UserMapper {
    UserModel findUserByStaffIdAndPassword(UserQuery userQuery);
}
