package com.study.utils.spi.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceLoader;
public class Main {
    public static void main(String[] args) {

        ServiceLoader<KeyGenerator> generators = ServiceLoader.load(KeyGenerator.class);

//        for (KeyGenerator generator : generators) {
//            System.out.println(generator.getKey());
//        }
//
//        Iterator<KeyGenerator> iterator = generators.iterator();;
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().getKey());
//        }

        ArrayList<KeyGenerator> al = new ArrayList<>();
        al.add(new SnowflakeKeyGenerator());
        al.add(new UUIDKeyGenerator());
        al.forEach(t->System.out.println(t.getKey()));
    }
}
