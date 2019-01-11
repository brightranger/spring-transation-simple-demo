package com.zhanghf.configurate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@ComponentScan(basePackages={"com.zhanghf.service"})// 扫描BlogService实现类所在的包路径
@ImportResource(locations={"classpath:beans.xml"})// 添加事务管理
public class JdbcConfigurate {


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionTemplate transactionTemplate(DataSourceTransactionManager transactionManager){
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager);
        //设置隔离级别
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        //传播属性
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //设置超时
        transactionTemplate.setTimeout(30);
        return transactionTemplate;
    }

    @Bean
    public DataSource dataSource(){
        try {
            return new SimpleDriverDataSource(new com.mysql.jdbc.Driver(), "jdbc:mysql://localhost:3306/employeedb?serverTimezone=UTC", "root", "root");
        } catch (SQLException e) {
        }
        return null;
    }

}
