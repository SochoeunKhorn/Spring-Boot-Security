package com.sochoeun.securityjwt.service;

import com.sochoeun.securityjwt.model.User;
import com.sochoeun.securityjwt.model.request.UserRequest;
import com.sochoeun.securityjwt.model.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();
    User getUser(Integer userId);
    void disableUser(Integer userId);
    void enableUser(Integer userId);

    void updateUser(Integer userId, UserRequest request);
}
