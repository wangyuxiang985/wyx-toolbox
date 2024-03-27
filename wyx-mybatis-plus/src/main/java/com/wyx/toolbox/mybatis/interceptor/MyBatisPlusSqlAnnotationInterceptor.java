package com.wyx.toolbox.mybatis.interceptor;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;

import java.lang.reflect.Field;
import java.sql.Connection;

/**
 * MyBatisPlusSqlAnnotationInterceptor
 * <p>
 * 用于为MyBatisPlus的SQL语句添加注释，标记语句的执行者
 *
 * @author wangyu
 */
@Slf4j
public class MyBatisPlusSqlAnnotationInterceptor implements InnerInterceptor {

    private static final String READ_FLAG = "/!TDDL:SLAVE*/";

    private boolean readOnlyFlag;


    public MyBatisPlusSqlAnnotationInterceptor(boolean readOnlyFlag) {
        this.readOnlyFlag = readOnlyFlag;
    }


    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
            PluginUtils.MPStatementHandler handler = PluginUtils.mpStatementHandler(sh);
            MappedStatement ms = handler.mappedStatement();
            SqlCommandType sct = ms.getSqlCommandType();
            if (sct == SqlCommandType.SELECT && readOnlyFlag) {
                    BoundSql boundSql = handler.boundSql();
                    String sql = boundSql.getSql();
                    sql = READ_FLAG + sql;
                    try {
                        Field field = boundSql.getClass().getDeclaredField("sql");
                        field.setAccessible(true);
                        field.set(boundSql, sql);
                    } catch (NoSuchFieldException e) {
                        log.error("NoSuchFieldException：", e);
                    } catch (IllegalAccessException e) {
                        log.error("IllegalAccessException：", e);
                    }
                }
    }
}
