package com.demo.singlethread;

/**
 * 开启多线程测试单例
 */
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
//                SingleInstanceTest.getInstance();
                DoubleCheckLockSingleInstanceTest.getSingleton();
            },String.valueOf(i)).start();
        }
    }
}
