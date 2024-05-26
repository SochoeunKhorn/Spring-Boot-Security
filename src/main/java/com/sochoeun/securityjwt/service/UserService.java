package com.sochoeun.securityjwt.service;

import com.sochoeun.securityjwt.model.User;

public interface UserService {
    User getUser(String email);
}
