package com.sochoeun.securityjwt.service.impl;

import com.sochoeun.securityjwt.exception.RoleNotFoundException;
import com.sochoeun.securityjwt.model.Role;
import com.sochoeun.securityjwt.model.User;
import com.sochoeun.securityjwt.model.request.UserRequest;
import com.sochoeun.securityjwt.model.response.UserResponse;
import com.sochoeun.securityjwt.repository.RoleRepository;
import com.sochoeun.securityjwt.repository.UserRepository;
import com.sochoeun.securityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<UserResponse> getUsers(String firstname) {
        List<User> all;
        if(firstname == null){
           all = userRepository.findAll();
        }else {
          all =  userRepository.findAllByFirstnameContainingIgnoreCase(firstname);
        }


        List<UserResponse> userResponses = new ArrayList<>();
        all.forEach(user -> {
            String status = "";
          //  log.info("Status {}", user.getStatus());
            if (user.getStatus()) {
                status = "ACTIVE";
            } else {
                status = "DISABLE";
            }
            UserResponse response =UserResponse.builder()
                    .id(user.getId())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .profile(user.getProfile())
                    .status(status)
                    .build();
            userResponses.add(response);
        });
       // log.info("Response:{}",userResponses);
        return userResponses;
    }

    @Override
    public User getUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User ID: %s not found".formatted(userId)));

        return user;
    }

    @Override
    public void disableUser(Integer userId) {
        User user = getUser(userId);
        user.setStatus(false);
        userRepository.save(user);
    }

    @Override
    public void enableUser(Integer userId) {
        User user = getUser(userId);
        user.setStatus(true);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Integer userId, UserRequest request) {
        User user = getUser(userId);

        List<String> strRole =request.getRoles();
        List<Role> roles = new ArrayList<>();
        for (String role:strRole){
            Role getRole = roleRepository.findByName(role).orElseThrow(
                    () -> new RoleNotFoundException(role));
            roles.add(getRole);
        }

        user.setFirstname(request.getFirstName());
        user.setLastname(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setProfile(request.getProfile());
        user.setRoles(roles);

        userRepository.save(user);
    }

}
