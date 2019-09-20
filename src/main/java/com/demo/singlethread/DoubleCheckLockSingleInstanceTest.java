package com.demo.singlethread;

/**
 * 多线程下，双重检索模式
 */
public class DoubleCheckLockSingleInstanceTest {
    private volatile static DoubleCheckLockSingleInstanceTest instance;

    private DoubleCheckLockSingleInstanceTest() {
        System.out.println("这是DoubleCheckLockSingleInstanceTest()");
    }

    public static DoubleCheckLockSingleInstanceTest getSingleton() {
        if (instance == null) {
            synchronized (DoubleCheckLockSingleInstanceTest.class) {
                if (instance == null) {
                    instance = new DoubleCheckLockSingleInstanceTest();
                }
            }
        }
        return instance;
    }
}
