package com.wyx.toolbox.datasource.dao.mapper.demo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyx.toolbox.datasource.dao.entity.demo.DemoUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoUserMapper extends BaseMapper<DemoUser> {
}
