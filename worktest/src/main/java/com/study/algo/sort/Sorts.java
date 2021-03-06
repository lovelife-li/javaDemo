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
        if (n > arr.length) {
            n = arr.length;
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
        if (n > arr.length) {
            n = arr.length;
        }
        int tmp;
        for (int i = 0; i < n - 1; i++) {
            boolean flag = true;
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
        if (n > arr.length) {
            n = arr.length;
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
        if (n > arr.length) {
            n = arr.length;
        }
        for (int i = 0; i < n - 1; i++) {
            int index = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            int tmp = arr[index];
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

        if (left >= right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge2(arr, left, mid, right);
    }

    private static void merge2(int[] arr, int left, int q, int right) {
        int[] l = new int[q - left + 2];
        int[] r = new int[right - q + 1]; // 不包含q
        for (int i = 0; i <= q - left; i++) {
            l[i] = arr[i + left];
        }

        for (int j = 0; j < right - q; j++) {
            r[j] = arr[j + q + 1];
        }
        l[q - left + 1] = Integer.MAX_VALUE;
        r[right - q] = Integer.MAX_VALUE;
        int k = left;
        int i = 0;
        int j = 0;
        while (k <= right) {
            if (l[i] <= r[j]) {
                arr[k++] = l[i++];
            } else {
                arr[k++] = r[j++];
            }
        }
    }

    private static void merge(int[] arr, int left, int q, int right) {
        int[] tmp = new int[right - left + 1];
        int i = left;
        int j = q + 1;
        int k = 0;
        while (i <= q && j <= right) {
            if (arr[i] > arr[j]) {
                tmp[k++] = arr[j++];
            } else {
                tmp[k++] = arr[i++];
            }
        }
        int start = i;
        int end = q;
        if (j <= right) {
            start = j;
            end = right;
        }
        for (int l = start; l <= end; l++) {
            tmp[k++] = arr[l];
        }
        for (int m = 0; m <= right - left; m++) {
            arr[m + left] = tmp[m];
        }


    }

    /**
     * 快速排序
     *
     * @param arr
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int q = partition3(arr, left, right);
        quickSort(arr, left, q - 1);
        quickSort(arr, q + 1, right);
    }


    /**
     * 从两边找,知道l==r 时，arr[l]大于pivot
     */
    private static int partition1(int[] arr, int left, int right) {
        // 取最后一个数分区
        int pivot = arr[right];
        int l = left - 1;
        int r = right;
        int tmp;
        while (l < r) {
            // 必须要比较到l+1;
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
     * 从一边找,分为有序和无序两部分。
     * {2, 1, 5, 6, 8, 4, 7, -1, 3, 13, 5};
     */
    private static int partition2(int[] arr, int left, int right) {
        int i = left;
        int pivot = arr[right];
        int tmp;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                if (i == j) {
                    i++;
                } else {
                    tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                    i++;
                }
            }
        }
        tmp = pivot;
        arr[right] = arr[i];
        arr[i] = tmp;
        return i;
    }

    /**
     * 从一边找,分为有序和无序两部分。随机pivot
     * {2, 1, 5, 6, 8, 4, 7, -1, 3, 13, 5};
     */
    private static int partition3(int[] arr, int left, int right) {
        int i = left;
        int mid = left + ((right - left) >> 1);
        int pivot = arr[mid];
        int tmp;
        // 交换到最右边
        tmp = pivot;
        arr[mid] = arr[right];
        arr[right] = tmp;
        for (int j = left; j < right; j++) {
            if (arr[j] < arr[right]) {
                if (i == j) {
                    i++;
                } else {
                    tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                    i++;
                }
            }
        }
        tmp = arr[right];
        arr[right] = arr[i];
        arr[i] = tmp;
        return i;
    }

    /**
     * 三向切分快速排序
     * 三向切分快速排序的基本思想，用i，j，k三个将数组切分成四部分
     * a[L, i-1]表示小于pivot的部分，
     * a[i, k-1]表示等于pivot的部分， i表示等于pivot第一个元素
     * a[j+1]表示大于pivot的部分，
     * 而a[k,j]表示未判定的元素（即不知道比pivot大，还是比中轴元素小）。我们要注意a[i]始终位于等于
     * pivot部分的第一个元素，a[i]的左边是小于pivot的部分。
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static void quickSort3(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int k = left + 1;
        int j = right;
        int pivot = arr[i];
        int tmp;
        while (k <= j) {
            if (arr[k] == pivot) {
                k++;
            } else if (arr[k] < pivot) {
                tmp = arr[k];
                arr[k] = arr[i];
                arr[i] = tmp;
                k++;
                i++;
            } else {
                if (arr[j] < pivot) {
                    tmp = arr[j];
                    arr[j] = arr[k];
                    arr[k] = arr[i];
                    arr[i] = tmp;
                    i++;
                    k++;
                    j--;
                } else if (arr[j] == pivot) {
                    tmp = arr[j];
                    arr[j] = arr[k];
                    arr[k] = tmp;
                    k++;
                    j--;
                } else {
                    j--;
                }
            }
        }

        quickSort3(arr, left, i - 1);
        quickSort3(arr, j + 1, right);
    }

    /**
     * 双轴快速排序
     * 双轴快速排序算法使用两个轴，通常选取最左边的元素作为pivot1和最右边的元素作pivot2。
     * 首先要比较这两个轴的大小，如果pivot1 > pivot2，则交换最左边的元素和最右边的元素，
     * 已保证pivot1 <=  pivot2。双轴快速排序同样使用i，j，k三个变量将数组分成四部分
     * <p>
     * A[L+1, i]是小于pivot1的部分，
     * A[i+1, k-1]是大于等于pivot1且小于等于pivot2的部分，
     * A[j, R]是大于pivot2的部分，
     * 而A[k, j-1]是未知部分。
     * 和三向切分的快速排序算法一样，初始化i = L，k = L+1，j=R，k自左向右扫描直到k与j相交为止（k == j）。
     * 我们扫描的目的就是逐个减少未知元素，并将每个元素按照和pivot1和pivot2的大小关系放到不同的区间上去。
     * i 表示小于pivot1的最后一个元素
     * j 表示大于pivot2的第一个元素
     */
    public static void quickSort4(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int l = left;
        int k = l + 1;
        int r = right;
        int tmp;
        if (arr[l] > arr[r]) {
            tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
        int pivot1 = arr[l];
        int pivot2 = arr[r];
        while (k < r) {
            if (arr[k] < pivot1) {
                l++;
                if (l != k) {
                    tmp = arr[k];
                    arr[k] = arr[l];
                    arr[l] = tmp;
                }
                k++;
            } else if (arr[k] > pivot2) {
                --r;
                if (arr[r] < pivot1) {
                    ++l;
                    tmp = arr[k];
                    arr[k] = arr[l];
                    arr[l] = arr[r];
                    arr[r] = tmp;
                    ++k;

                } else if (arr[r] > pivot2) {
                } else {
                    tmp = arr[k];
                    arr[k] = arr[r];
                    arr[r] = tmp;
                    ++k;
                }
            } else {
                k++;
            }
        }
        arr[left] = arr[l];
        arr[l] = pivot1;
        arr[right] = arr[r];
        arr[r] = pivot2;

        quickSort4(arr, left, l - 1);
        quickSort4(arr, l + 1, r - 1);
        quickSort4(arr, r + 1, right);

    }

    /**
     * O(n)  时间复杂度内求无序数组中的第 K  大元素。比如， 4 ， 2 ， 5 ， 12 ， 3  这样一组数据，第 3  大元素就是 4 。
     *
     * @param arr
     */
    public static int sort(int[] arr, int l, int r, int k) {
        if (l >= r) {
            return -1;
        }
        int p = partition2(arr, l, r);
        if (k == p + 1) {
            return arr[p];
        } else if (k > p + 1) {
            return sort(arr, p + 1, r, k);
        } else {
            return sort(arr, l, p - 1, k);
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 1, 5, 6, 8, 4, 7, -1, 3, 13, 9, 8, 12, 10, 4};
//        bubbleSort(arr,6);
//        bubbleSort2(arr,6);
//        insertSort(arr);
//        insertSort(arr,6);
//        selectSort(arr, 100);
//        mergeSort(arr, 0, arr.length - 1);
        quickSort4(arr, 0, arr.length - 1);
        System.out.println(sort(arr, 0, arr.length - 1, 1));

        Arrays.sort(arr);// 双轴排序

        int[] arr2 = {1, 2, 9, 5, 11, 8};
        int i = partition1(arr2, 0, arr2.length - 1);
        System.out.println(i);
        print(arr2);
        print(arr);



    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


}
