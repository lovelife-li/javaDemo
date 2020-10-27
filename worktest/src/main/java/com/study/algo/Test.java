package com.study.algo;

import java.util.HashMap;

/**
 * @author ldb
 * @date 2020/5/17
 * @dsscription
 */
public class Test {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < 32; i++) {
            map.put(i,i);
        }
    }
}
