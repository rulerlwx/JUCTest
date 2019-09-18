package com.demo.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合不安全
 */
public class ContainerNotSafeTest {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        /**
         * 1 故障现象
         *  java.util.ConcurrentModificationException
         * 2 导致原因
         *
         * 3 解决方案
         *  3.1 new Vector<>();
         *  3.2 Collections.synchronizedList(new ArrayList<>());
         * 4 优化建议（同样的错误不犯第2次）
         *
         */
    }
}