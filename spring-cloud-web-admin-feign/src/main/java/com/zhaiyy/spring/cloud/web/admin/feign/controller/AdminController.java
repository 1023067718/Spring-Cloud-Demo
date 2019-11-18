package com.zhaiyy.spring.cloud.web.admin.feign.controller;

import com.zhaiyy.spring.cloud.web.admin.feign.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "hi")
    public String sayHi(String message) {
        return adminService.sayHi(message);
    }
}
