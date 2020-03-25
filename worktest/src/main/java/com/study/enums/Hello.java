package com.study.enums;

public enum Hello {

    SEED;

    Hello(){
         System.out.println("hello");
    }



    public static void main(String[] args) {
        System.out.println(Hello.SEED);
        System.out.println("hello");
    }
}