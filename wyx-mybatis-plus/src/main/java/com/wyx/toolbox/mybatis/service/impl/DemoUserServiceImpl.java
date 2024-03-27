package com.wyx.toolbox.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyx.toolbox.mybatis.dao.entity.DemoUser;
import com.wyx.toolbox.mybatis.dao.mapper.DemoUserMapper;
import com.wyx.toolbox.mybatis.service.DemoUserService;
import org.springframework.stereotype.Service;

@Service
public class DemoUserServiceImpl extends ServiceImpl<DemoUserMapper, DemoUser> implements DemoUserService {
}
