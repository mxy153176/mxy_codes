package com.net.gongdan.service;

import com.net.gongdan.model.UserModel;
import com.net.gongdan.query.UserQuery;

public interface UserService {

    /**
     * 登录验证
     * @param query
     * @return
     */
    public UserModel varifyUserInfo(UserQuery query);

}
