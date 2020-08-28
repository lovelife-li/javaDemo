package com.study.algo.practice;

/**
 * 0-1 背包
 * 我们有一个背包,背包总的承载重量是 Wkg。现在我们有 n 个物品,每个物品的重量不等,并且不可分割。我们现在期望选择几件物品,装载到背包中。
 * 在不超过背包所能装载重量的前提下,如何让背包中物品的总重量最大？
 *
 * @author ldb
 * @date 2020/08/25
 */
public class Package0_1 {

    // 存储背包中物品总重量的最大值
    public int maxW = Integer.MIN_VALUE;

    /**
     * @param a   物品重量存储在数组 a 中
     * @param cw  表示当前已经装进去的物品的重量和
     * @param max 背包重量
     * @param i   放第i个物品
     * @param n   表示物品个数
     */
    public void f(int[] a, int cw, int max, int i, int n) {
        // 退出条件
        if (i == n || cw == max) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        // 第i次不放物品
        f(a, cw, max, i + 1, n);
        // 剪枝
        if (cw + a[i] <= max) {
            // 第i次放物品
            f(a, cw + a[i], max, i + 1, n);
        }

    }

    private int maxW2 = Integer.MIN_VALUE; // 结果放到 maxW 中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    /**
     * 用回溯算法解决这个问题的时间复杂度 O(2^n)
     *
     * @param i  放第i个物品
     * @param cw 表示当前已经装进去的物品的重量和
     */
    public void f2(int i, int cw) {
        // 退出条件
        if (i == n || cw == w) {
            if (cw > maxW2) {
                maxW2 = cw;
            }
            return;
        }
        // 递归公式 2阶段
        // 第i次不放物品
        f2(i + 1, cw);

        if (cw + weight[i] <= w) {
            // 第i次放物品
            f2(i + 1, cw + weight[i]);
        }


    }


    boolean[][] mem = new boolean[n][w + 1];

    /**
     * 添加备忘录
     *
     * @param i  放第i个物品
     * @param cw 表示当前已经装进去的物品的重量和
     */
    public void f3(int i, int cw) {
        // 退出条件
        if (i == n || cw == w) {
            if (cw > maxW2) {
                maxW2 = cw;
            }
            return;
        }
        if (mem[i][cw]) {
            return;
        }
        mem[i][cw] = true;
        // 递归公式 2阶段
        // 第i次不放物品
        f2(i + 1, cw);

        if (cw + weight[i] <= w) {
            // 第i次放物品
            f2(i + 1, cw + weight[i]);
        }
    }

    /**
     * 时间复杂度是 O(n*w)
     *
     * @param n 物品个数
     * @param w 背包可承载重量
     * @param a 物品重量
     * @return
     */
    public int knapsack(int n, int w, int[] a) {
        boolean[][] weight = new boolean[n][w + 1];
        // 装一个物品
        weight[0][0] = true;
        weight[0][a[0]] = true;
        for (int i = 1; i < n; i++) {
            // 不把第 i 个物品放入背包
            for (int j = 0; j <= w; j++) {
                if (weight[i - 1][j]) {
                    weight[i][j] = true;
                }
            }
            // 把第 i 个物品放入背包,因为a[i]不在包中,减少每层循环
            for (int j = 0; j <= w - a[i]; j++) {
                if (weight[i - 1][j]) {
                    weight[i][j + a[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (weight[n - 1][i]) {
                return i;
            }
        }

        return 0;
    }

    /**
     * 时间复杂度是 O(n*w)
     * 基于一维数组做
     *
     * @param n 物品个数
     * @param w 背包可承载重量
     * @param a 物品重量
     * @return
     */
    public int knapsack2(int n, int w, int[] a) {
        boolean[] status = new boolean[w + 1];
        status[0] = true;
        status[a[0]] = true;
        for (int i = 1; i < n; i++) {
            // 把第 i 个物品放入背包
            for (int j = w - a[i]; j >= 0; --j) {
                if (status[j]) {
                    status[j + a[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (status[i]) {
                return i;
            }
        }
        return 0;
    }

    private int maxV = Integer.MIN_VALUE; // 结果放到 maxV 中
    private int[] value = {3, 4, 8, 9, 6}; // 物品的价值

    /**
     * 对于一组不同重量、不同价值、不可分割的物品,我们选择将某些物品装入背包,在满足背包最大重量限制的前提下,背包中可装入物品的总价值最大是多少呢？
     *
     * @param i  放第i个物品
     * @param cw 表示当前已经装进去的物品的重量和
     */
    public void f4(int i, int cw, int cv) {
        // 退出条件
        if (i == n || cw == w) {
            if (cv > maxV) {
                maxV = cv;
            }
            return;
        }
        // 递归公式 2阶段
        // 第i次不放物品
        f4(i + 1, cw, cv);

        if (cw + weight[i] <= w) {
            // 第i次放物品
            f4(i + 1, cw + weight[i], cv + value[i]);
        }
    }

    public int knapsack3(int n, int w, int[] a, int[] v) {
        int[][] s = new int[n][w + 1];
        s[0][0] = 0;
        s[0][a[0]] = v[0];
        for (int i = 1; i < n; i++) {
            // 抄第一行
            for (int j = 0; j <= w; j++) {
                if (s[i - 1][j] > 0) {
                    s[i][j] = s[i - 1][j];
                }
            }
            // 从第二个数算价值大小
            for (int j = 0; j <= w - a[i]; j++) {
                if (s[i - 1][j] >= 0) {
                    s[i][j + a[i]] = Math.max(s[i - 1][j] + v[i], s[i][j + a[i]]);
                }
            }

        }

        print(s);

        for (int i = w; i >= 0; i--) {
            if (s[n - 1][w] > 0) {
                return s[n - 1][w];
            }
        }
        return 0;
    }

    public int knapsack4(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1];
        for (int i = 0; i < n; ++i) { // 初始化 states
            for (int j = 0; j < w + 1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        states[0][weight[0]] = value[0];
        for (int i = 1; i < n; ++i) { // 动态规划，状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第 i 个物品
                if (states[i - 1][j] >= 0) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) { // 选择第 i 个物品
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }

        print(states);

        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n - 1][j] > maxvalue) maxvalue = states[n - 1][j];
        }
        return maxvalue;
    }

    public void print(int[][] s) {
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                System.out.print(s[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int a[] = {2, 2, 4, 6, 3};
        int[] value = {3, 4, 8, 9, 6};
        Package0_1 p = new Package0_1();
        p.f(a, 0, 9, 0, a.length);
        System.out.println(p.maxW);

        p.f2(0, 0);
        System.out.println(p.maxW2);

        p.f3(0, 0);
        System.out.println(p.maxW2);

        int w1 = p.knapsack(p.n, p.w, a);
        System.out.println(w1);

        int w2 = p.knapsack2(p.n, p.w, a);
        System.out.println(w2);

        p.f4(0, 0, 0);
        System.out.println(p.maxV);

        int v1 = p.knapsack3(p.n, p.w, a, value);
        System.out.println(v1);

        int v2 = p.knapsack4(a, value, p.n, p.w);
        System.out.println(v2);

    }
}
