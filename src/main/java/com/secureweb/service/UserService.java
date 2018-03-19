package com.secureweb.service;

import java.util.List;

import com.secureweb.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
    List getAllUser();
    void updateUser(String uId,String name,String dob,String state,String city,String mobile,String email);
}
