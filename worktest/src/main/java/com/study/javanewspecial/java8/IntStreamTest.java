package com.study.javanewspecial.java8;

import java.util.stream.IntStream;

/**
 * @author ldb
 * @date 2020-03-25 14:17
 * @description ??
 */
public class IntStreamTest {

    public static void main(String[] args) {
        int[] ints = {1, 5, 9, 2, 5, 63, 4, 8};
        System.out.println("________分割线① rangeClosed 使用_________");
        IntStream.rangeClosed(0, 5).forEach(System.out::println);

        System.out.println("________分割线② range 使用_________");
        IntStream.range(0, 5).forEach(System.out::println);

        System.out.println("________分割线② sum 使用_________");
        int sum = IntStream.rangeClosed(0, 5).sum();

        System.out.println(sum);
        System.out.println("________分割线② sorted 使用_________");
        IntStream.of(ints).sorted().forEach(System.out::println);

    }
}
