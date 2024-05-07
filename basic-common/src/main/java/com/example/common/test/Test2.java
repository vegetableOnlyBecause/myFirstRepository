package com.example.common.test;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/9/12 15:51
 * @description:
 */
public class Test2 implements Runnable{
    private CountDownLatch countDownLatch;

    public Test2(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(1000);
        System.out.println("2");
        countDownLatch.countDown();
    }

    public static void main(String[] args) {
        double a = 999999.1 * 1024;
        double b = 999999 * 1024;
        BigDecimal l = BigDecimal.valueOf(a);
        BigDecimal l2 = BigDecimal.valueOf(b);
        System.out.println(a);
        String s = l.toString();
        System.out.println(s);
    }
}
