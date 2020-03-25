package com.study.javanewspecial.java8;

/**
 * @author ldb
 * @date 2020-03-06 16:12
 * @description ??
 */
public class JAVA8_Bug {
    public static void main(String[] args) {
        JAVA8_Bug h = new JAVA8_Bug();
        for (int i = 0; i < 50000; i++) {
            h.test();
        }
    }

    public void test() {
        int i = 8;
        while ((i -= 3) > 0) ;
        System.out.println("i= " + i);
    }

}
