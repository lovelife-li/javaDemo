package com.study.algo.exam.question;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author ldb
 * @date 2021/3/4
 * @dsscription
 */
public class Question001 {

    /***
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 字符串中只有字符'('和')'。合法字符串需要括号可以配对。
     */
    static boolean isValid(String s) {
        if (s == null || s.equals("")) {
            return false;
        }
        // 当字符串长度为奇数的时候，不可能是一个有效的合法字符串
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                if (c != ')') {
                    return false;
                } else {
                    if (stack.empty()) {
                        return false;
                    }
                    stack.pop();
                }
            }
        }
        return stack.empty();
    }

    /**
     * Size = [4, 2, 5, 3, 1], Dir = [1, 1, 0, 0, 0]
     * 在水中有许多鱼，可以认为这些鱼停放在 x 轴上。再给定两个数组 Size，Dir，Size[i] 表示第 i 条鱼的大小，
     * Dir[i] 表示鱼的方向 （0 表示向左游，1 表示向右游）。这两个数组分别表示鱼的大小和游动的方向，并且两个数组的长度相等。
     * 鱼的行为符合以下几个条件:
     * 1，所有的鱼都同时开始游动，每次按照鱼的方向，都游动一个单位距离；
     * 2，方向相对时，大鱼会吃掉小鱼；
     * 3，鱼的大小都不一样。
     * 计算还剩下几条鱼，用栈来存活鱼
     */
    static int solution(int[] size, int[] dir) {
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < size.length; i++) {
            // 当前的鱼是否被栈中的鱼吃掉了
            boolean hasEat = false;
            // 后面的鱼只有向左游，才可能会被吃掉
            while (dir[i] == 0 && !s.empty() && dir[s.peek()] == 1) {
                if (size[s.peek()] > size[i]) {
                    hasEat = true;
                    break;
                }
                s.pop();
            }
            if (!hasEat) {
                s.push(i);
            }
        }
        return s.size();
    }

    /**
     * 数组中右边第一个比我小的元素的位置
     * 栈中存的是没有比它大或小的值的下标
     * 递增栈
     */
    public static int[] findRightSmall(int[] a) {
        if (a == null || a.length == 0) {
            return new int[0];
        }
        Stack<Integer> s = new Stack<>();
        int[] res = new int[a.length];
        // 遍历，将元素下标存入栈
        for (int i = 0; i < a.length; i++) {
            while (!s.empty() && a[i] < a[s.peek()]) {
                // 记录结果
                res[s.peek()] = i;
                s.pop();
            }
            s.push(i);
        }
        while (!s.empty()) {
            res[s.pop()] = -1;
        }
        return res;
    }

    public static int[] findLeftSmall(int[] a) {
        return null;
    }

    /**
     * 给定一个正整数数组和 k，要求依次取出 k 个数，输出其中数组的一个子序列，需要满足：1. 长度为 k；2.字典序最小。
     * 输入：nums = [3,5,2,6], k = 2
     * 输出：[2,6]
     * <p>
     * 递增栈
     */
    static int[] findSmallSeq(int[] a, int k) {
        Stack<Integer> s = new Stack<>();
        int[] res = new int[k];
        for (int i = 0; i < a.length; i++) {
            // 当剩下的数字个数与栈中的元素刚好能凑够 k 个数时，就不能再消除了
            while (!s.empty() && s.size() + a.length - i > k) {
                if (s.peek() <= a[i]) {
                    break;
                }
                s.pop();

            }
            s.push(a[i]);
        }
        while (s.size() > k) {
            s.pop();
        }
        for (int i = k - 1; i >= 0; i--) {
            res[i] = s.pop();
        }
        return res;
    }

    /**
     * 给定一个数组，数组中的元素代表木板的高度。请你求出相邻木板能剪出的最大矩形面积。
     * 利用栈，栈顶方
     * 输入: [2,1,5,6,2,3]
     * 输出: 10
     */
    static int findMaxSquart(int[] a, int w) {
        int max = 0;
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < a.length; i++) {
            while (!s.empty() && a[i] > s.peek()) {
                int tmp = s.peek();
                s.pop();
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        int[] size = {4, 2, 5, 3, 1};
        int[] dir = {1, 1, 0, 0, 0};
        System.out.println(solution(size, dir));

        int[] a = {1, 2, 4, 9, 4, 0, 5};
//        System.out.println(Arrays.toString(findRightBig(a)));

        int[] a1 = {3, 5, 2, 1};
        int[] a2 = {3, 5, 2, 6};
        int[] a3 = {9, 2, 4, 5, 1, 2, 3, 0};
        System.out.println(Arrays.toString(findSmallSeq(a1, 2)));
        System.out.println(Arrays.toString(findSmallSeq(a2, 2)));
        System.out.println(Arrays.toString(findSmallSeq(a3, 3)));

        int[] a4 = {14, 7, 9, 1, 12, 6, 4, 2};
    }
}
