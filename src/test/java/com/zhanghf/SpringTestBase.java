package com.zhanghf;

import com.zhanghf.configurate.JdbcConfigurate;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(JdbcConfigurate.class)
public class SpringTestBase {
}
