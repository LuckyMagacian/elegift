package com.lanxi.dao;

import com.lanxi.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> getUserList(int offset, int count);
    int checkUsername(String username);
    User checkPassword(String username,String password);
    int addUser(User user);
    User getUserById(int id);
    boolean update(User user);


}