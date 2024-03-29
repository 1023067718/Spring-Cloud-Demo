package com.zhaiyy.spring.cloud.web.admin.feign.service.hystrix;

import com.zhaiyy.spring.cloud.web.admin.feign.service.AdminService;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceHystrix implements AdminService {

    @Override
    public String sayHi(String message) {
        System.out.println("feign hystrix");
        return String.format("Hi your message is : %s but request bad", message);
    }
}
