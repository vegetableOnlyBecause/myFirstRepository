package com.example.common.test;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/9/12 15:51
 * @description:
 */
public class Test3 implements Runnable {
    private CountDownLatch countDownLatch;

    public Test3(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(3000);
        System.out.println("3");
        countDownLatch.countDown();
    }

    public static void main(String[] args) {
        double  l = 999999 * 1024;
        System.out.println(""+l);
        double v = Double.parseDouble("1.023998976E9");
        System.out.println(v);
    }
}
