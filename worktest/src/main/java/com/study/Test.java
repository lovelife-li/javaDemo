package com.study;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ldb
 * @date 2020-03-15 14:13
 * @dsscription
 */
public class Test {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("wang", null);
        stringMap.put("ldb", "abc");
        stringMap.computeIfAbsent("wangsheng", K -> new String("wang"));
        stringMap.computeIfAbsent("wang", K -> new String("sheng"));
        stringMap.computeIfAbsent("sheng", k -> "string");
        stringMap.computeIfAbsent("wang", K -> "string");
        stringMap.computeIfAbsent("ldb", K -> "ldb2");
        for (Map.Entry n : stringMap.entrySet()) {
            System.out.println(n);
        }

        System.out.println(-1&1);
        System.out.println(2&3);
        System.out.println(-2|3);



    }
}
