package com.sochoeun.securityjwt.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profile;
    private List<String> roles;
}
