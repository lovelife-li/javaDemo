package com.study.algo.tree;

import java.util.Arrays;
import java.util.Random;

/**
 * 堆是要一颗完全叉树
 * 这里做一个大顶堆,数组第一个元素为null.
 * 如果节点的下标是 i，那左子节点的下标就是 2∗i，右子节点的下标就是 2∗i+1，父节点的下标就是 i/2。
 *
 * @author ldb
 * @date 2020/08/21
 */
public class Heap {

    // 数组,第一个位置不存数据
    private int[] a;
    // 堆大小
    private int n;
    // 堆中已经存储的数据个数
    private int count;

    public Heap(int n) {
        a = new int[n + 1];
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
        a[++count] = data;
        int i = count;
        while (i > 1 && a[i] > a[i >> 1]) {
            swap(a, i, i >> 1);
            i >>= 1;
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
     * 使用场景：删除堆顶元素，构建堆。
     *
     * @param a 数组
     * @param n 堆大小
     * @param i 从哪个下标开始堆化
     */
    private void heapify(int[] a, int n, int i) {
        if (n <= 1) {
            return;
        }
        for (; ; ) {
            int pos = i;
            // 与左右子节点比较,找到最大值
            if (i * 2 <= n && a[i] < a[i * 2]) {
                pos = i * 2;
            }
            if (i * 2 + 1 <= n && a[pos] < a[i * 2 + 1]) {
                pos = i * 2 + 1;
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
     * @return 返回堆顶元素的值
     */
    public int deleteMax() {
        if (count == 0) {
            return 0;
        }
        int res = a[1];
        a[1] = a[count--];
        heapify(a, count, 1);


        return res;
    }

    /**
     * 创建堆
     * 思路1：调用堆插入方法，依次插入数据
     * 思路2：找到第一个非叶子节点，从后往前处理数据，每个数据都是从上往下堆化。
     *
     * @param a
     * @param n 堆中元数个数
     */
    public void createHeap(int[] a, int n) {
        if (n > a.length - 1) {
            return;
        }
        // 怎么找第一个非叶子节点？ 规律：n/2是第一个非叶子节点。
        // 其实可以看出找最后叶子节点的父节点。最后节点下标：n
        if (n > 1) {
            for (int i = n >> 1; i >= 1; --i) {
                heapify(a, n, i);
            }
        }


    }

    /**
     * 排序
     * 思路：
     * 1，建堆
     * 2，将第一个元素和最后一个元素交换
     * 注意这里要求，数据第一个元素是空的。
     * 大顶堆排序，从小到大排，是原地排序。如果从大到小排就不是原地排序。
     * 小顶堆排序，从大到小排，是原地排序。如果从小到大排就不是原地排序。
     *
     * @param a
     * @param n n表示堆元素个数
     */
    public void sort(int[] a, int n) {
        if (n > a.length - 1) {
            return;
        }
        createHeap(a, n);
        // i表示堆元素最大下标
        int i = n;
        while (i > 1) {
            swap(a, 1, i);
            i--;
            // 减少一个元素堆化
            heapify(a, i, 1);

        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap(20);
        test1(heap);

    }


    /**
     * 测试排序
     */
    public static void test1(Heap heap) {
        Random random = new Random();
        int size = 10;
        int arr[] = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            arr[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(arr));
        heap.sort(arr, size);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 测试插入
     */
    public static void test2(Heap heap) {
        int arr[] = {33, 17, 21, 16, 13, 15, 9, 5, 6, 7, 8, 1, 2};
        for (int i = 0; i < arr.length; i++) {
            heap.a[i + 1] = arr[i];
        }
        heap.count = arr.length;
        System.out.println(Arrays.toString(heap.a));
        System.out.println(heap.count);
//        heap.deleteMax();
//        System.out.println(Arrays.toString(heap.a));
        heap.insert(8);
        System.out.println(Arrays.toString(heap.a));

        System.out.println();
        for (; heap.count > 0; ) {

            System.out.println(heap.deleteMax());
            System.out.println(Arrays.toString(heap.a));
        }

    }


}
