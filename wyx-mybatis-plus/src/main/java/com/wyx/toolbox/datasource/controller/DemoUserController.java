package com.wyx.toolbox.datasource.controller;

import com.wyx.toolbox.datasource.dao.entity.demo.DemoUser;
import com.wyx.toolbox.datasource.dao.entity.test.TestUser;
import com.wyx.toolbox.datasource.service.DemoUserService;
import com.wyx.toolbox.datasource.service.TestUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoUserController {

    @Resource
    private DemoUserService demoUserService;
    @Resource
    private TestUserService testUserService;

    @GetMapping("/demoUser")
    public List<DemoUser> getDemoUserList1(){
        List<DemoUser> list = demoUserService.list();
        return list;
    }

    @GetMapping("/testUser")
    public List<TestUser> getTestUserList(){
        List<TestUser> list = testUserService.list();
        return list;
    }
}
