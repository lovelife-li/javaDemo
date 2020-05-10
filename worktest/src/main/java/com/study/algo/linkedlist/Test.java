package com.study.algo.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.annotation.meta.When;

/**
 * @author ldb
 * @date 2020/05/09
 * @description ??
 */
public class Test {

    // 单链表反转
    public static Node reverseLinkedList(Node head) {
        Node prev = null;
        Node cur = head;
        while (head != null) {
            cur = head;
            head = head.next;
            cur.next = prev;
            prev = cur;
        }
        return prev;
    }

    //链表中指定节点翻转
    public static Node inverseLinkList(Node head, Node p) {
        Node pre = null;
        Node r = head;
        Node next;
        while (r != p) {
            next = r.next;
            r.next = pre;
            pre = r;
            r = next;
        }
        r.next = pre;
        return r;

    }



    @AllArgsConstructor
    public static class Node {
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            Node cur = this;
            StringBuilder builder = new StringBuilder();
            while (cur != null) {
                builder.append(cur.value + "->");
                cur = cur.next;
            }
            builder.append("null");
            return builder.toString();
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
        cur.next = head.next.next;
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
        if (list == null) {
            return false;
        }
        Node p = list;
        Node q = list;
        while (q.next != null && q.next.next != null) {
            p = p.next;
            q = q.next.next;
            if (p == q) {
                return true;
            }
        }
        return false;
    }


    // 删除倒数第K个结点
    public static Node deleteLastKth(Node list, int k) {

        if (list == null || k < 1) {
            return null;
        }
        Node cur = list;
        Node prev = cur;
        Node head = list;
        int i = 0;
        while (cur != null && i < k) {
            prev = cur;
            cur = cur.next;
            ++i;
        }
        if (cur == null && i < k) {
            return null;
        } else if (cur == null && i == k) {
            return list.next;
        }
        cur = head;
        while (prev.next != null) {
            cur = head;
            prev = prev.next;
            head = head.next;
        }
        cur.next = cur.next.next;

        return list;

    }

    // 有序链表合并
    public static Node mergeTwoLists(Node l1, Node l2) {
        Node head = new Node(-1);
        Node cur = head;
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 == null) {
            cur.next = l2;
        }
        if (l2 == null) {
            cur.next = l1;
        }
        return head.next;

    }

    // 求链表的中间结点
    public static Node getMiddleNode(Node head) {
        if (head == null) {
            return null;
        }
        Node p = head;
        Node q = head;
        while (q.next != null && q.next.next != null) {
            p = p.next;
            q = q.next.next;
        }
        return p;
    }

    //　判断是否为回文
    //  思路：找到中间节点，反转，比较
    public static boolean palindrome(Node head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        Node p = head;
        Node q = head;
        while (p.next != null && p.next.next != null) {
            p = p.next.next;
            q = q.next;
        }

        // q 是中间节点,分两种情况
        Node tmp;
        if (p.next == null) {
            // q 是最中间节点
            tmp = q;
            return compareTwoLinkListData(q.next, inverseLinkList(head,q).next);
        } else {
            tmp = q.next;
            return compareTwoLinkListData(q.next, inverseLinkList(head,q));
        }
    }

    // 比较两个链表数据是否相同
    public static boolean compareTwoLinkListData(Node left, Node right) {
        Node l = left;
        Node r = right;
        while (l != null && r != null) {
            if (l.value == r.value) {
                l = l.next;
                r = r.next;
                continue;
            } else {
                return false;
            }
        }
        if (l != null || r != null) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("--链表反转--");
        Node head = createLinkedList(new int[]{1, 2, 3, 4, 5});
        System.out.println(head.toString());
        print(head);
        Node p = reverseLinkedList(head);
        print(p);
        System.out.println("--删除倒数节点--");
        head = createLinkedList(new int[]{1, 2, 3, 4, 5});
        print(deleteLastKth(head, 5));
        System.out.println("--得到中间节点--");
        System.out.println(getMiddleNode(head).value);
        Node head3 = createLinkedList(new int[]{1, 2, 3});
        Node head4 = createLinkedList(new int[]{1, 2, 3, 4});
        Node head5 = createLinkedList2(new int[]{1, 2, 3, 4, 5});
        System.out.println("--检测环--");
        System.out.println(checkCircle(head5));
        System.out.println("--合并有序链表--");
        print(mergeTwoLists(head4, head3));
        System.out.println("---判断回文-");
        Node head6 = createLinkedList(new int[]{1});
        System.out.println(palindrome(head6));
        Node head7 = createLinkedList(new int[]{1, 2, 3});
        System.out.println(palindrome(head7));
        Node head8 = createLinkedList(new int[]{1, 2});
        System.out.println(palindrome(head8));
        Node head9 = createLinkedList(new int[]{1, 2, 2, 1});
        System.out.println(palindrome(head9));
        Node head10 = createLinkedList(new int[]{1, 2, 3, 2, 1});
        System.out.println(palindrome(head10));

    }
}
