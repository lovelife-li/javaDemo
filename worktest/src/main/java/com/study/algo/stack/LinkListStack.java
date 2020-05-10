package com.study.algo.stack;

import com.study.algo.Node;

/**
 * @author ldb
 * @date 2020/5/10
 * @dsscription 链表栈
 */
public class LinkListStack {

    private Node top;

    // 元素个数
    public int count;


    // 入栈,可以扩容
    public void push(int value) {
        if (top != null) {
            Node node = new Node(value);
            node.next = top;
            top = node;
        } else {
            top = new Node(value);
        }
        ++count;

    }

    // 出栈
    public int pop() {
        Node tmp;
        if (top == null) {
            return -1;
        } else {
            tmp = top;
            top = top.next;
            --count;
        }

        return tmp.data;
    }


    public static void main(String[] args) {
        LinkListStack stack = new LinkListStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.top.print();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
