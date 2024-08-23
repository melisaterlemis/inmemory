package com.spring.security.in_memory.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class PrivateController {

    @GetMapping
    public String hello(){
        return "PRIVATE SAYFAMIZA HOŞGELDİNİZ";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String helloUser(){
        return "PRIVATE USER SAYFAMIZA HOŞGELDİNİZ";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String helloAdmin(){
        return "PRIVATE ADMIN    SAYFAMIZA HOŞGELDİNİZ";
    }

}
