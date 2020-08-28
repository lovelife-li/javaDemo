package com.study.algo.tree;

/**
 * Trie 树 ,一个多叉树
 * Trie 树的本质，就是利用字符串之间的公共前缀，将重复的前缀合并在一起。
 * 查找某个字符串的时间复杂度是多少？
 * 构建好 Trie 树后，在其中查找字符串的时间复杂度是 O(k)，k 表示要查找的字符串的长度。
 * 构建 Trie 树的过程，需要扫描所有的字符串，时间复杂度是 O(n)（n 表示所有字符串的长度和）
 *
 * @author ldb
 * @date 2020/08/25
 */
public class TrieTree {

    Node head = new Node('/');

    static class Node {
        char data;
        Node childern[] = new Node[26];
        boolean isEndChar = false;

        public Node(char data) {
            this.data = data;
        }
    }

    // 往 Trie 树中插入一个字符串
    public void insert(char[] text) {
        Node q = head;
        for (int i = 0; i < text.length; i++) {
            int p = text[i] - 'a';
            if (q.childern[p] == null) {
                q.childern[p].data = text[i];
            }
            q = q.childern[p];
        }
        q.isEndChar = true;

    }

    // 在 Trie 树中查找一个字符串
    public boolean find(char[] pattern) {
        Node q = head;
        for (int i = 0; i < pattern.length; i++) {
            int p = pattern[i] - 'a';
            if (q.childern[p] == null) {
                return false;
            } else {
                q = q.childern[p];
            }
        }
        if (q != head && q.isEndChar) {
            return true;
        } else {
            return false;
        }
    }

}
