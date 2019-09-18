package com.demo.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        //当前值是5，期望值是5则设置当前值为2019
        System.out.println(atomicInteger.compareAndSet(5, 2019)+" current value:"+ atomicInteger.get());

        atomicInteger.getAndIncrement();//代替 i++
    }
}
