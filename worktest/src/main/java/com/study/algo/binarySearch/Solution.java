package com.study.algo.binarySearch;

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
        System.out.println(getResult(10, 0.000001, 10000));
        double res = getResult(10, 0.000001, 10000);
        System.out.println(String.format("%d的平方根是%f", 10, res));
    }
}
