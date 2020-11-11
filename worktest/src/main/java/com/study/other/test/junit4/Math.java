package com.study.other.test.junit4;


/**
 * @author ldb
 */
public class Math {

    /**
     * 阶乘
     * @param n
     * @return
     */
    public int factorial(int n) throws Exception {
        if (n < 0) {
            throw new Exception("负数没有阶乘");
        } else if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

}