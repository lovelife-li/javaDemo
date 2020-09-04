package com.study.utils.collection;

import java.util.HashMap;

/**
 * todo
 *
 * @author ldb
 * @date 2020/09/04
 */
public class TestHashMap {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        map.compute("a",(key,oldVal)->{
           return "cc";
        });

        map.compute("a",(key,oldVal)->{
            System.out.println(key+","+oldVal);
            return oldVal;
        });

        System.out.println(map);
    }
}
