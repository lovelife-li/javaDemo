package com.study.algo.stack;

import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Arrays;

/**
 * @author ldb
 * @date 2020/5/10
 * @dsscription 数组栈
 */
public class ArrayStack {
    private int[] data;

    // 栈元素个数
    public int size;

    // 栈总大小
    public int n;

    public ArrayStack(int n) {
        this.n = n;
        data = new int[n];
        size = 0;
    }


    // 入栈,可以扩容
    public void push(int value) {
        if (size < n) {
            data[size] = value;
            ++size;
        } else {
            int[] newArray = new int[n * 2];
            for (int i = 0; i < data.length; i++) {
                newArray[i] = data[i];
            }
            data = newArray;
            n = n << 1;
            data[size] = value;
            ++size;
        }

    }

    // 出栈
    public int pop() {
        if (size == 0) {
            return -1;
        }
        int val = data[--size];
        return val;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            if (i==size-1){
                System.out.println(data[i]);
            }else {
                System.out.print(data[i] + ",");
            }

        }

    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(2);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.print();
        System.out.println(stack.pop());
        stack.print();
    }
}
