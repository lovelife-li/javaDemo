package com.study.utils.collection;

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
     * put已经存在的值，返回旧值
     * put已经不存在的值，返回null
     */
    @Test
    public void testMap1() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        String v = map.put("b", "v"); // 输出 B
        System.out.println(v);
        String v1 = map.put("c", "v");
        System.out.println(v1); // 输出：NULL
    }

    /**
     * compute,put增强，通过一个函数（key,oldValue)计算value值。
     * compute：返回函数计算的值。
     */
    @Test
    public void testMap2() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        String val = map.compute("b", (k, v) -> {
            System.out.println("k=" + k + ",v=" + v);
            return "v";
        });
        System.out.println(val); // 输出 v
        String v1 = map.compute("c", (k, v) -> "v"); // 输出 v
        System.out.println(v1);
        String v2 = map.compute("d", (key, oldValue) -> {
            System.out.println(key + "," + oldValue);
            return "dd";
        });
        System.out.println(v2); // 输出 dd
        System.out.println(map);
    }

    /**
     * putIfAbsent返回值跟put 一样
     * 指定key不存在，就put.存在，就什么也不做
     */
    @Test
    public void testMap3() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        String v = map.putIfAbsent("b", "v");  // 输出 B
        System.out.println(v);
        String v1 = map.putIfAbsent("c", "v");  // 输出 null
        System.out.println(v1);
        System.out.println(map);

    }

    /**
     * computeIfAbsent:存在时返回存在的值，不存在时返回新值
     * 如果缺席，才设置值。返回该值。
     * 如果不缺席，什么也不做。返回该值。
     * 参数为：key，value计算方法
     * 当key不存在时，执行value计算方法，计算value
     */
    @Test
    public void testMap4() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        String v = map.computeIfAbsent("b", k -> {
            System.out.println("==========" + k);
            return "abc";
        });  // 输出 B
        System.out.println(v);
        String v1 = map.computeIfAbsent("c", k -> {
            System.out.println("----key:" + k);
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
        map.put("a", "A");
        map.put("b", "B");
        String x = map.computeIfPresent("f", (key, value) -> {
            System.out.println("=============");
            return "world";
        });
        System.out.println(x);
        String b = map.computeIfPresent("b", (key, value) -> {
            System.out.println(key + "=============" + value);
            return "world";
        });
        System.out.println(b);

        System.out.println(map);
    }

    @Test
    public void TestMap6() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");

        // 存在key
        map.merge("a", "AA", (x, y) -> {
            System.out.println(x + "," + y);
            return "AAA";
        });

        // 不存在key
        map.merge("c", "CC", (x, y) -> {
            System.out.println(x + "," + y);
            return "CCC";
        });

        System.out.println(map);
    }

}
