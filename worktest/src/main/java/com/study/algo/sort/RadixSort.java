package com.study.algo.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author ldb
 * @date 2020/05/15
 * @description 基数排序，需要稳定排序算法，数据位数一样
 * 用法：10万手机号码排序  10*O(n)
 */
public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 从个位开始，对数组arr按"指数"进行排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    /**
     * 计数排序-对数组按照"某个位数"进行排序
     *
     * @param arr
     * @param exp 指数
     */
    public static void countingSort(int[] arr, int exp) {
        if (arr.length <= 1) {
            return;
        }

        // 计算每个元素的个数
        int[] c = new int[10];
        for (int i = 0; i < arr.length; i++) {
            c[(arr[i] / exp) % 10]++;
        }

        // 计算排序后的位置
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }

        // 临时数组r，存储排序之后的结果
        int[] r = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            r[c[(arr[i] / exp) % 10] - 1] = arr[i];
            c[(arr[i] / exp) % 10]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = r[i];
        }
    }

    public static void main(String[] args) {
        int arr[]= new int[10];
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            StringBuilder val = new StringBuilder("1");
            for (int j = 0; j < 4; j++) {
                val.append(r.nextInt(10));
            }
            arr[i]=Integer.valueOf(val.toString());
        }
        System.out.println(Arrays.toString(arr));
        arr[0] = 0;
        arr[1] = 2;
        radixSort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
