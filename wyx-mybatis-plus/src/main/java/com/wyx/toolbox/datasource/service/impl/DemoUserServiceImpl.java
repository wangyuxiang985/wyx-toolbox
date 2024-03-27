package com.wyx.toolbox.datasource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyx.toolbox.datasource.dao.entity.demo.DemoUser;
import com.wyx.toolbox.datasource.dao.mapper.demo.DemoUserMapper;
import com.wyx.toolbox.datasource.service.DemoUserService;
import org.springframework.stereotype.Service;

@Service
public class DemoUserServiceImpl extends ServiceImpl<DemoUserMapper, DemoUser> implements DemoUserService {
}
