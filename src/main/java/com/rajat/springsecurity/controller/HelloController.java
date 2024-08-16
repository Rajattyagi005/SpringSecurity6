package com.rajat.springsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

@GetMapping("/")
    public String greet(HttpServletRequest httpServletRequest) {
        return "Welcome to Spring security Rajat" + httpServletRequest.getSession().getId();
    }
}
