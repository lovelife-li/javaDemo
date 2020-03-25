package com.study.utils.spi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ldb
 * @date 2020-01-10 15:13
 * @description ??
 */
public class Test {

    private  SerializerService serializerService;

    @Before
    public void beforeStart(){
        serializerService = new SerializerService();
    }

    @org.junit.Test
    public void serializerTest() throws ObjectSerializerException {
        ObjectSerializer objectSerializer = serializerService.getObjectSerializer();
        System.out.println(objectSerializer.getSchemeName());

        byte[] cats = objectSerializer.serialize(new Cat(12, "cat"));
        System.out.println("aaaaa");
        Cat cat = objectSerializer.deSerialize(cats, Cat.class);
        System.out.println(cat.getName());

        byte[] arrays = objectSerializer.serialize(Arrays.asList("1", "2", "3"));
        ArrayList list = objectSerializer.deSerialize(arrays, ArrayList.class);
        Assert.assertArrayEquals(Arrays.asList("1", "2", "3").toArray(), list.toArray());
    }



}
@Data
@NoArgsConstructor
@AllArgsConstructor
class Cat{
    private int age;
    private String name;
}