package com.study.algo.sort;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * todo
 *
 * @author ldb
 * @date 2020/08/05
 */
public class MySort {

    public static void bubbleSort(int arr[], int n) {
        for (int i = 0; i < n - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
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

    public static void insertSort(int arr[], int n) {
        for (int i = 1; i < n; i++) {
            int index = i - 1;
            int val = arr[i];
            while (index >= 0 && val < arr[index]) {
                arr[index + 1] = arr[index];
                --index;
            }
            arr[index + 1] = val;
        }
    }

    public static void insertSort2(int arr[], int n) {
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            int val = arr[i];
            for (; j >= 0; --j) {
                if (val < arr[j]) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = val;
        }
    }

    public static void selectSort(int arr[], int n) {
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
    }

    public static void mergeSort(int arr[], int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge2(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int k = 0;
        int[] tmp = new int[right - left + 1];
        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                tmp[k++] = arr[l++];
            } else {
                tmp[k++] = arr[r++];
            }
        }
        int start = l;
        int end = mid;
        if (r <= right) {
            start = r;
            end = right;
        }
        for (int i = start; i <= end; i++) {
            tmp[k++] = arr[i];
        }
        for (int i = left; i <= right; i++) {
            arr[i] = tmp[i - left];
        }
    }

    private static void merge2(int[] arr, int left, int mid, int right) {
        int[] arrLeft = new int[mid - left + 2];
        int[] arrRight = new int[right - mid + 1];
        for (int i = 0; i < arrLeft.length - 1; i++) {
            arrLeft[i] = arr[left + i];
        }
        for (int i = 0; i < arrRight.length - 1; i++) {
            arrRight[i] = arr[mid + 1 + i];
        }
        arrLeft[arrLeft.length - 1] = Integer.MAX_VALUE;
        arrRight[arrRight.length - 1] = Integer.MAX_VALUE;
        int p = 0;
        int q = 0;
        int k = left;
        while (k <= right) {
            if (arrLeft[p] < arrRight[q]) {
                arr[k++] = arrLeft[p++];
            } else {
                arr[k++] = arrRight[q++];
            }
        }

    }

    public static void quickSort(int arr[], int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partion1(arr, left, right);
        quickSort(arr, left, p - 1);
        quickSort(arr, p + 1, right);
    }

    private static int partion(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        int j = left;
        int tmp;
        for (; j < right; j++) {
            if (arr[j] < pivot) {
                if (i != j) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
                ++i;
            }
        }
        tmp = arr[i];
        arr[i] = pivot;
        arr[right] = tmp;
        return i;
    }

    private static int partion1(int[] arr, int left, int right) {
        int l = left - 1;
        int r = right;
        int pivot = arr[right];
        int tmp;
        while (l < r) {
            while (l < r && arr[++l] < pivot) ;
            while (l < r && arr[--r] > pivot) ;
            if (l != r) {
                tmp = arr[l];
                arr[l] = arr[r];
                arr[r] = tmp;
            }
        }
        tmp = arr[l];
        arr[l] = pivot;
        arr[right] = tmp;
        return l;
    }

    /**
     * 三向切分排序
     *
     * @param arr
     * @param left
     * @param right a[L,i-1] 小于pivot
     *              a[i,k-1] 等于pivot
     *              a[k,j] 待比较元素
     *              a[j+1,R] 大于pivot
     * @return
     */
    private static void quickSort2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int k = i + 1;
        int j = right;
        int pivot = arr[i];
        int tmp;
        while (k <= j) {
            if (arr[k] < pivot) {
                // 和i交换
                tmp = arr[i];
                arr[i] = arr[k];
                arr[k] = tmp;
                ++i;
                ++k;
            } else if (arr[k] == pivot) {
                ++k;
            } else {
                if (arr[j] > pivot) {
                    --j;
                } else if (arr[j] == pivot) {
                    // 和k 交换
                    tmp = arr[k];
                    arr[k] = arr[j];
                    arr[j] = tmp;
                    --j;
                    ++k;
                } else {
                    // j 和 i 交换
                    // k 和 j 交换
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = arr[k];
                    arr[k] = tmp;
                    ++i;
                    ++k;
                    --j;
                }
            }

        }

        quickSort2(arr, left, i - 1);
        quickSort2(arr, j + 1, right);


    }

    /**
     * 双轴排序
     * a[L,i] 小于pivot1
     * a[i+1,k-1] 大于等于pivot1 并且 小于等于pivot2
     * a[k,--j] 待比较元素
     * a[j,R] 大于等于pivot2
     */
    private static void quickSort3(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int k = i + 1;
        int j = right;
        int tmp;
        if (arr[i] > arr[j]) {
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        int pivot1 = arr[i];
        int pivot2 = arr[j];
        while (k < j) {
            if (arr[k] < pivot1) {
                ++i;
                // 交换
                if (i != k) {
                    tmp = arr[i];
                    arr[i] = arr[k];
                    arr[k] = tmp;
                }
                ++k;
            } else if (arr[k] >= pivot1 && arr[k] <= pivot2) {
                ++k;
            } else {
                --j;
                if (arr[j] > pivot2) {
                } else if (arr[j] >= pivot1 && arr[j] <= pivot2) {
                    // 和k 交换
                    tmp = arr[k];
                    arr[k] = arr[j];
                    arr[j] = tmp;
                    ++k;
                } else {
                    ++i;
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = arr[k];
                    arr[k] = tmp;
                    ++k;
                }
            }
        }
        // 交换pivot1 ,pivot2
        tmp = arr[i];
        arr[i] = pivot1;
        arr[left] = tmp;
        tmp = arr[j];
        arr[j] = pivot2;
        arr[right] = tmp;

        quickSort3(arr, left, i - 1);
        quickSort3(arr, i + 1, j - 1);
        quickSort3(arr, j + 1, right);


    }


    public static void main(String[] args) {
        int[] arr = {12, 1, 5, 6, 8, 4, 7, -1, 3, 13, 9, 8, 12, 10, 4, 24};
//        bubbleSort(arr,arr.length);
//        selectSort(arr,arr.length);
//        insertSort(arr,arr.length);
//        insertSort2(arr, arr.length);
//        mergeSort(arr, 0, arr.length - 1);
        quickSort3(arr, 0, arr.length - 1);

        print(arr);

        System.out.println(topK(arr, 0, arr.length - 1, 4));
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int topK(int[] arr, int left, int right, int k) {
        if (left >= right) {
            return -1;
        }
        int p = partion(arr, left, right);
        if (p + 1 == k) {
            return arr[p];
        } else if (p + 1 < k) {
            return topK(arr, p + 1, right, k);
        } else {
            return topK(arr, left, p - 1, k);
        }
    }

}
