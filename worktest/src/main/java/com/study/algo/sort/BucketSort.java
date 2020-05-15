package com.study.algo.sort;

import java.util.Arrays;

/**
 * @author ldb
 * @date 2020/05/15
 * @description 桶排序，把数据划分到m个桶，再对每个桶进行排序。（要求数据范围不大,比如10GB订单金额）
 */
public class BucketSort {
    /**
     * 桶排序算法
     *
     * @param arr        数据数组
     * @param bucketSize 桶元素个数
     * @param size       元素个数小于sise 用插入排序 否则用快速排序
     */
    public static void bucketSort(int[] arr, int bucketSize, int size) {
        if (arr.length < 2) {
            return;
        }
        // 数组最小值
        int minValue = 0;
        // 数组最大值
        int maxValue = 0;
        if (arr[0] > arr[1]) {
            maxValue = arr[0];
        } else {
            minValue = arr[1];
        }
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        // 桶数量(跟分页计算有想似之处)
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        System.out.println("桶数量：" + bucketCount + " 桶中元素个数：" + bucketSize);
        int[][] buckets = new int[bucketCount][bucketSize];
        // 记录每个桶中元素位置
        int[] indexArr = new int[bucketCount];

        // 将数组中值分配到各个桶里
        for (int i = 0; i < arr.length; i++) {
            // 桶下标计算
            int bi = (arr[i] - minValue) / bucketSize;
            // 因为相同元素过多，落在同一个桶里，使数组扩容
            if (indexArr[bi] == buckets[bi].length) {
                resizeCapacity(buckets, bi);
            }
            buckets[bi][indexArr[bi]++] = arr[i];
        }

        // 对每个桶进行排序，这里使用了快速排序,并放到arr
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            System.out.println(Arrays.toString(buckets[i]));
            if (indexArr[i] == 0) {
                continue;
            }
            if (indexArr[i] < size) {
                Sorts.insertSort(buckets[i], indexArr[i]);
            } else {
                Sorts.quickSort4(buckets[i], 0, indexArr[i] - 1);
            }
            for (int j = 0; j < indexArr[i]; j++) {
                arr[k++] = buckets[i][j];
            }
        }


    }

    /**
     * 数组扩容
     *
     * @param buckets
     * @param bucketIndex
     */
    private static void resizeCapacity(int[][] buckets, int bucketIndex) {
        int[] tempArr = buckets[bucketIndex];
        int[] newArr = new int[tempArr.length * 2];
        for (int j = 0; j < tempArr.length; j++) {
            newArr[j] = tempArr[j];
        }
        buckets[bucketIndex] = newArr;
    }

    public static void main(String[] args) {
        int arr[] = {22, 5, 11, 41, 45, 26, 29, 10, 7, 8, 8, 8, 8, 8, 8, 30, 27, 42, 43, 40};

        bucketSort(arr, 10, 5);
//        Sorts.insertSort(arr,5);
        System.out.println(Arrays.toString(arr));
    }
}
