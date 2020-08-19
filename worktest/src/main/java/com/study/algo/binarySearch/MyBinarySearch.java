package com.study.algo.binarySearch;

import lombok.val;

/**
 * todo
 *
 * @author ldb
 * @date 2020/08/06
 */
public class MyBinarySearch {

    // 基本二分查找
    public static int search(int arr[], int n, int val) {
        int low = 0;
        int high = n - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] == val) {
                return mid;
            } else if (arr[mid] > val) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;

    }

    // 基本二分查找 递归
    public static int search1(int arr[], int low, int high, int val) {
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) >> 1);
        if (arr[mid] == val) {
            return mid;
        } else if (arr[mid] > val) {
            return search1(arr, low, mid - 1, val);
        } else {
            return search1(arr, mid + 1, high, val);
        }

    }

    // 变体一：查找第一个值等于给定值的元素
    public static int search2(int arr[], int n, int val) {
        int low = 0;
        int high = n - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] == val) {
                if (mid == 0 || arr[mid - 1] != val) {
                    return mid;
                } else {
                    high = mid - 1;
                }

            } else if (arr[mid] > val) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 变体二：查找最后一个值等于给定值的元素
     */
    public static int search3(int arr[], int n, int val) {
        int low = 0;
        int high = n - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] == val) {
                if (mid == high || arr[mid + 1] != val) {
                    return mid;
                } else {
                    low = mid + 1;
                }

            } else if (arr[mid] > val) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 变体三：查找第一个大于等于给定值的元素
     */
    public static int search4(int arr[], int n, int val) {
        int low = 0;
        int high = n - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] >= val) {
                if (mid == 0 || arr[mid - 1] < val) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 变体四：查找最后一个小于等于给定值的元素
     */
    public static int search5(int arr[], int n, int val) {
        int low = 0;
        int high = n - 1;
        int mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] > val) {
                high = mid - 1;
            } else {
                if (mid == high || arr[mid + 1] > val) {
                    return mid;
                } else {
                    low = mid + 1;
                }

            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 4, 4, 5, 5, 6, 6, 7, 13, 24};
        System.out.println(search(arr, arr.length, 4));
        System.out.println(search1(arr, 0, arr.length - 1, 5));
        System.out.println("---------");
        System.out.println(search2(arr, arr.length, 4));
        System.out.println(search3(arr, arr.length, 4));
        System.out.println(search4(arr, arr.length, 6));
        System.out.println(search5(arr, arr.length, 6));
    }

}
