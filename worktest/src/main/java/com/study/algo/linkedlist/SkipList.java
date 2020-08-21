package com.study.algo.linkedlist;


import org.springframework.util.StopWatch;

import java.util.Random;

/**
 * 跳表实现
 *
 * @author ldb
 * @date 2020/08/20
 */
public class SkipList<T> {

    private static final int MAX_LEVEL = 32;
    private int levelCount = 1;
    // 表头
    private Node<T> head = new Node<>(MAX_LEVEL, null);

    // 生成randomLevel用到的概率值
    private double p;
    // 论文里给出的最佳概率值
    private static final double OPTIMAL_P = 0.25;

    public SkipList(double p) {
        this.p = p;
    }

    public SkipList() {
        this(OPTIMAL_P);
    }


    final static class Node<T> {
        T value;
        Node[] next;
        // 统计当前节点在0到maxLevel-1层有数据
        private int maxLevel = 0;

        public Node(int maxLevel, T value) {
            next = new Node[maxLevel];
            this.value = value;
            this.maxLevel = maxLevel;
        }

        @Override
        public String toString() {
            return "{data:" + value + ",maxLevel:" + maxLevel + "}";
        }
    }

    private int randomLevel() {
        int level = 1;
        while (level < MAX_LEVEL && Math.random() <= p) {
            level++;
        }
        return level;
    }

    public Node find(T val) {
        int hash = val.hashCode();
        Node p = head;
        // 从左往右找到最后一个小于val的节点
        // 从上到下找到叶子节点
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.next[i] != null && hash > p.next[i].value.hashCode()) {
                p = p.next[i];
            }
        }
        // 第0层比较
        if (p.next[0] != null && p.next[0].value.hashCode() == hash) {
            return p.next[0];
        } else {
            return null;
        }

    }

    public boolean insert(T val) {
        int hash = val.hashCode();
        // 得到插入节点放在哪几层上
        int level = randomLevel();
        // 每次只增加一层，如果条件满足
        if (level > levelCount) {
            level = ++levelCount;
        }
        Node<T> newNode = new Node<>(level, val);
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.next[i] != null && hash > p.next[i].value.hashCode()) {
                p = p.next[i];
            }
            // 随机level <  levelCount ，而只更新 0 到level-1 所以加上判断
            if (level > i) {
                if (p.next[i] == null) {
                    p.next[i] = newNode;
                } else {
                    if (p.next[i].value.hashCode() == hash) {
                        // 数据已经存在
                        return false;
                    } else {
                        Node next = p.next[i];
                        p.next[i] = newNode;
                        newNode.next[i] = next;
                    }

                }
            }
        }
        return true;
    }

    public boolean delete(T val) {
        int hash = val.hashCode();
        // 找到删除元素前一个位置
        Node update[] = new Node[levelCount];
        for (int i = 0; i < levelCount; i++) {
            update[i] = head;
        }
        Node p = head;
        boolean flag = false;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.next[i] != null && hash > p.next[i].value.hashCode()) {
                p = p.next[i];
            }
            if (p.next[i] != null && p.next[i].value.hashCode() == hash) {
                p.next[i] = p.next[i].next[i];
                flag = true;
            }
        }
        return flag;
    }

    public void print() {
        Node p = head;
        while (p.next[0] != null) {
            System.out.print(p.next[0] + " ");
            p = p.next[0];
        }
    }

    /**
     * 打印所有数据
     */
    public void printAll_beautiful() {
        Node p = head;
        Node[] c = p.next;
        Node[] d = c;
        int maxLevel = c.length;
        for (int i = maxLevel - 1; i >= 0; i--) {
            do {
                System.out.print((d[i] != null ? d[i].value : null) + ":" + i + "-------");
            } while (d[i] != null && (d = d[i].next)[i] != null);
            System.out.println();
            d = c;
        }
    }

    public static void main(String[] args) {
        SkipList<Integer> list = new SkipList<>();

//        test1(list);


    }

    /**
     * 插入100万数据，找随机数10万次,打印查找次数
     * @param list
     */
    public static void test1(SkipList<Integer> list) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int size = 1000000;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.insert(i);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
//        System.out.println(stopWatch.getTotalTimeMillis());
//        System.out.println(stopWatch.getLastTaskName());
//        System.out.println(stopWatch.getLastTaskInfo());
//        System.out.println(stopWatch.getTaskCount());



        for (int i = 0; i < 100000; i++) {
            Node node = list.find2(random.nextInt(size));
            System.out.println("node:" + node.value + ",num:" + num + ",level:" + node.maxLevel);
            num = 0;
        }

    }

    public static void test2(SkipList<Integer> list) {
        int arr[] = {1, 2, 3, 4, 23, 89, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < arr.length; i++) {
            list.insert(arr[i]);
        }


        System.out.println(list.delete(89));
        list.printAll_beautiful();
        System.out.println(list.delete(11));
    }

    // 统计查找次数
    static int num = 0;

    public Node find2(T val) {
        int hash = val.hashCode();
        Node p = head;
        // 从左往右找到最后一个小于val的节点
        // 从上到下找到叶子节点
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.next[i] != null && hash > p.next[i].value.hashCode()) {
                p = p.next[i];
                ++num;
            }
            ++num;
        }
        // 第0层比较
        if (p.next[0] != null && p.next[0].value.hashCode() == hash) {
            return p.next[0];
        } else {
            return null;
        }

    }
}
