package com.lanxi.action;

import com.lanxi.entity.User;
import com.lanxi.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by summer on 16-8-11.
 */
@RestController
@RequestMapping("/users")
public class  UserAction {

    @Autowired
    UserService userService;


    /**
     *  非restful参数传递
     * @param name
     * @return
     */
    @RequestMapping("/test")
    public String test(String name){
        return "Hello " + name;
    }



    /**
     * restful参数传递
     * 获取所有用户
     * @return
     */
    @RequestMapping(value = "/{offset}/{count}", method = RequestMethod.GET)
    public List<User> getUserList(@PathVariable("offset") int offset, @PathVariable("count") int count){
        return userService.getList(offset,count);
    }





    @RequestMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") int id){
        return userService.getById(id);
    }



    @RequestMapping("/login")
    public User login(String username, String password){
        return userService.login(username,password);
    }


    @RequestMapping("/update")
    public boolean update(int id,String username, String password){
        return userService.update(new User(id,username,password));
    }

    @RequestMapping("/register")
    public int register(User user){
        return userService.register(user);
    }


}
