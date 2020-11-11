package com.study.rpc.demo02;

public class Dao {

    public void update() {
        System.out.println("PeopleDao.update()");
    }

    public void select() {
        System.out.println("PeopleDao.select()");
    }

    public Dao() {
        update();
    }
}