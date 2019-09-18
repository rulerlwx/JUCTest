package com.demo.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContainerNotSafeTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
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
         *
         * 4 优化建议（同样的错误不犯第2次）
         *
         */
    }
}