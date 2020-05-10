package com.study.algo.sort;

import java.util.Arrays;

/**
 * 冒泡，选择，插入，快速，归并
 *
 * @author ldb
 * @date 2019-10-08 16:09
 */
public class Sorts {

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }
        int tmp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 优化冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort2(int[] arr, int n) {
        if (n <= 1) {
            return;
        }
        int tmp;
        boolean flag = true;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {

        int val;
        for (int i = 1; i < arr.length; i++) {
            val = arr[i];
            int index = i - 1;
            while (index >= 0 && arr[index] > val) {
                arr[index + 1] = arr[index];
                --index;
            }
            arr[index + 1] = val;
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     * @param n   表示数组有用大小
     */
    public static void insertSort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; i++) {
            int val = arr[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (arr[j] > val) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = val;

        }
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    index = j;
                }
            }
            int tmp = min;
            arr[index] = arr[i];
            arr[i] = tmp;

        }

    }

    /**
     * 归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr, int left, int right) {


    }

    private static void merge2(int[] arr, int left, int q, int right) {

    }

    private static void merge(int[] arr, int left, int q, int right) {


    }

    /**
     * 快速排序
     *
     * @param arr
     */
    public static void quickSort(int[] arr, int left, int right) {

    }

    private static int partition(int[] arr, int left, int right) {
        return 0;
    }

    private static int partition2(int[] arr, int left, int right) {
        return 0;
    }

    /**
     * 三向切分快速排序
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static void quickSort3(int[] arr, int left, int right) {

    }

    /**
     * 双轴快速排序
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort4(int[] arr, int left, int right) {

    }

    /**
     * O(n)  时间复杂度内求无序数组中的第 K  大元素。比如， 4 ， 2 ， 5 ， 12 ， 3  这样一组数据，第 3  大元素就是 4 。
     *
     * @param arr
     */
    public static int sort(int[] arr, int l, int r, int k) {
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 8, 4, 12, 11, 13, 15, 7, 9, 0, -1};
//        bubbleSort(arr,6);
//        bubbleSort2(arr,6);
//        insertSort(arr);
//        insertSort(arr,6);
        selectSort(arr,6);
//        mergeSort(arr, 0, arr.length - 1);
//        quickSort4(arr, 0, arr.length - 1);

//        Arrays.sort(arr);// 双轴排序
        print(arr);

    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


}
