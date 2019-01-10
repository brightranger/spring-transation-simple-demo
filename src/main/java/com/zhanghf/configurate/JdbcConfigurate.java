package com.zhanghf.configurate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

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
    public DataSource dataSource(){
        try {
            return new SimpleDriverDataSource(new com.mysql.jdbc.Driver(), "jdbc:mysql://localhost:3306/employeedb?serverTimezone=UTC", "root", "root");
        } catch (SQLException e) {
        }
        return null;
    }

}
