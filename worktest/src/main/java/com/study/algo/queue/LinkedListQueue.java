package com.study.algo.queue;

import com.study.algo.Node;

/**
 * @author ldb
 * @date 2020/5/10
 * @dsscription 链表实现队列
 */
public class LinkedListQueue {

    Node head;

    Node tail;

    public void enqueue(Node node) {
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }

    }

    public Node dequeue() {
        Node tmp = head; ;
        if (head != null) {
            head = head.next;
        }
        if (head == null){
            tail = null;
        }
        return tmp;
    }
}
