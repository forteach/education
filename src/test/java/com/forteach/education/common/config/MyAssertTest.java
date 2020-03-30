package com.forteach.education.common.config;

import com.forteach.education.common.keyword.DefineCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-20 14:46
 * @version: 1.0
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyAssertTest {

    @Test
    public void between() {
        MyAssert.between(-1, 0, 2, DefineCode.ERR0010,"不在区间");
    }
}