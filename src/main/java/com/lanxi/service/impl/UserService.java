package com.lanxi.service.impl;

import com.lanxi.dao.UserMapper;
import com.lanxi.entity.User;
import com.lanxi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by summer on 16-8-12.
 */

@Service
public class UserService extends BaseService<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;




    @Override
    public User login(String username, String password) {
        int count = userMapper.checkUsername(username);
        if(count > 0){
            return userMapper.checkPassword(username,password);
        }
        return null;
    }

    @Override
    public int register(User user) {
        user.setRegTime(new Date());
        userMapper.addUser(user);
        return user.getId();
    }

}
