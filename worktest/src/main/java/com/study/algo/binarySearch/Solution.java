package com.study.algo.binarySearch;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author ldb
 * @date 2020/05/15
 * @description 求一个正整数的平方根10
 */
public class Solution {

    /**
     * @param n      求解数
     * @param m      误差
     * @param maxTry 计算次数
     * @return
     */
    public static double getResult(int n, double m, int maxTry) {
        if (n <= 1) {
            return -1.0;
        }
//        double min = 1.0;
//        double max =(double) n;
//        for (int i = 0; i < maxTry; i++) {
//            double mid = (max + min) / 2;
//            double tmp = mid * mid;
//            double delta = Math.abs((tmp / n) - 1);
//            if (delta <= m) {
//                return mid;
//            } else {
//                if (tmp > n) {
//                    max = mid;
//                } else {
//                    min = mid;
//                }
//            }
//
//        }

        double min = 1.0, max = (double) n;
        for (int i = 0; i < maxTry; i++) {
            double middle = (min + max) / 2;
            double square = middle * middle;
            double delta = Math.abs((square / n) - 1);
            if (delta <= m) {
                return middle;
            } else {

                if (square > n) {
                    max = middle;
                } else {
                    min = middle;
                }
            }
        }

        return -1.0;
    }

    public static void main(String[] args) {
        System.out.println(getResult(13, 0.000001, 10000));
//        double res = getResult(6, 0.000001, 10000);
//        System.out.println(String.format("%d的平方根是%f", 10, res));

        double v = Math.sqrt(5) * Math.sqrt(5);
        System.out.println(v);

        System.out.println(get(80));
        System.out.println(cal(80));

        System.out.println(get2(5));
    }

    public static long get(double n){
        double d =  1.0/Math.sqrt(5)*( Math.pow((1+Math.sqrt(5.0d))/2.0,n)-Math.pow((1-Math.sqrt(5.0d))/2.0,n));
        BigDecimal b = new BigDecimal(d);
        return b.setScale(0, RoundingMode.HALF_UP).longValue();
    }

    static long cal(int i) {
        long r = 0;
        long f = 1;
        long s = 1;
        for (int j = 2; j < i; j++) {
            r = f + s;
            f = s;
            s = r;
        }
        return r;
    }

    public static long get2(double n){

//        double d =  1.0/Math.sqrt(5)*( Math.pow((1+Math.sqrt(5.0d))/2.0,n)-Math.pow((1-Math.sqrt(5.0d))/2.0,n));
//        BigDecimal x1 = new BigDecimal(1);
//        BigDecimal sqrt = new BigDecimal(5).sqrt(MathContext.DECIMAL128);
//        x1.divide(sqrt,MathContext.DECIMAL128).doubleValue();
//
//        System.out.println( x1.divide(sqrt,MathContext.DECIMAL128).doubleValue());

        return 1;
    }
}
