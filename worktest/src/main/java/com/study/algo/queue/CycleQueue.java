package com.study.algo.queue;

/**
 * @author ldb
 * @date 2020/5/10
 * @dsscription 数组实现循环队列
 * 队列满的条件：
 * （tail+1)%n = head;
 */
public class CycleQueue {
    public int[] arr;
    // 容量
    public int n;

    // 元素个数
    public int count;

    public int head = 0;

    public int tail = 0;

    public CycleQueue(int n) {
        this.n = n;
        arr = new int[n];
        count = 0;
    }

    // 入队，可搬移数据
    public boolean enqueue(int val) {
        // 队列满了
        if ((tail + 1) % n == head) {
            return false;
        }

        arr[tail] = val;
        tail = (tail + 1) % n;
        ++count;
        return true;
    }

    // 出队
    public int dequeue() {
        if (head == tail) {
            return -1;
        } else {
            --count;
            int tmp = arr[head];
            head = (head + 1) % n;
            return tmp;
        }
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                System.out.println(arr[i]);
            } else {
                System.out.print(arr[i] + ",");
            }

        }

    }

    public static void main(String[] args) {
        CycleQueue q = new CycleQueue(6);
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
        q.enqueue(9);
        q.enqueue(10);
        q.print();

    }
}
