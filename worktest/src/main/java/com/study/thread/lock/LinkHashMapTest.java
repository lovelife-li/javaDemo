package com.study.thread.lock;

import java.util.LinkedHashMap;

/**
 * @author ldb
 * @date 2020/9/20
 * @dsscription
 */
public class LinkHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        map.put("d", "4");
        System.out.println(map);
//        print(map);
        map.get("a");
        map.get("b");
        System.out.println(map);
        map.put("c", "5");
        System.out.println(map);
//        print(map);
    }

    public static void print(LinkedHashMap<String, String> map) {
        map.forEach((x, y) -> {
            System.out.print(x + " ");
        });
        System.out.println();
    }
}
