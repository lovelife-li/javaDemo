package com.study.javanewspecial.java8;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * parallelStream是一个并行执行的流，其使用 fork/join （ForkJoinPool）并行方式来拆分任务和加速处理过程。
 *
 * ForkJoinPool的核心是采用分治法的思想，将一个大任务拆分为若干互不依赖的子任务，把这些子任务分别放到不同的队列里，
 * 并为每个队列创建一个单独的线程来执行队列里的任务。同时，为了最大限度地提高并行处理能力，采用了工作窃取算法来运行任务，
 * 也就是说当某个线程处理完自己工作队列中的任务后，尝试当其他线程的工作队列中窃取一个任务来执行，直到所有任务处理完毕。
 * 所以为了减少线程之间的竞争，通常会使用双端队列，被窃取任务线程永远从双端队列的头部拿任务执行，
 * 而窃取任务的线程永远从双端队列的尾部拿任务执行。
 */
public class ParallelStreamTest {
	private static final int COUNT = 1000;
	public static void main(String[] args) {
		List<RiderDto> orilist=new ArrayList<RiderDto>();
		// 添加1000个骑手
        for(int i=0;i<COUNT;i++){
        	orilist.add(init());
        }
        final List<RiderDto> copeList=new ArrayList<RiderDto>();
        orilist.parallelStream().forEach(rider -> {
        	RiderDto t = new RiderDto();
        	t.setId(rider.getId());
    		t.setCityId(rider.getCityId());
        	copeList.add(t);
		});
        System.out.println("orilist size:"+orilist.size());
        System.out.println("copeList size:"+copeList.size());
        System.out.println("compare copeList and list,result:"+(copeList.size()==orilist.size())); 
	}
	// 骑手dto
	private static RiderDto init() {
		RiderDto t = new RiderDto();
		Random random = new Random();
		t.setId(random.nextInt(2 ^ 20));
		t.setCityId(random.nextInt(1000));
		return t;
	}
	@Data
	static class RiderDto implements Serializable {
		private static final long serialVersionUID = 1;
		//城市Id
	    private Integer cityId;
	    //骑手Id
	    private Integer id;
	}
}