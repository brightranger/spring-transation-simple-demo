package com.zhanghf.service.impl;

import com.zhanghf.service.BlogService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED)
@Component
public class BlogServiceImpl2 implements BlogService2 {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void delete(int id){

        String sql = "delete from t_blog where id=?";
        jdbcTemplate.update(sql, id);
    }

}
