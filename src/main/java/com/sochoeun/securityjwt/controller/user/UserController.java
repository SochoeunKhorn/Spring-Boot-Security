package com.sochoeun.securityjwt.controller.user;

import com.sochoeun.securityjwt.model.BaseResponse;
import com.sochoeun.securityjwt.model.Role;
import com.sochoeun.securityjwt.model.User;
import com.sochoeun.securityjwt.model.request.UserRequest;
import com.sochoeun.securityjwt.model.response.UserResponse;
import com.sochoeun.securityjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private BaseResponse baseResponse;
    @GetMapping
    public ResponseEntity<?> getUsers(){
        List<UserResponse> users = userService.getUsers();
        baseResponse = new BaseResponse();
        baseResponse.success(users);
        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping("/disable/{userId}")
    public ResponseEntity<?> disableUser(@PathVariable Integer userId){
        userService.disableUser(userId);
        return ResponseEntity.ok("User ID: %s disabled".formatted(userId));
    }

    @PostMapping("/enable/{userId}")
    public ResponseEntity<?> enableUser(@PathVariable Integer userId){
        userService.enableUser(userId);
        return ResponseEntity.ok("User ID: %s enabled".formatted(userId));
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId,@RequestBody UserRequest request){
        userService.updateUser(userId,request);
        return ResponseEntity.ok("User ID: %s updated".formatted(userId));
    }
}
