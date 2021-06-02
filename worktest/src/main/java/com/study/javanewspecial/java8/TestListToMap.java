package com.study.javanewspecial.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * list 转 map
 *
 * @author ldb
 * @date 2020/10/15
 */
public class TestListToMap {

    @Test
    public void test1(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Book("The Two Towers", 1954, "0345339711"));
        bookList.add(new Book("The Return of the King", 1955, "0618129111"));
        bookList.add(new Book("The Return of the King", 1955, "0618129112"));
//        Map<String, String> stringStringMap = listToMap(bookList);
//        System.out.println(stringStringMap);
//        Map<Integer, Book> integerBookMap = listToMapWithDupKeyError(bookList);
//        System.out.println(integerBookMap);
        Map<Integer, Book> integerBookMap1 = listToMapWithDupKey2(bookList);
        System.out.println(integerBookMap1);
//
//        Map<Integer, String> stringMap = listToMapWithDupKeyError2(bookList);
//        System.out.println(stringMap);
    }

    public Map<String, String> listToMap(List<Book> books) {
        return books.stream().collect(Collectors.toMap(Book::getIsbn, Book::getName));
    }

    // Key冲突报错
    public Map<Integer, String> listToMapWithDupKeyError2(List<Book> books) {
        return books.stream().collect(Collectors.toMap(Book::getReleaseYear, Book::getName));
    }

    // Key冲突报错
    public Map<Integer, Book> listToMapWithDupKeyError(List<Book> books) {
        return books.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity()));
    }
    // 解决Key冲突报错
    // key 冲突，调用合并函数，函数返回值为value的值
    public Map<Integer, Book> listToMapWithDupKey(List<Book> books) {
        return books.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity(),
                (existing, replacement) -> replacement));
    }

    // 解决Key冲突报错
    // key 冲突，调用合并函数，函数返回值为value的值
    // 一个是之前的value,一个是重复key的value
    public Map<Integer, Book> listToMapWithDupKey2(List<Book> books) {
        return books.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity(),
                (value1, value2) -> {
                    System.out.println("value1:"+value1);
                    System.out.println("value2:"+value2);
                    return value2;
                }));
    }

    // List 转 ConcurrentMap
    public Map<Integer, Book> listToConcurrentMap(List<Book> books) {
        return books.stream().collect(Collectors.toMap(Book::getReleaseYear, Function.identity(),
                (o1, o2) -> o1, ConcurrentHashMap::new));
    }

    // List 转 treeMap
    public TreeMap<String, Book> listToSortedMap(List<Book> books) {
        return books.stream()
                .sorted(Comparator.comparing(Book::getName))
                .collect(Collectors.toMap(Book::getName, Function.identity(), (o1, o2) -> o1, TreeMap::new));
    }

}

@AllArgsConstructor
@Data
class Book {
    private String name;
    private int releaseYear;
    private String isbn;

}