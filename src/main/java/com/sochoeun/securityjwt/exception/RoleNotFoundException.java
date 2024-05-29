package com.sochoeun.securityjwt.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String role){
        super("Role '%s' not found".formatted(role));
    }
}
