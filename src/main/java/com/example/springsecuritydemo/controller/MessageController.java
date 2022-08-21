package com.example.springsecuritydemo.controller;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api")
public class MessageController {
    @GetMapping(value = "/admin/message")
    public String adminMessage(Authentication auth) {
        String role = "";
        for (GrantedAuthority gauth: auth.getAuthorities()) {
            role = gauth.getAuthority();
        }
        return auth.getName()+ "you are" + role;
    }

    @GetMapping(value = "/user/message")
    public String userMessage(Authentication auth) {
        String role = "";
        for (GrantedAuthority gauth: auth.getAuthorities()) {
            role = gauth.getAuthority();
        }
        return auth.getName()+ "you are" + role;
    }

    @GetMapping(value = "/any/message")
    public String anyMessage(Authentication auth) {
        String role = "";
        for (GrantedAuthority gauth: auth.getAuthorities()) {
            role = gauth.getAuthority();
        }
        return auth.getName()+ "you are" + role;
    }
}
