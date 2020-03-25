package com.study.thread.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;

public class VolatileTests {

	public static void main(String[] args) {
		ConcurrentMap<Integer, Task> record = new ConcurrentHashMap<Integer, Task>();
		LockSupport.unpark(Thread.currentThread());
		LockSupport.unpark(Thread.currentThread());
		LockSupport.unpark(Thread.currentThread());
		LockSupport.park();
		System.out.println("ggggg");
		LockSupport.park();
		System.out.println("aaaa");

		ExecutorService newFixedThreadPool1 = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 1000; i++) {
			Task task = new Task(Thread.currentThread(), false);
			record.put(i, task);
			int j = i;
			Runnable r = new Runnable() {
				int id = j;

				@Override
				public void run() {
					Task task = record.get(id);
					task.setObj("result");
					task.setFlag(true);
					LockSupport.unpark(task.getThread());
				}
			};
			newFixedThreadPool1.execute(r);
			try {
				task.get();
//				TimeUnit.MILLISECONDS.sleep(10);
//				System.out.println("hello");
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		newFixedThreadPool1.shutdown();
	}
}

class Task implements Future<Object> {
	Thread thread;
	volatile boolean flag;
	volatile Object obj;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Task(Thread thread, boolean flag) {
		super();
		this.thread = thread;
		this.flag = flag;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return false;
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public Object get() throws InterruptedException, ExecutionException {
		for(;;) {
			if (!flag) {
				LockSupport.park();
				if (!flag) {
					System.out.println("-------------------------------------------------: " + flag);
				}
//			System.out.println("park():"+flag);

			} else {
				return getObj();
			}
		}

	}

	@Override
	public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return null;
	}

}
