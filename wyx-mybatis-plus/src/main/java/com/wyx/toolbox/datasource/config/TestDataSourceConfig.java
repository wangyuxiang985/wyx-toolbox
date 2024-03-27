package com.wyx.toolbox.datasource.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.wyx.toolbox.datasource.interceptor.MyBatisPlusSqlAnnotationInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * test数据源配置
 * @author wangyu
 */
@Configuration
@MapperScan(basePackages = {"com.wyx.toolbox.datasource.dao.mapper.test"}, sqlSessionFactoryRef = "sqlSessionFactoryTest")
public class TestDataSourceConfig {

  @Value(value = "${flag.readOnly:true}")
  private boolean flagReadOnly;

  @Resource(name = "testDs")
  private DataSource dataSource;

  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptorTest() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
    paginationInnerInterceptor.setDbType(DbType.MYSQL);
    paginationInnerInterceptor.setMaxLimit(1000L);
    interceptor.addInnerInterceptor(paginationInnerInterceptor);
    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
    interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
    // 增加只读标识
    interceptor.addInnerInterceptor(new MyBatisPlusSqlAnnotationInterceptor(flagReadOnly));
    return interceptor;
  }


  @Bean
  @Primary
  public SqlSessionFactory sqlSessionFactoryTest() throws Exception {
    MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/test/*.xml"));
    factoryBean.setPlugins(mybatisPlusInterceptorTest());
    MybatisConfiguration configuration = new MybatisConfiguration();
    configuration.setMapUnderscoreToCamelCase(true);
    configuration.setCacheEnabled(false);
    configuration.setLogImpl(StdOutImpl.class);
    factoryBean.setConfiguration(configuration);
    return factoryBean.getObject();

  }

  @Bean
  @Primary
  public SqlSessionTemplate sqlSessionTemplateTest() throws Exception {
    return new SqlSessionTemplate(sqlSessionFactoryTest());
  }



}
