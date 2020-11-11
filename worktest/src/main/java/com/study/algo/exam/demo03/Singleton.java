package com.study.algo.exam.demo03;

import java.util.*;
import java.util.stream.Collectors;

public class Singleton {
    private Singleton() {
    }

    public static Singleton getSingleton() {
        return Inner.instance;
    }

    private static class Inner {
        private static final Singleton instance = new Singleton();
    }

    public static List<String> foo(List<String> list, String subString) {

        List<String> res = list.stream()
                .filter(x -> {
                    if (x == null || "".equals(x)) {
                        return false;
                    } else {
                        return x.contains(subString);
                    }
                })
                .map(x -> {
                    return new StringBuilder(x).reverse().toString();
                }).distinct().limit(3).collect(Collectors.toList());

        return res;
    }

    public static String sort(String str) {
        if (str == null) {
            return null;
        }
        if ("".equals(str)) {
            return str;
        }
        HashMap<String, Integer> map = new HashMap<>(32);
        for (char c : str.toCharArray()) {
            String key = String.valueOf(c);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        Optional<String> myRes = map.entrySet().stream().sorted((x1, x2) -> {
            if (x1.getValue().intValue() == x2.getValue().intValue()) {
                // 值相同，按字母顺序
                return x1.getKey().compareTo(x2.getKey());
            } else {
                return Integer.compare(x1.getValue(), x2.getValue());
            }
        }).map(x -> x.getKey()).reduce((x, y) -> {
            return new StringBuilder(x).append(y).toString();
        });
        return myRes.get();
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("not");
        list.add("add");
        list.add(null);
        list.add("dog");
        list.add("acid");
        list.add("add");
        list.add("elf");
        list.add("gap");
        list.add("cat");
        list.add("rat");
        List<String> res = foo(list, "a");
        System.out.println(res);

        System.out.println(sort("zzzaccbb"));
        System.out.println(sort("eeaddabccccaaeec"));
        System.out.println(sort(""));
        System.out.println();
    }
} 
