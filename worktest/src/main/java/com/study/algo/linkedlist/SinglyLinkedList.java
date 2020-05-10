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
        Node node = new Node(value, head);
        head = node;
    }

    public void insertToHead(Node newNode) {
        if (newNode != null) {
            newNode.next = head;
            head = newNode;
        }
    }

    // 插入数组
    public void insertArray(int[] data) {
        if (head == null) {
            insertToHead(data[0]);
            for (int i = 1; i < data.length; i++) {
                insertTail(data[i]);
            }
        } else {
            for (int i = 0; i < data.length; i++) {
                insertTail(data[i]);
            }
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
        insertAfter(p, new Node(value, null));
    }

    public void insertAfter(Node p, Node newNode) {
        if (p == null) {
            System.out.println("p为null，不能插入");
            return;
        }
        Node cur = head;
        if (cur == null) {
            System.out.println("没有找到p节点");
        } else {
            if (cur == p) {
                Node tmp = cur.next;
                cur.next = newNode;
                newNode.next = tmp;
            } else {
                while (cur != null && cur.next != p) {
                    cur = cur.next;
                }
                if (cur == null) {
                    System.out.println("链表中没有p节点,不能插入");
                } else {
                    Node tmp = p.next;
                    p.next = newNode;
                    newNode.next = tmp;
                }
            }
        }

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

    // 找到节点删除
    public void deleteByNode(Node p) {
        if (p == null) {
            System.out.println("p为null，不能删除");
            return;
        }
        Node cur = head;
        if (cur == null) {
            System.out.println("没有找到p节点");
        } else {
            if (cur == p) {
                head = head.next;
            } else {
                while (cur != null && cur.next != p) {
                    cur = cur.next;
                }
                if (cur == null) {
                    System.out.println("链表中没有p节点,不能删除");
                } else {
                    cur.next = cur.next.next;
                }
            }
        }
    }

    // 删除具有该值的节点
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

    //判断两个链表值是否相等
    public boolean TFResult(Node left, Node right) {
        Node l = left;
        Node r = right;
        while (l != null && r != null) {
            if (l.data == r.data) {
                l = l.next;
                r = r.next;
                continue;
            } else {
                return false;
            }
        }
        if (l == null && r == null) {
            return true;
        } else {
            return false;
        }
    }

    //　判断是否为回文
    //  找中间节点，反转，比较
    public boolean palindrome() {
        Node p = head;
        Node q = head;
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        while (q.next != null && q.next.next != null) {
            p = p.next;
            q = q.next.next;
        }

        // p 是中间节点
        if (q.next == null) {
            // p是正中间节点
            Node tmp = inverseLinkList(p);
            tmp.print();
            return TFResult(p.next, tmp.next);
        } else {
            return TFResult(p.next, inverseLinkList(p));
        }

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
            p.next = prev;
            return p;
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

        public void print() {
            Node cur = this;
            while (cur != null) {
                System.out.print(cur.data + "->");
                cur = cur.next;
            }
            System.out.println("null");

        }


        public int getData() {
            return data;
        }
    }

    public static void main(String[] args) {

        SinglyLinkedList link = new SinglyLinkedList();
        int data[] = {1, 2, 3, 4, 5, 6};
        link.insertArray(data);
        System.out.println("打印原始:");
        link.printAll(link.head);

        Node node = link.inverseLinkList(link.head.next.next.next);
        link.printAll(node);

        link = new SinglyLinkedList();
        link.insertArray(new int[]{1, 2});
        link.insertToHead(0);
        link.insertAfter(link.head.next, 3);
        link.insertTail(5);
        link.deleteByValue(2);
        link.insertArray(new int[]{5, 3, 1, 0});
        link.printAll(link.head);

        System.out.println();
        if (link.palindrome()) {
            System.out.println("回文");
        } else {
            System.out.println("不是回文");
        }
    }

}
