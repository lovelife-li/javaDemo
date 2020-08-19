package com.study.other;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 保留小数
 *
 * @author ldb
 * @date 2020/08/07
 */
public class TestDouble {

    public static void main(String[] args) {
        double d = 114.145;
        d = (double) Math.round(d * 100) / 100;
        System.out.println(d);

        d = 114.1452132324224;
        BigDecimal b = new BigDecimal(d);
        d = b.setScale(5, RoundingMode.HALF_UP).doubleValue();
        System.out.println(d);

        // #.00表示保留后两位，它的处理方式是直接截掉不要的尾数，不四舍五入。
        d = 114.145;
        DecimalFormat df = new DecimalFormat("#.0000");
        String str = df.format(d);
        System.out.println(str);
    }
}
