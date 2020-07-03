package com.study.other.extend;

public class Test {

    static class Father {
        int a = 1;
        public Father() {
            a = 4;
            this.t();
        }
        
        void t() {
            System.out.println(a);
        }
    }
    
    static class Son extends Father {
        int a = 2;
        public Son() {
            a = 3;
            this.t();
        }
        void t() {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        Son s = new Son();
        System.out.println(s.a);
    }
}

