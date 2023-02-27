package com.example;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BasicWebApplicationTests {
    @Resource
    private GenericObjectPool<StringBuffer> objectPool;

    @Test
    public void testPool() throws Exception {
        objectPool.addObject();
        StringBuffer stringBuffer = objectPool.borrowObject(123L);
        objectPool.clear();
        objectPool.listAllObjects();
        objectPool.returnObject(new StringBuffer());
        System.out.println();
    }


    @Test
    void contextLoads() {
    }

}
