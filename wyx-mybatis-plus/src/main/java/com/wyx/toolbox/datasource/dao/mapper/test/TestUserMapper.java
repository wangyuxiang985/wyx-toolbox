package com.wyx.toolbox.datasource.dao.mapper.test;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyx.toolbox.datasource.dao.entity.test.TestUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestUserMapper extends BaseMapper<TestUser> {
}
