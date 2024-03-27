package com.wyx.toolbox.datasource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyx.toolbox.datasource.dao.entity.test.TestUser;
import com.wyx.toolbox.datasource.dao.mapper.test.TestUserMapper;
import com.wyx.toolbox.datasource.service.TestUserService;
import org.springframework.stereotype.Service;

@Service
public class TestUserServiceImpl extends ServiceImpl<TestUserMapper, TestUser> implements TestUserService {
}
