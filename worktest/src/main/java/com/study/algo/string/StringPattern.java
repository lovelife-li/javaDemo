package com.study.algo.string;

import com.study.thread.SimpleLimiter;
import org.apache.commons.math3.analysis.function.Max;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串匹配算法BF , RK , kmp , bm
 *
 * @author ldb
 * @date 2020/08/24
 */
public class StringPattern {

    /**
     * BF 算法中的 BF 是 Brute Force 的缩写，中文叫作暴力匹配算法
     * 时间复杂度是 O(n*m)
     *
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return
     */
    public int bf(char[] a, int n, char[] b, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j == m) {
                    return i;
                }
                if (a[i + j] != b[j]) {
                    break;
                }
            }
        }
        return -1;
    }

    /**
     * RK 算法的全称叫 Rabin-Karp 算法，是由它的两位发明者 Rabin 和 Karp 的名字来命名的
     * 时间复杂度是 O(n)
     *
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return
     */
    public int rk(char[] a, int n, char[] b, int m) {
        int tab[] = new int[m];
        for (int i = 0; i < m; i++) {
            tab[i] = (int) Math.pow(26, i);
        }
        int hash_m = 0;
        int hash_n = 0;
        for (int i = 0; i < m; i++) {
            hash_m += (b[i] - 'a') * tab[m - i - 1];
            hash_n += (a[i] - 'a') * tab[m - i - 1];
        }
        if (hash_m == hash_n) {
            return 0;
        }
        for (int i = 1; i < n; i++) {
            if (i > (n - m)) {
                return -1;
            }
            hash_n = hash_n - tab[m - 1] * (a[i - 1] - 'a') * 26 + tab[0] * (a[i + m - 1] - 'a');
            if (hash_m != hash_n) {
                continue;
            }
            for (int j = 0; j < m; j++) {
                if (j == m) {
                    return j;
                }
                if (a[i + j] != b[j]) {
                    break;
                }
            }
        }

        return -1;
    }

    /**
     * BM算法
     * 1,坏字符规则
     * ----需要数组，记录模式串所有字符位置。
     * ----缺点：单纯使用坏字符规则还是不够的。因为根据 si-xi 计算出来的移动位数，有可能是负数，比如主串是 aaaaaaaaaaaaaaaa，模式串是 baaa。
     * 2,好后缀规则
     * ----对模式串，生成好后缀预处理数组,
     * ----计算滑动位置
     * <p>
     * 平均的情况时间复杂度O(n/m)，空间复杂度bc[], prefix[],suffix[]  O(m+bc.length)
     *
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return
     */
    public int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE];
        // 预处理坏字符
        genericBC(b, m, bc);

        // 预处理好后缀
        boolean[] prefix = new boolean[m];
        int[] suffix = new int[m];
        generateGS(b, m, suffix, prefix);
        int i = 0;
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; j--) {
                if (a[i + j] != b[j]) {
                    break;
                }
            }
            if (j < 0) {
                return i;
            }
            // 记录坏字符移动个数
            int x = j - bc[a[i + j]];
            // 记录好后缀移动个数
            int y = 0;
            // 坏字符在m-2位置，才有好后缀
            if (j < m - 1) {
                y = moveByGS(j, m, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;

    }


    public static int SIZE = 256;

    /**
     * bad character rule
     * 生成坏字符数组
     *
     * @param b
     * @param m
     * @param bc
     */
    public void genericBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            bc[b[i]] = i;
        }
    }

    /**
     * 对模式串，生成好后缀预处理数组
     * 思路：
     * 拿下标从 0 到 i 的子串（i 可以是 0 到 m-2）与整个模式串，求公共后缀子串。
     * 如果公共后缀子串的长度是 k，那我们就记录 suffix[k]=j（j 表示公共后缀子串的起始下标）。
     * 如果 j 等于 0，也就是说，公共后缀子串也是模式串的前缀子串，我们就记录 prefix[k]=true。
     * <p>
     *
     * @param b      b 表示模式串 如：cabcab
     * @param m      m 表示长度
     * @param suffix 数组，记录子串下标
     * @param prefix 数组，记录子串是不是前缀
     */
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        // 初始化
        for (int i = 0; i < m; ++i) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        // abcde的好后缀是bcde,所以要减去一
        // 前缀串b[0,m-2]与后缀串b[1,m-1]求公共后缀子串,以b[m-1] 为比较
        // 比如cab 与b 求后缀子串
        for (int i = 0; i < m - 1; i++) {
            int j = i;
            int k = 0;
            while (j >= 0 && b[j] == b[m - 1 - k]) {
                ++k;
                suffix[k] = j;
                --j;
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }

    }

    /**
     * @param j      表示坏字符对应的模式串中的字符下标
     * @param m      表示模式串长度
     * @param suffix
     * @param prefix
     * @return
     */
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        // 好后缀长度
        int k = m - 1 - j;
        if (suffix[k] != -1) {
            return j - suffix[k] + 1;
        }
        // 模式串不匹配好后缀，找好后缀的后缀子串与模式串匹配的前缀子串
        for (int r = j + 2; r <= m - 1; r++) {
            if (prefix[m - r]) {
                // r下标匹配前缀，所以滑动r位。
                return r;
            }
        }
        return m;

    }

    // a, b 分别是主串和模式串；n, m 分别是主串和模式串的长度。

    /**
     * KMP 算法是根据三位作者（D.E.Knuth，J.H.Morris 和 V.R.Pratt）的名字来命名的，算法的全称是 Knuth Morris Pratt 算法
     * 思路：在模式串和主串匹配的过程中，当遇到坏字符后，对于已经比对过的好前缀，能否找到一种规律，将模式串一次性滑动很多位
     * <p>
     * 模式串前往后匹配
     * <p>
     * 如何求好前缀的最长可匹配前缀最后字符下标呢？
     * 提前求next数组。
     * <p>
     * 空间复杂度是 O(m)
     * 时间复杂度就是 O(m+n)
     *
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int kmp(char[] a, int n, char[] b, int m) {
        int next[] = getNexts(b, m);
        for (int i = 0; i < n; i++) {
            int j = 0;
            // 找可匹配下标
            while (j > 0 && a[i] != b[j]) {
                j = next[j - 1] + 1;
            }
            // 一直相等
            if (a[i] == b[j]) {
                ++j;
            }
            if (j == m) {
                return i - m + 1;
            }

        }
        return -1;
    }

    // b 表示模式串，m 表示模式串的长度  ababacd
    private int[] getNexts(char[] b, int m) {
        int next[] = new int[m];
        next[0] = -1;
        int k = -1;
        // 两个字符才能有最长匹配前缀
        for (int i = 1; i < m; i++) {
            if (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }


    public static void main(String[] args) {
        String a = "abcdeaabcd";
        String b = "bcdea";
        StringPattern p = new StringPattern();
        int bf = p.bf(a.toCharArray(), a.length(), b.toCharArray(), b.length());
        System.out.println(bf);
        int rk = p.bf(a.toCharArray(), a.length(), b.toCharArray(), b.length());
        System.out.println(rk);

        int bm = p.bm(a.toCharArray(), a.length(), b.toCharArray(), b.length());
        System.out.println(bm);
    }


}
