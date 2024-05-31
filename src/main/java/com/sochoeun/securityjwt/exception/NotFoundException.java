package com.sochoeun.securityjwt.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String name,Integer id){
        super("%name ID: '%s' not found".formatted(name,id));
    }
}
