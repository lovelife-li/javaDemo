package com.study.java8.demo02.exer;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestTransaction_my {
	
	List<Transaction> transactions = null;
	
	@Before
	public void before(){
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		
		transactions = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950)
		);
	}
	
	//1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
	@Test
	public void test1(){
		transactions.stream()
				.filter(x->x.getYear()==2011)
				.sorted(Comparator.comparingInt(Transaction::getValue))
				.forEach(System.out::println);
	}
	
	//2. 交易员都在哪些不同的城市工作过？
	@Test
	public void test2(){
		transactions.stream()
				.map(x->x.getTrader().getCity())
				.distinct()
				.forEach(System.out::println);
	}
	
	//3. 查找所有来自剑桥的交易员，并按姓名排序
	@Test
	public void test3(){
		transactions.stream()
				.map(Transaction::getTrader)
				.filter(x->x.getCity().equals("Cambridge"))
				.sorted(Comparator.comparing(Trader::getName))
				.distinct()
				.forEach(System.out::println);
	}
	
	//4. 返回所有交易员的姓名字符串，按字母顺序排序
	@Test
	public void test4(){
		transactions.stream()
				.map(Transaction::getTrader)
				.map(Trader::getName)
				.distinct()
				.sorted()
				.forEach(System.out::println);
	}
	

	
	//5. 有没有交易员是在米兰工作的？
	@Test
	public void test5(){
		boolean b = transactions.stream()
				.map(Transaction::getTrader)
				.anyMatch(x -> x.getCity().equals("Milan"));
		System.out.println(b);

	}
	
	
	//6. 打印生活在剑桥的交易员的所有交易额
	@Test
	public void test6(){
		transactions.stream()
				.filter(x->x.getTrader().getCity().equals("Cambridge"))
				.map(Transaction::getValue)
				.forEach(System.out::println);
	}
	
	
	//7. 所有交易中，最高的交易额是多少
	@Test
	public void test7(){
		Optional<Integer> max = transactions.stream()
				.map(Transaction::getValue)
				.max(Integer::compare);
		System.out.println(max);
	}
	
	//8. 找到交易额最小的交易
	@Test
	public void test8(){
		IntSummaryStatistics collect = transactions.stream()
				.collect(Collectors.summarizingInt(Transaction::getValue));

		System.out.println(collect.getMin());

		Optional<Transaction> min = transactions.stream().min((x1, x2) -> x1.getValue() - x2.getValue());

		System.out.println(min.get());
	}

}