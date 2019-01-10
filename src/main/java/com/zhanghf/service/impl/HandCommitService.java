package com.zhanghf.service.impl;

import com.zhanghf.entry.Blog;
import com.zhanghf.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

@Component
public class HandCommitService {
    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;

    @Autowired
    BlogService blogService;


    public boolean save(Blog blog) {
        DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition();
        //设置事务的传播属性
        transDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
        //设置事务的隔离级别
        transDefinition.setIsolationLevel(DefaultTransactionDefinition.ISOLATION_DEFAULT);
        //获取事务状态
        TransactionStatus transactionStatus = transactionManager.getTransaction(transDefinition);
        try {
            /**
             * 写自己的操作数据库的代码
             */
            blogService.save(blog);
            //提交事务
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            //回滚事务
            transactionManager.rollback(transactionStatus);
        }
        return true;
    }
}
