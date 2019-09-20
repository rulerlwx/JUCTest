package com.demo.singlethread;

/**
 * 单线程下，懒汉模式
 */
public class SingleInstanceTest {
    private SingleInstanceTest(){
        System.out.println("这是SingleInstanceTest()");
    }
    public static SingleInstanceTest instance;

    public static SingleInstanceTest getInstance() {
        if (instance == null) {
            instance = new SingleInstanceTest();
        }
        return instance;
    }
}
