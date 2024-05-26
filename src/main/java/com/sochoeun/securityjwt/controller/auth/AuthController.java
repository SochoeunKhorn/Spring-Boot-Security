package com.sochoeun.securityjwt.controller.auth;

import com.sochoeun.securityjwt.model.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private BaseResponse baseResponse;
    @PostMapping("/api/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        AuthResponse registered = authService.register(request);
        return ResponseEntity.ok(registered);
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        AuthResponse login = authService.login(request);
        baseResponse  = new BaseResponse();
        baseResponse.success(login);
        return ResponseEntity.ok(baseResponse);
    }
}
