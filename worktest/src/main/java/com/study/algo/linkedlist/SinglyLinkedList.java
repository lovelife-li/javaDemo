package com.study.algo.linkedlist;

/**
 * 1）单链表的插入、删除、查找操作；
 * 2）链表中存储的是int类型的数据；
 * <p>
 * Author：ldb
 */
public class SinglyLinkedList {

    private Node head = null;

    public Node findByValue(int value) {
        Node cur = head;
        while (cur != null) {
            if (cur.data == value) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public Node findByIndex(int index) {
        Node cur = head;
        int i = 0;
        while (cur != null) {
            if (i == index) {
                return cur;
            }
            ++i;
            cur = cur.next;
        }
        return null;
    }

    //表头部插入
    public void insertToHead(int value) {
        Node node = new Node(value, null);
        insertToHead(node);
    }

    public void insertToHead(Node newNode) {
        if (newNode != null) {
            newNode.next = head;
            head = newNode;
        }
    }

    //顺序插入
    //链表尾部插入
    public void insertTail(int value) {
        if (head == null) {
            head = new Node(value, null);
        } else {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = new Node(value, null);
        }
    }

    /**
     * 在p节点后插入值
     * 判断p节点在不在，存在才插入
     *
     * @param p
     * @param value
     */
    public void insertAfter(Node p, int value) {
        Node node = findByValue(p.data);
        if (node != null) {
            Node tmp = node.next;
            node.next = new Node(value, tmp);
            ;
        }
    }

    public void insertAfter(Node p, Node newNode) {
        insertAfter(p, newNode.data);

    }

    public void insertBefore(Node p, int value) {
        insertBefore(p, new Node(value, null));
    }

    public void insertBefore(Node p, Node newNode) {
        Node cur = head;
        Node prev = cur;
        while (cur != null) {
            if (cur == p) {
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        if (prev == p) {
            insertToHead(newNode);
        } else if (cur == null) {
            System.out.println("没有找到p节点");
        } else {
            prev.next = newNode;
            newNode.next = p;
        }


    }

    public void deleteByNode(Node p) {
        deleteByValue(p.data);
    }

    public void deleteByValue(int value) {
        Node cur = head;
        Node prev = cur;
        while (cur != null) {
            if (cur.data == value) {
                break;
            }
            prev = cur;
            cur = cur.next;
        }

        if (cur != null && prev != cur) {
            prev.next = prev.next.next;
        } else {
            head = head.next;
        }
    }

    public void printAll(Node node) {
        Node p = node;
        while (p != null) {
            System.out.print(p.data + "->");
            p = p.next;
        }
        System.out.println("null");
    }

    //判断true or false
    public boolean TFResult(Node left, Node right) {
        return false;
    }

    //　判断是否为回文 
    public boolean palindrome() {

        return false;
    }


    //链表中指定节点翻转
    public Node inverseLinkList(Node p) {
        Node node = findByValue(p.data);
        // p节点存在
        if (node != null) {
            Node cur = head;
            Node prev = null;
            Node tmp;
            while (cur != p) {
                tmp = cur;
                cur = cur.next;
                tmp.next = prev;
                prev = tmp;
            }
            return p.next = prev;
        }
        return null;

    }


    public static Node createNode(int value) {
        return new Node(value, null);
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public static void main(String[] args) {

        SinglyLinkedList link = new SinglyLinkedList();
//        int data[] = {1};
        //int data[] = {1,2};
        //int data[] = {1,2,3,1};
        //int data[] = {1,2,5};
        //int data[] = {1, 2, 2, 1};
        //int data[] = {1,2,5,2,1};
        int data[] = {1, 2, 3, 4, 5, 6};

        for (int i = 0; i < data.length; i++) {
            //link.insertToHead(data[i]);
            link.insertTail(data[i]);
        }
        System.out.println("打印原始:");
        link.printAll(link.head);

        Node node = link.inverseLinkList(link.head.next.next.next);
        link.printAll(node);
        link.printAll(link.head);


        System.out.println();
//        if (link.palindrome()) {
//            System.out.println("回文");
//        } else {
//            System.out.println("不是回文");
//        }
    }

}
