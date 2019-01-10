package com.zhanghf.service.impl;

import com.zhanghf.entry.Blog;
import com.zhanghf.service.BlogService;
import com.zhanghf.service.BlogService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
@Component
public class BlogServiceImpl implements BlogService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BlogService2 blogService2;

    @Override
    public void save(Blog blog) {

        String sql = "insert into t_blog values(?,?,?)";
        jdbcTemplate.update(sql,
                new Object[]{blog.getId(), blog.getName(), blog.getUrl()},
                new int[]{java.sql.Types.INTEGER, java.sql.Types.VARCHAR, java.sql.Types.VARCHAR});

        blogService2.delete(16);
    }

    @Override
    public void update(Blog blog) {
        String sql = "update t_blog set name = ? where id=?";
        jdbcTemplate.update(sql, new Object[]{blog.getName(), blog.getId()},
                new int[]{java.sql.Types.VARCHAR, java.sql.Types.INTEGER});
    }

}
