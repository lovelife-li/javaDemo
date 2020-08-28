package com.study.algo.practice;

/**
 * 我们有一个 8x8 的棋盘，希望往里放 8 个棋子（皇后），每个棋子所在的行、列、对角线都不能有另一个棋子。
 * 一共有92种
 * @author ldb
 * @date 2020/08/25
 */
public class Queens8 {
    // 全局或成员变量, 下标表示行, 值表示 queen 存储在哪一列
    int[] result = new int[8];

    /**
     * 第一行，第二行依次放
     * 调用 cal8queens(0)
     *
     * @param row
     */
    public void cal8queens(int row) {
        if (row == 8) {
            print(result);
            return;
        }

        // 每行有8中方法
        for (int i = 0; i < 8; i++) {
            if (isOk(row, i)) {
                result[row] = i;
                cal8queens(row + 1);
            }
        }

    }

    /**
     * 判断 row 行 column 列放置是否合适
     *
     * @param row
     * @param column
     * @return
     */
    private boolean isOk(int row, int column) {
        int leftup = column - 1, rightup = column + 1;
        // 逐行往上考察每一行
        for (int i = row - 1; i >= 0; i--) {
            if (result[i] == column) {
                // 判断上一行，column有棋子吗？
                return false;
            }
            if (leftup >= 0) {
                // 判断上一行，左上角有棋子吗？
                if (result[i] == leftup) {
                    return false;
                }
            }
            if (rightup >= 0) {
                // 判断上一行，右上角有棋子吗？
                if (result[i] == rightup) {
                    return false;
                }
            }
            --leftup;
            ++rightup;

        }
        return true;
    }

    static int n = 0;
    public void print(int[] result) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                if (result[i] == j) {
                    System.out.print("Q ");
                }else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        ++n;
        System.out.println(n);
    }

    public static void main(String[] args) {
        Queens8 q = new Queens8();
        q.cal8queens(0);
    }

}
