package com.zhanghf.service.impl;

import com.zhanghf.SpringTestBase;
import com.zhanghf.entry.Blog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionTemplateServiceTest extends SpringTestBase {

    @Autowired
    TransactionTemplateService transactionTemplateService;

    @Test
    public void save(){
        Blog blog = new Blog(18,"lili","url");
        transactionTemplateService.save(blog);

    }
}
