package com.study.utils.web;

/**
 * staticObj、instanceObj、localObj存放在哪里?
 */
public class JHSDB_TestCase {
    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done"); // 这里设一个断点
        }
    }
    
    
    void test(JHSDB_TestCase j) {
        j.t();
    }
    
    private void t() {
        System.out.println("t()");
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new JHSDB_TestCase.Test();
        test.foo();

        Cat cat = new Cat();
        cat.test(cat);

    }
}
class Cat extends JHSDB_TestCase{

}