package com.sochoeun.securityjwt.service.impl;

import com.sochoeun.securityjwt.model.User;
import com.sochoeun.securityjwt.repository.UserRepository;
import com.sochoeun.securityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User getUser(String email) {
        User byEmail = userRepository.findUserByEmail(email);
        return byEmail;
    }
}
