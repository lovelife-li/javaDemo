package com.study.algo.tree;

/**
 * 二叉搜索树
 *
 * @author ldb
 * @date 2020/08/21
 */
public class BinarySearchTree {

    Node head;

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node find(int data) {
        Node p = head;
        while (p != null) {
            if (p.data == data) {
                return head;
            } else if (p.data > data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    public boolean insert(int data) {
        Node p = head;
        Node node = new Node(data);
        if (p == null) {
            head = node;
            return true;
        }
        while (p != null) {
            if (p.data > data) {
                if (p.left == null) {
                    p.left = node;
                    return true;
                }
                p = p.left;
            } else if (p.data < data) {
                if (p.right == null) {
                    p.right = node;
                }
                p = p.right;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean delete(int data) {
        Node pp = null;
        Node p = head;
        // 找到删除节点
        while (p != null && p.data != data) {
            pp = p;
            if (p.data > data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            // 没找到p
            return false;
        }
        // 找到p
        Node p1 = null;
        Node p2 = null;
        if (p.left != null && p.right != null) {
            // 找后继节点
            p1 = p.right;
            // 找后继节点父节点
            p2 = p;
            while (p1.left != null) {
                p2 = p1;
                p1 = p1.left;
            }
            p.data = p1.data;
            // 需要删除p
            p = p1;
            pp = p2;

        }
        Node child = null;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        }
        if (pp == null) {
            head = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
        return true;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(33);
        tree.insert(16);
        tree.insert(50);
        tree.insert(51);
        tree.delete(33);

        tree.print1(tree.head);
        System.out.println();
        System.out.println(tree.getDeepth(tree.head));
    }

    public void print1(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            print1(node.left);
            print1(node.right);
        }
    }

    public void print2(Node node) {
        if (node != null) {
            print2(node.left);
            System.out.print(node.data + " ");
            print2(node.right);
        }
    }

    public void print3(Node node) {
        if (node != null) {
            print3(node.left);
            print3(node.right);
            System.out.print(node.data + " ");
        }
    }

    public int getDeepth(Node node) {
        if (node != null) {
            return Math.max(getDeepth(node.left) + 1, getDeepth(node.right) + 1);
        } else {
            return 0;
        }
    }

}
