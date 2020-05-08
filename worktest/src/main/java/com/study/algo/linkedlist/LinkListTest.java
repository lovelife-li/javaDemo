package com.study.algo.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ldb
 * @date 2019-10-08 9:28
 * 1) 单链表反转
 * 2) 链表中环的检测
 * 3) 两个有序的链表合并
 * 4) 删除链表倒数第n个结点
 * 5) 求链表的中间结点
 */
public class LinkListTest {


    // 单链表反转
    public static Node reverseLinkedList(Node head) {
        Node cur = head;
        Node tmp;
        Node prev = null;
        while (cur != null) {
            tmp = cur;
            cur = cur.next;
            tmp.next = prev;
            prev = tmp;

        }
        return prev;
    }


    @Data
    @AllArgsConstructor
    public static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    // 创建单链表（无环）
    public static Node createLinkedList(int[] arr) {
        if (arr == null) {
            return null;
        }
        Node head = new Node(-1);
        Node cur = head;
        for (int i = 0; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
            cur = cur.next;
        }
        return head.next;
    }

    // 创建单链表（有环）
    public static Node createLinkedList2(int[] arr) {

        if (arr == null) {
            return null;
        }
        Node head = new Node(-1);
        Node cur = head;
        for (int i = 0; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
            cur = cur.next;
        }
        cur.next = head.next;
        return head.next;
    }

    public static void print(Node head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    // 检测环
    public static boolean checkCircle(Node list) {
        return false;
    }


    // 删除倒数第K个结点
    public static Node deleteLastKth(Node list, int k) {
        if (list == null) {
            return null;
        }
        Node cur = list;
        int i = 0;
        while (cur != null) {
            ++i;
            cur = cur.next;

        }
        cur = list;
        if (i - k == 1) {
            cur.next = cur.next.next;
            return list;
        }
        if (i - k < 1) {
            return list.next;
        }
        while (i - k > 1) {
            cur = cur.next;
            --i;
        }
        cur.next = cur.next.next;

        return list;
    }

    // 有序链表合并
    public static Node mergeTwoLists(Node l1, Node l2) {

        return null;
    }

    // 求链表的中间结点
    public static Node getMiddleNode(Node head) {
        return null;
    }

    public static void main(String[] args) {
        Node head = createLinkedList(new int[]{1, 2, 3, 4, 5});
        print(head);
        Node p = reverseLinkedList(head);
        print(p);
        print(deleteLastKth(p, 5));

//        System.out.println(getMiddleNode(head).value);
//        Node head1 = createLinkedList(new int[]{1});
//        Node head2 = createLinkedList(new int[]{1, 2});
//        Node head3 = createLinkedList(new int[]{1, 2, 3});
//        Node head4 = createLinkedList(new int[]{1, 2, 3, 4});
//        Node head5 = createLinkedList2(new int[]{1, 2, 3, 4, 5});
//        print(deleteLastKth(head1, 2));
//        print(deleteLastKth(head2, 2));
//        print(deleteLastKth(head3, 2));
//        System.out.println(checkCircle(head5));
//        System.out.println("--------------");
//        print(mergeTwoLists(head4, head3));
    }
}
