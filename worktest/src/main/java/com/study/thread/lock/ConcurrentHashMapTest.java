package com.study.thread.lock;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ldb
 * @date 2020-03-25 14:48
 * @description ??
 */
public class ConcurrentHashMapTest {

    /**
     * put返回旧值，如果没有则返回null
     */
    @Test
    public void testMap1() {
        Map<String, String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        String v = map.put("b","v"); // 输出 B
        System.out.println(v);
        String v1 = map.put("c","v");
        System.out.println(v1); // 输出：NULL
    }

    /**
     * compute（相当于put,只不过返回的是新值）
     * compute：返回新值
     *  当key不存在时，执行value计算方法，计算value
     */
    @Test
    public void testMap2() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        String val = map.compute("b", (k, v) -> "v"); // 输出 v
        System.out.println(val);
        String v1 = map.compute("c", (k, v) -> "v"); // 输出 v
        System.out.println(v1);
    }

    /**
     * putIfAbsent返回旧值，如果没有则返回null
     * 指定key的value不存在，就put.存在，就什么也不做
     */
    @Test
    public void testMap3() {
        Map<String, String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        String v = map.putIfAbsent("b","v");  // 输出 B
        System.out.println(v);
        String v1 = map.putIfAbsent("c","v");  // 输出 null
        System.out.println(v1);
        System.out.println(map);

    }

    /**
     * computeIfAbsent:存在时返回存在的值，不存在时返回新值
     *  如果缺席，才设置值。返回该值。
     *  如果不缺席，什么也不做。返回该值。
     *  参数为：key，value计算方法
     *  当key不存在时，执行value计算方法，计算value
     */
    @Test
    public void testMap4() {
        Map<String, String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        String v = map.computeIfAbsent("b",k->{
            System.out.println("==========");
            return "abc";
        });  // 输出 B
        System.out.println(v);
        String v1 = map.computeIfAbsent("c",k->{
            System.out.println("----key:"+k);
            return "v";
        }); // 输出 v
        System.out.println(v1);
        String b = map.computeIfAbsent("b", (x) -> "v");
        System.out.println(b);
        System.out.println(map);

    }

    /**
     * 如果存在，就设置值, 返回新值。
     * 如果不存在，就不设置值。返回null.
     */
    @Test
    public void testMap5() {
        Map<String, String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        String x = map.computeIfPresent("f", (key, value) -> {
            System.out.println("=============");
            return "world";
        });
        System.out.println(x);
        String b = map.computeIfPresent("b", (key, value) -> {
            System.out.println("=============");
            return "world";
        });
        System.out.println(b);

        System.out.println(map);
    }

}
