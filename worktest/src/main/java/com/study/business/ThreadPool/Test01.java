package com.study.business.ThreadPool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author ldb
 * @date 2020/11/24
 * @dsscription
 */
@Slf4j
public class Test01 {

    @Test
    public void testScale() {
        BigDecimal bigDecimal1 = new BigDecimal("100");
        BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(100d));
        BigDecimal bigDecimal3 = new BigDecimal(String.valueOf(100));
        BigDecimal bigDecimal4 = BigDecimal.valueOf(100d);
        BigDecimal bigDecimal5 = new BigDecimal(Double.toString(100));

        print(bigDecimal1); //scale 0 precision 3 result 401.500
        print(bigDecimal2); //scale 1 precision 4 result 401.5000
        print(bigDecimal3); //scale 0 precision 3 result 401.500
        print(bigDecimal4); //scale 1 precision 4 result 401.5000
        print(bigDecimal5); //scale 1 precision 4 result 401.5000
    }

    public void print(BigDecimal bigDecimal) {
        log.info("scale {} precision {} result {}", bigDecimal.scale(), bigDecimal.precision(), bigDecimal.multiply(new BigDecimal("4.015")));
    }

    @Test
    public void test2() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        List<Integer> subList = list.subList(1, 4);
        System.out.println(subList);
        subList.remove(1);
        System.out.println(list);
        list.add(0);
        try {
            subList.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


//    private List<String> rightMethod(FooService fooService, Integer i, String s, String t) {
//        log.info("result {} {} {} {}", Optional.ofNullable(i).orElse(0) + 1, "OK".equals(s), Objects.equals(s, t), new HashMap<String, String>().put(null, null));
//        Optional.ofNullable(fooService)
//                .map(FooService::getBarService)
//                .filter(barService -> "OK".equals(barService.bar()))
//                .ifPresent(result -> log.info("OK"));
//        return new ArrayList<>();
//    }

    //    @GetMapping("right")
//    public int right(String test) {
//        return Optional.ofNullable(rightMethod(test.charAt(0) == '1' ? null : new FooService(),
//                test.charAt(1) == '1' ? null : 1,
//                test.charAt(2) == '1' ? null : "OK",
//                test.charAt(3) == '1' ? null : "OK"))
//                .orElse(Collections.emptyList()).size();
//    }
}
