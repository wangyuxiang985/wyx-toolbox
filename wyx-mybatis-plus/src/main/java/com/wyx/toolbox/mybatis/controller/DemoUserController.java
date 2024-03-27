package com.wyx.toolbox.mybatis.controller;

import com.wyx.toolbox.mybatis.dao.entity.DemoUser;
import com.wyx.toolbox.mybatis.service.DemoUserService;
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

    @GetMapping("/list")
    public List<DemoUser> getDemoUserList(){
        List<DemoUser> list = demoUserService.list();
        return list;
    }
}
