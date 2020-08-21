package com.study.algo.tree;

import java.util.Arrays;
import java.util.Random;

/**
 * 堆是要一颗完全叉树
 * 这里做一个大顶堆
 * 如果节点的下标是 i，那左子节点的下标就是 2∗i+1，右子节点的下标就是 2∗i+2，父节点的下标就是 (i−1)/2。
 *
 * @author ldb
 * @date 2020/08/21
 */
public class Heap2 {

    // 数组,第一个位置不存数据
    private int[] a;
    // 堆大小
    private int n;
    // 堆中已经存储的数据个数
    private int count;

    public Heap2(int n) {
        a = new int[n];
        this.n = n;
        this.count = 0;
    }

    /**
     * 堆尾插入,从下往上堆化
     *
     * @param data
     * @return
     */
    public boolean insert(int data) {
        if (count >= n) {
            // 堆满，不能插入
            return false;
        }
        a[count] = data;
        int i = count;
        while (a[(i - 1) / 2] > 0 && a[i] > a[(i - 1) / 2]) {
            swap(a, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
        return true;
    }

    /**
     * 交换数据
     *
     * @param a
     * @param i
     * @param j
     */
    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    /**
     * 从指定下标开始堆化
     *
     * @param a 数组
     * @param n 堆大小
     * @param i 从哪个下标开始堆化
     */
    private void heapify(int[] a, int n, int i) {
        for (; ; ) {
            int pos = i;
            // 与左右子节点比较,找到最大值
            if (i * 2 + 1 < n && a[i] < a[i * 2 + 1]) {
                pos = i * 2 + 1;
            }
            if (i * 2 + 2 < n && a[pos] < a[i * 2 + 2]) {
                pos = i * 2 + 2;
            }
            if (i == pos) {
                break;
            }
            swap(a, i, pos);
            i = pos;
        }
    }


    /**
     * 只能删除堆顶元素
     * 思路：用堆尾元素替换，删除用堆尾元素，从上到下堆化
     *
     * @return
     */
    public boolean deleteMax() {
        if (count == 0) {
            return false;
        }
        a[0] = a[--count];
        heapify(a, n, 0);
        return true;
    }

    /**
     * 创建堆
     * 思路1：调用堆插入方法，依次插入数据
     * 思路2：找到第一个非叶子节点，从后往前处理数据，每个数据都是从上往下堆化。
     *
     * @param a
     * @param n 堆中元数个数
     */
    public boolean createHeap(int[] a, int n) {
        if (n > a.length) {
            return false;
        }
        // 怎么找第一个非叶子节点？ 规律：n/2是第一个非叶子节点。
        int i = (n - 2) / 2;
        for (; i >= 0; --i) {
            heapify(a, n, i);
        }
        return true;
    }

    /**
     * 排序
     * 思路：
     * 1，建堆
     * 2，将第一个元素和最后一个元素交换
     *
     * @param a
     * @param n n表示数组元素个数
     */
    public void sort(int[] a, int n) {

        if (!createHeap(a, n)) {
            return;
        }
        System.out.println(Arrays.toString(a));
        int i = --n;
        while (i > 0) {
            swap(a, 0, i);
            // 减少一个元素堆化
            heapify(a, i, 0);
            i--;

        }
    }

    public static void main(String[] args) {
        Heap2 heap = new Heap2(20);
        test1(heap);
    }

    /**
     * 测试排序
     */
    public static void test1(Heap2 heap) {
        Random random = new Random();
        int size = 20;
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(arr));
        heap.sort(arr, size);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 测试插入
     */
    public static void test2(Heap2 heap) {
        int arr[] = {33, 17, 21, 16, 13, 15, 9, 5, 6, 7, 8, 1, 2};
        for (int i = 0; i < arr.length; i++) {
            heap.a[i] = arr[i];
        }
        heap.count = arr.length;
        System.out.println(Arrays.toString(heap.a));
        System.out.println(heap.count);
        heap.deleteMax();
        System.out.println(Arrays.toString(heap.a));


    }


}
