package com.study.algo;

/**
 * @author ldb
 * @date 2020/5/10
 * @dsscription
 */
public class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

    public void print() {
        Node cur = this;
        while (cur != null) {
            System.out.print(cur.data + "->");
            cur = cur.next;
        }
        System.out.println("null");
    }
}
