package com.study.other;

import java.util.regex.Pattern;

/**
 * todo
 *
 * @author ldb
 * @date 2020/06/24
 */
public class Test {

    static {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        System.out.println("\\n\n\\");
        System.out.println("\n");
        System.out.println("\\\n");
        System.out.print("\\\\n");

        System.out.println(Pattern.matches("\n", "\\n\n\\"));
        System.out.println(Pattern.matches("\\n", "\\n\n\\"));
        System.out.println(Pattern.matches("\\\n", "\\n\n\\"));
        System.out.println(Pattern.matches("\\\\n", "\\n\n\\"));
    }

}
