package com.sochoeun.securityjwt.service;

import com.sochoeun.securityjwt.model.User;
import com.sochoeun.securityjwt.model.request.UserRequest;
import com.sochoeun.securityjwt.model.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserResponse> getUsers(String firstname);
    User getUser(Integer userId);
    void disableUser(Integer userId);
    void enableUser(Integer userId);

    void updateUser(Integer userId, UserRequest request);

    String uploadProfile(Integer userId, MultipartFile file);

}
