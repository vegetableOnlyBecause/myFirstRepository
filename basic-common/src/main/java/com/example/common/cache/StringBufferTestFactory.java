package com.example.common.cache;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.stereotype.Component;

/**
 * @title: StringBuffer对象工厂(测试)
 * @author: vegetableOnlyBecause
 * @date 2023/2/15 16:21
 * @description:
 */
@Component
public class StringBufferTestFactory extends BasePooledObjectFactory<StringBuffer> {
    @Override
    public StringBuffer create() throws Exception {
        return new StringBuffer("abcd");
    }

    @Override
    public PooledObject<StringBuffer> wrap(StringBuffer value) {
        return new DefaultPooledObject<>(value);
    }
}
