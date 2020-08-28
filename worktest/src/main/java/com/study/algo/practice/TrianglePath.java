package com.study.algo.practice;

/**
 * 杨辉三角,填上任意数字，求上层到下层最短路径
 *
 * @author ldb
 * @date 2020/08/28
 */
public class TrianglePath {

    public static int f(int[][] a, int m, int n) {
        int[][] status = new int[m][n];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                status[i][j] = a[i][j];
            }
        }

        // 初始化第一列
        int tmp = a[0][0];
        for (int i = 1; i < m; i++) {
            status[i][0] = a[i][0] + tmp;
            tmp = status[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < a[i].length; j++) {
                if (status[i - 1][j] == 0) {
                    status[i][j] += status[i - 1][j - 1];
                } else {
                    status[i][j] += Math.min(status[i - 1][j - 1], status[i - 1][j]);
                }

            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (min > status[m - 1][i]) {
                min = status[m - 1][i];
            }
            System.out.print(status[m - 1][i] + " ");
        }
        System.out.println();
        return min;
    }

    //  回溯法
    private static int minValue = Integer.MAX_VALUE;

    /**
     * @param i    行下标
     * @param j    列下标
     * @param dist 最短路径
     * @param a    数据
     * @param n
     */
    public static void triangle(int i, int j, int dist, int[][] a, int n) {
        dist += a[i][j];
        if (i == n - 1) {
            if (minValue > dist) {
                minValue = dist;
            }
            return;
        }
        triangle(i + 1, j, dist, a, n);
        triangle(i + 1, j + 1, dist, a, n);
    }


    public static void main(String[] args) {
        int[][] a = {
                {5}, {7, 1}, {2, 3, 4}, {4, 1, 6, 1}, {2, 2, 1, 4, 5}
        };
        int min = f(a, 5, 5);
        System.out.println(min);

        triangle(0, 0, 0, a, 5);
        System.out.println(minValue);
    }

}
