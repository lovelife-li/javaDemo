package com.study.javanewspecial.java8.demo02.reduce;

import com.study.utils.web.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    @Test
    public void test01(){
        ArrayList<User> users = new ArrayList<>();
        Long id = users.stream().map(User::getId).min(Long::compareTo).orElse(0L);
        System.out.println(id);

        Set<Integer> sets = IntStream.of(16, 32, 64, 256).boxed().collect(Collectors.toSet());
        Integer a1 = new Integer(16);
        Integer a2 = new Integer(32);
        Integer a3 = new Integer(64);
        Integer a4 = new Integer(256);
        System.out.println(sets.contains(a1));
        System.out.println(sets.contains(a2));
        System.out.println(sets.contains(a3));
        System.out.println(sets.contains(a4));
    }
}
