package com.study.javanewspecial.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ldb
 * @date 2020-03-30 9:19
 * @description ??
 */
public class HashMapTest {

    public static void main(String[] args) {
        Node[] table = new Node[]{new Node(2),new TreeNode("abc")};

        System.out.println(table[0].hash);
        System.out.println(TreeNode.class.cast(table[1]).name);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Node{
        int hash;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class TreeNode extends Node{
        String name;
    }
}
