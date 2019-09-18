package com.demo.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);
    // AtomicStampedReference(V initialRef, int initialStamp)，initialRef 初始值，initialStamp 版本或时间戳
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        System.out.println("--------------以下是ABA问题的产生----------------");
        new Thread(()->{
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        },"t1").start();
        new Thread(()->{
            // 暂停1秒仲，目的是让t1执行一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            boolean b = atomicReference.compareAndSet(100, 2019);
            System.out.println(b);
        },"t2").start();

        // 暂停2秒仲，目的是让上面的ABA问题操作执行完成
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("--------------以下是ABA问题的解决----------------");

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第1次版本号："+stamp);
            // 暂停1秒仲t3线程
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            atomicStampedReference.compareAndSet(100, 101,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"\t 第2次版本号："+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"\t 第3次版本号："+atomicStampedReference.getStamp());
        },"t3").start();
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第1次版本号："+stamp);
            // 暂停3秒仲t4线程，目的是让t3执行完成
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            boolean b = atomicStampedReference.compareAndSet(100, 2019,
                    stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName()+"\t修改结果："+ b
                    +"\t当前的实际版本号："+atomicStampedReference.getStamp());
        },"t4").start();
    }
}