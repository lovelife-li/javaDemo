package com.study.other.extend;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ac自动机（全称是 Aho-Corasick ）算法：多模式串匹配
 * 用法：可以匹配多个关键词，做关键词过滤。
 *
 * @author ldb
 * @date 2020/08/25
 */
public class ACTrie {

    final static class AcNode {
        char data;
        // 字符集只包含 a~z 这 26 个字符
        AcNode[] children = new AcNode[26];
        boolean isEndChar = false;
        // 当 isEndingChar=true 时，记录模式串长度
        public int length = 0;
        // 失败指针
        public AcNode fail;

        public AcNode(char data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data + "->" + fail;
        }
    }

    // 存储无意义字符
    private AcNode root = new AcNode('/');

    // 往 Trie 树中插入一个字符串
    public void insert(char[] text) {
        AcNode p = root;
        int h = 0;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                AcNode newNode = new AcNode(text[i]);
                p.children[index] = newNode;
            }
            ++h;
            p = p.children[index];
            p.length = h;
        }
        p.isEndChar = true;
    }

    // 在 Trie 树中查找一个字符串
    public boolean find(char[] pattern) {
        AcNode p = root;
        for (int i = 0; i < pattern.length; ++i) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                return false; // 不存在 pattern
            }
            p = p.children[index];
        }
        if (p != root && p.isEndChar == false) {
            return false; // 不能完全匹配，只是前缀
        } else {
            return true; // 找到 pattern
        }
    }

    /**
     * 构建失败指针，需要广度遍历每一个数据
     */
    public void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            for (int i = 0; i < 26; i++) {
                AcNode q = p.children[i];
                if (q == null) {
                    // 继续构建下一个节点失败指针
                    continue;
                }
                if (p == root) {
                    q.fail = root;
                } else {
                    // 找父节点失败指针
                    AcNode pf = p.fail;
                    while (pf != null) {
                        // 找父节点失败指针儿子
                        AcNode pfc = pf.children[q.data - 'a'];
                        if (pfc != null) {
                            q.fail = pfc;
                            break;
                        }
                        pf = pf.fail;
                    }
                    if (pf == null) {
                        q.fail = root;
                    }
                }
                queue.add(q);
            }
        }
    }

    /**
     * 思路：依次在每一条分支上找，如果没找到，就到失败指针指向的分支上继续找。找到包含的关键词。
     *
     * @param text 在text中匹配关键词
     */
    public void match(char[] text) {
        AcNode p = root;
        for (int i = 0; i < text.length; i++) {
            int id = text[i] - 'a';
            // p 应该指向一个不为null的值,找p的失败指针，找完，在从p开始找
            while (p.children[id] == null && p != root) {
                // 找失败指针
                p = p.fail;
            }
            p = p.children[id];
            if (p == null) {
                p = root;
                continue;
            }
            // p 有值处理
            AcNode tmp = p;
            while (tmp != root) {
                if (tmp.isEndChar) {
                    int pos = i - tmp.length + 1;
                    System.out.println(" 匹配起始下标 " + pos + "; 长度 " + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }

    public static void main(String[] args) {
        ACTrie acTrie = new ACTrie();
        acTrie.insert("you".toCharArray());
        acTrie.insert("are".toCharArray());
        acTrie.insert("a".toCharArray());
        acTrie.insert("nice".toCharArray());
        acTrie.insert("person".toCharArray());

        acTrie.buildFailurePointer();
        boolean b = acTrie.find("nice".toCharArray());
        System.out.println(b);

        String text = "you are a nice persion";
        acTrie.match(text.toCharArray());
    }

}
