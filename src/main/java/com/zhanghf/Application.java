package com.zhanghf;

import com.zhanghf.configurate.JdbcConfigurate;
import com.zhanghf.entry.Blog;
import com.zhanghf.service.BlogService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(JdbcConfigurate.class);
        BlogService service = ac.getBean(BlogService.class);
        Blog b = new Blog(18,"lili","url");
        service.save(b);

    }
}
