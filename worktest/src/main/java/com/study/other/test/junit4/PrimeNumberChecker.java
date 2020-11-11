package com.study.other.test.junit4;

/**
 * @author ldb
 */
public class PrimeNumberChecker {

    public Boolean validate(final Integer parimeNumber) {
        for (int i = 2; i < (parimeNumber / 2); i++) {
            if (parimeNumber % i == 0) {
                return false;
            }
        }
        return true;
    }
}
