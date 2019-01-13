package com.zhanghf.service.impl;

import com.zhanghf.entry.Blog;
import com.zhanghf.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class TransactionTemplateService {
    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    BlogService blogService;

    public Boolean save(Blog blog) {

        Boolean result;
        if(transactionTemplate==null){
            return true;
        }
        result = this.transactionTemplate
            .execute(status -> {
                //如果要回滚。则抛异常即上可
                if(blogService==null){
                    throw new NullPointerException("blogService 不能为空");
                }
                blogService.save(blog);
                return true;
            });
        return result;

    }



}
