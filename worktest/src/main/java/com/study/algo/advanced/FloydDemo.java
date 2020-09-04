package com.study.algo.advanced;

/**
 * @author ldb
 * @date 2020/9/4
 * @description Floyd算法demo
 * 时间复杂度:O(n^3)；
 * 空间复杂度:O(n^2)
 */
public class FloydDemo {
    //    表示无穷大 即不可达
    public static int MAX = Integer.MAX_VALUE;
    //    距离矩阵
    public int[][] dist;
    //    路径Path矩阵
    public int[][] path;

    /**
     * 按点初始化
     *
     * @param size
     */
    public FloydDemo(int size) {
        this.path = new int[size][size];
        this.dist = new int[size][size];
    }

    public static void print(int[][] arrs) {
        System.out.println("------------------------");
        for (int[] arr : arrs) {
            for (int a : arr) {
                if (a == FloydDemo.MAX) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(a + " ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    /**
     * 核心算法
     * 构建距离矩阵和路径矩阵
     *
     * @param matrix
     */
    public void floyd(int[][] matrix) {
//        matrix和path length不一致可处理异常
        int size = matrix.length;
        //初始化 dist 和 path
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                path[i][j] = -1;
                dist[i][j] = matrix[i][j];
            }
        }
        // 核心算法
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == j) {
                        continue;
                    }
                    // 判断[i][j]是否可以从k到达
                    // 判断如果 ik距离可达和kj距离可达 且 i和j的距离是否大于 i-> k 与 k->j的距离和
                    if (dist[i][k] != MAX && dist[k][j] != MAX && dist[i][j] > (dist[i][k] + dist[k][j])) {
                        System.out.println("路径{" + i + "->" + j + "}在点" + k + "处可中转(原先中转点为" + path[i][j] + ")，距离由" + dist[i][j] + "缩短为" + (dist[i][k] + dist[k][j]));
                        path[i][j] = k;
                        dist[i][j] = dist[i][k] + dist[k][j];
                    } else {
                        System.out.println("路径{" + i + "->" + j + "}在点" + k + "中转不合适");
                    }
                }
            }
        }
    }

    //    查找i到j的路径
    public void findPath(int i, int j) {
//        i = j    0
//        i < size && j < size
        StringBuffer pathStr = new StringBuffer(i + " -> ");
//        List list = new ArrayList(); 也可以存储list里
        tofind(i, j, pathStr);
        pathStr.append(j);
        System.out.println(i + "到" + j + ":");
        System.out.println("最终路径：" + pathStr.toString());
        System.out.println("最终距离：" + dist[i][j]);
    }

    public void tofind(int i, int j, StringBuffer pathStr) {
        if (path[i][j] != -1) {
            pathStr.append(path[i][j] + " -> ");
//           list.add(path[i][j])
            tofind(path[i][j], j, pathStr);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 5, MAX, 7},
                {MAX, 0, 4, 2},
                {3, 3, 0, 2},
                {MAX, MAX, 1, 0}
        };

        FloydDemo.print(matrix);
        FloydDemo demo = new FloydDemo(matrix[0].length);
        demo.floyd(matrix);
        System.out.println("算法执行之后：");
        FloydDemo.print(demo.dist);
        FloydDemo.print(demo.path);
        demo.findPath(0, 3);


    }


}