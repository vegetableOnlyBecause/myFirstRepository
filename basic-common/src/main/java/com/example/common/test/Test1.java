package com.example.common.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/9/12 15:50
 * @description:
 */
public class Test1{
    volatile int i = 0;
    private static Unsafe unsafe = null;
    //偏移量
    private static long valueOffset;
    static {
        //此方法不能用，JDK不让别人用，只让它自己用
        //unsafe = Unsafe.getUnsafe();
        try {
            //正确的方法是用反射来调用
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            /**将此对象的{@code accessible}标志设置为指定的布尔值.
             *{@code true}表示反射的对象应该禁止Java语言访问检查何时使用.
             *{@code false}表示反映的对象应该强制执行Java语言访问检查。*/
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            //根据反射拿到CounterUnsafe类（本类）中的i字段的偏移量
            Field field = Test1.class.getDeclaredField("i");
            valueOffset = unsafe.objectFieldOffset(field);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        CountDownLatch latch =new CountDownLatch(5);
    }


    public void add() {
        while (true) {
            //拿到旧值
            int current = unsafe.getIntVolatile(this, valueOffset);
            //通过CAS来修改i值
            boolean b = unsafe.compareAndSwapInt(this, valueOffset, current, current + 1);
            //如果失败，发生自旋，如果成功，跳出循环
            if (b) {
                System.out.println(i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Test1 t1 = new Test1();
        t1.add();
    }
}
