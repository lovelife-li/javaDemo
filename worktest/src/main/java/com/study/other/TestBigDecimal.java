package com.study.other;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * BigDecimal 舍入法测试
 *
 * @author ldb
 * @date 2020/11/15
 */
public class TestBigDecimal {

    @Test
    public void test01(){
        BigDecimal a = new BigDecimal("3.56499999");
        a.setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(a.doubleValue());

        BigDecimal b = new BigDecimal("3.56499999");
        double v = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("ROUND_HALF_UP:v="+v);
//        System.out.println("b="+b);

        v = b.setScale(2, BigDecimal.ROUND_UP).doubleValue();
        System.out.println("ROUND_UP:v="+v);

        v = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        System.out.println("ROUND_DOWN:v="+v);

        v = b.setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        System.out.println("ROUND_CEILING():v="+v);

        v = b.setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
        System.out.println("ROUND_FLOOR:v="+v);

        v = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("ROUND_HALF_UP:v="+v);

        v = b.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
        System.out.println("ROUND_HALF_DOWN:v="+v);

        v = b.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
        System.out.println("ROUND_HALF_EVEN:v="+v);

        // 舍入模式断言请求的操作具有精确的结果，因此不需要舍入。 如果能产生精确结果的操作指定此舍入模
//        v = b.setScale(2, BigDecimal.ROUND_UNNECESSARY).doubleValue();
//        System.out.println("ROUND_UNNECESSARY:v="+v);
    }

    @Test
    public void test02() {
        BigDecimal a = new BigDecimal("3.56499999");
        a = a.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(a.doubleValue());
    }
}
