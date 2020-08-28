package com.study.algo.practice;

/**
 * 求数组有序程度
 * 利用归并，拆成两个数组,假设数组1有序度为K1,数组2有序度为K2,数组1和数组2有序度为K3
 * 有序度=K1+K2+K3
 *
 * @author ldb
 * @date 2020/08/25
 */
public class OrderDegree {

    int num = 0;

    public void getOrderDegree(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int m = left + ((right - left) >> 1);
        getOrderDegree(a, left, m);
        getOrderDegree(a, m + 1, right);
        merge(a, left, m, right);
    }

    private void merge(int[] a, int left, int m, int right) {
        int[] tmp = new int[right - left + 1];
        int k = 0;
        int l = left;
        int r = m + 1;
        while (l <= m && r <= right) {
            if (a[l] < a[r]) {
                tmp[k++] = a[l++];
                num += right - r + 1;
            } else {
                tmp[k++] = a[r++];
            }
        }
        int start = l;
        int end = m;
        if (r <= right) {
            start = r;
            end = right;
        }
        for (int i = start; i <= end; i++) {
            tmp[k++] = a[i];
        }
        for (int i = 0; i <= right - left; i++) {
            a[i + left] = tmp[i];
        }
    }

    public static void main(String[] args) {
        OrderDegree d = new OrderDegree();
        int[] a = {1, 5, 6, 2, 3, 4};
        d.getOrderDegree(a, 0, a.length - 1);
        System.out.println(d.num);
    }
}
