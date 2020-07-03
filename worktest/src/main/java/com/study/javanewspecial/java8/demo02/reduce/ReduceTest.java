package com.study.javanewspecial.java8.demo02.reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * todo
 *
 * @author ldb
 * @date 2020/06/05
 */
public class ReduceTest {

    public static void main(String[] args) {
        Cat cat1 = new Cat(1, "xiaoxiao");
        Cat cat2 = new Cat(2, "huahau");
        Cat cat3 = new Cat(3, "baibai");
        List<Cat> cats = Arrays.asList(cat1, cat2, cat3);
        Cat res = cats.stream().reduce(new Cat(1,"boss"),(x, y) -> {
            x.setAge(x.getAge() + y.getAge());
            return x;
        });
        System.out.println(res);
        System.out.println(cats);

        System.out.println(UUID.randomUUID().toString());

    }
}
