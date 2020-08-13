package com.study.algo.sort;

import java.util.Arrays;

/**
 * @author ldb
 * @date 2020/05/15
 * @description 划分n个桶，每个桶存的数据一样，比如分数
 * 限制：非负整数，数据范围小
 * 排序：{2, 5, 3, 0, 2, 3, 0, 3}
 */
public class CountingSort {
    public static void countingSort(int[] a, int n) {
        int max = 0;
        // 找到最大值
        for (int i = 0; i < n; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }

        // 划分桶,值为相同分数个数
        int[] c = new int[max + 1];
        for (int i = 0; i < a.length; i++) {
            c[a[i]]++;
        }
        // 依次累加
        for (int i = 1; i < max + 1; ++i) {
            c[i] = c[i - 1] + c[i];
        }

        // 临时数组r，存储排序之后的结果
        int[] r = new int[n];
        // 计算排序的关键步骤了，有点难理解
//        for (int i = n - 1; i >= 0; --i) {
//            int index = c[a[i]] - 1;
//            r[index] = a[i];
//            c[a[i]]--;
//        }

        for (int i = 0; i < n; ++i) {
            int index = c[a[i]] - 1;
            r[index] = a[i];
            c[a[i]]--;
        }
        System.out.println(Arrays.toString(r));
        //  将结果拷贝给 a  数组
        for (int i = 0; i < n; ++i) {
            a[i] = r[i];
        }

        // 已经得到排序结果了
        // 将结果拷贝会a数组
//        int k = 0;
//        for (int i = 0; i < c.length; i++) {
//            if (c[i] == 0) {
//                continue;
//            }
//            for (int j = 0; j < c[i]; j++) {
//                a[k++] = i;
//            }
//        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 5, 3, 0, 2, 3, 0, 3, 7, 9};
        countingSort(arr, arr.length);
        System.out.println(Arrays.toString(arr));

    }
}
