package com.study.algo.array;

/**
 * @author ldb
 * @date 2020/05/08
 * @description 数组操作
 */
public class Array {

    public int data[];

    // 定义数组长度
    private int n;

    // 实际元素个数
    private int count;

    public Array(int capacity) {
        data = new int[capacity];
        n = capacity;
    }

    // 根据索引，找到数据中的元素并返回
    public int find(int index) {
        return data[index];
    }

    // 插入元素
    public boolean insert(int index, int value) {
        if (index < 0 || index > count) {
            System.out.println("越界，插入不合法");
            return false;
        }
        // 判断数组是否已满,扩容
        if (count == n) {
            int[] newArray = new int[n << 1];
            for (int i = 0; i < data.length; i++) {
                newArray[i] = data[i];
            }
            n = n << 1;
            data = newArray;
        }
        // 插入位置往后移
        for (int i = count; i > index; --i) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        ++count;
        return true;
    }

    // 根据索引，删除数组中元素
    public boolean delete(int index) {
        if (index < 0 || index >= count) {
            System.out.println("越界，删除不合法");
            return false;
        }
        // 删除位置后面元素前移
        for (int i = index + 1; i < count; i++) {
            data[i - 1] = data[i];
        }
        --count;
        return true;
    }

    public void printAll() {
        for (int i = 0; i < count; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();

    }

    public static void main(String[] args) {
        Array array = new Array(5);
        array.insert(0, 1);
        array.insert(1, 2);
        array.insert(2, 3);
        array.insert(3, 4);
        array.insert(4, 5);
        array.printAll();
        array.delete(2);
        array.printAll();

        array.insert(4, 4);
        array.printAll();


    }
}
