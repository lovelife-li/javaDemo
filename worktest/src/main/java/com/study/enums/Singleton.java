package com.study.enums;

/**
 * @author ldb
 * @date 2020-03-19 9:16
 * @description ??
 */
public class Singleton {

    private Singleton(){}

    public enum SingletonEnum{
        SEED;

        private Singleton singleton;

        SingletonEnum(){
            singleton = new Singleton();
        }

        public Singleton getInstance(){
            return singleton;
        }
    }

    public static Singleton getInstance(){
        return SingletonEnum.SEED.getInstance();
    }

}
