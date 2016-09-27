package com.lanxi.service;

import com.lanxi.entity.User;

/**
 * Created by summer on 16-8-12.
 */
public interface IUserService extends IBaseService<User>{

    User login(String username, String password);
    int register(User user);


}
