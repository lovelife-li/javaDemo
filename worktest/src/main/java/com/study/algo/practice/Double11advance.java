package com.study.algo.practice;

/**
 * 淘宝的“双十一”购物节有各种促销活动，比如“满 200 元减 50 元”。假设你女朋友的购物车中有 n 个（n>100）想买的商品，她希望从里面选几个，
 * 在凑够满减条件的前提下，让选出来的商品价格总和最大程度地接近满减条件（200 元），这样就可以极大限度地“薅羊毛”。
 * <p>
 * 白话：n个商品 大于200 最小值。不能超过200太多，比如250
 *
 * @author ldb
 * @date 2020/08/26
 */
public class Double11advance {

    /**
     * @param n     商品个数
     * @param v     最大价值
     * @param items 商品列表
     */
    public static void f(int n, int v, int[] items) {
        boolean[][] s = new boolean[n][v + 1];
        s[0][0] = true;
        s[0][items[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= v; j++) {
                if (s[i - 1][j]) {
                    s[i][j] = true;
                }

            }
            for (int j = 0; j <= v - items[i]; j++) {
                if (s[i - 1][j]) {
                    s[i][j + items[i]] = true;
                }
            }
        }
        // 找到最好一个数
        int k = 0;
        for (k = v; k >= 0; k--) {
            if (s[n - 1][k]) {
                break;
            }
        }
        for (int i = n - 1; i >= 1; i--) {
            if (k - items[i] >= 0 && s[i - 1][k - items[i]]) {
                System.out.println("价格：" + items[i] + " ");
                k = k - items[i];
            }
        }
        if (k != 0) {
            System.out.println("价格：" + items[0] + " ");
        }

    }

    public static void main(String[] args) {
        int[] items = {100, 200, 230, 50, 30, 20};
        f(items.length, 260, items);
    }

}
