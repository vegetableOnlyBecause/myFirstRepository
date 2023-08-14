package com.example;

import com.example.common.redis.RedisOperator;
import com.example.common.utils.RedisUtils;
import com.example.dao.CartDao;
import com.example.filter.FilterService;
import com.example.filter.bo.FilterBO;
import com.example.model.CartDO;
import com.example.user.UserService;
import com.example.user.dto.UserDTO;
import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BasicWebApplicationTests {
    @Resource
    private GenericObjectPool<StringBuffer> objectPool;

    @Resource
    private FilterService filterService;

    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private CartDao cartDao;

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
    public void test() {
        UserDTO user = userService.getUserById(3098190);
        FilterBO filterBO = FilterBO.builder()
                .userDTO(user)
                .build();
        boolean filter = filterService.filter("3", filterBO);
        System.out.println(filter);
    }

}
