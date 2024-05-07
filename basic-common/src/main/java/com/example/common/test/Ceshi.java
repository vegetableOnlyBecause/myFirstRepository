package com.example.common.test;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/9/12 15:54
 * @description:
 */
@Service
public class Ceshi {
    @Resource
    private ThreadPoolTaskExecutor asyncServiceExecutor;
    public void ttt() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        List<Runnable> t = new ArrayList<>();
        Test2 test2 =  new Test2(latch);
        Test3 test3 =  new Test3(latch);
        t.add(test2);
        t.add(test3);
        long l = System.currentTimeMillis();
        for (Runnable test : t) {
            asyncServiceExecutor.execute(test);
        }
        latch.await();
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }

    private static final class Sync extends AbstractQueuedSynchronizer {

        private static final long serialVersionUID = -4694348664876563249L;


    }
}
