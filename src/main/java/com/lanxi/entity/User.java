package com.lanxi.entity;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String password;

    private Integer age;

    private String hobby;

    private Date birthday;

    private Date regTime;

    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }


    public User() {
    }

    public User(Integer id, String username, String password) {
        this.username = username;
        this.id = id;
        this.password = password;
    }

    public User(Integer id, String username, String password, Integer age, String hobby, Date birthday, Date regTime, String state) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.hobby = hobby;
        this.birthday = birthday;
        this.regTime = regTime;
        this.state = state;
    }
}