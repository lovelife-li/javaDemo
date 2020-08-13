package com.study.algo.queue;

import com.study.algo.array.Array;

/**
 * @author ldb
 * @date 2020/5/10
 * @dsscription 数组实现队列
 */
public class ArrayQueue {

    private int[] arr;
    // 容量
    public int n;

    // 元素个数
    public int count;

    public int head = 0;

    // 最后一个元素后面一个位置
    public int tail = 0;

    public ArrayQueue(int n) {
        this.n = n;
        arr = new int[n];
        count = 0;
    }

    // 入队，可搬移数据
    public boolean enqueue(int val) {
        // 队列满了
        if (tail == n) {
            if (head == 0) {
                // 无法搬移
                return false;
            } else {
                for (int i = head; i < tail; i++) {
                    arr[i - head] = arr[i];
                }
                tail = tail - head;
                head = 0;
            }
        }
        arr[tail++] = val;
        ++count;
        return true;
    }

    // 出队
    public int dequeue() {
        if (head == tail) {
            return -1;
        } else {
            --count;
            return arr[head++];
        }
    }

    public void print() {
        for (int i = head; i < tail; i++) {
            if (i == tail - 1) {
                System.out.println(arr[i]);
            } else {
                System.out.print(arr[i] + ",");
            }

        }

    }

    public static void main(String[] args) {
        ArrayQueue q = new ArrayQueue(6);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.print();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.print();
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(8);
        q.print();

    }
}
