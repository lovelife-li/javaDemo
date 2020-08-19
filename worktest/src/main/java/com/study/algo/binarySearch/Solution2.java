package com.study.algo.binarySearch;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * 如何编程实现 “ 求一个数的平方根 ” ？要求精确到小数点后 6  位。
 *
 * @author ldb
 * @date 2020/08/06
 */
public class Solution2 {


    public static double sqrt(int n, double cha, int bit) {

        double low = 0;
        double high = n;
        while (true) {
            double mid = low + ((high - low)/2);
            double tmp = mid * mid;
            double res = Math.abs(n - tmp);
            if (res < cha) {
                BigDecimal b = new BigDecimal(mid);
                return b.setScale(bit,RoundingMode.HALF_UP).doubleValue();
            } else {
                if (tmp < n) {
                    low = mid;
                } else {
                    high = mid;
                }
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(sqrt(5, 0.0000000001,2));


    }

}
