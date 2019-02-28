package com.yjl.pool;

public class JoinTest implements Runnable {

	public static void main(String[] args) {
		
		Thread thread = new Thread(new JoinTest());
		thread.setName("thread_A");
		try {
			//thread让出CPU执行时间，让thread1线程和主线程先执行
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.start();
		Thread thread1 = new Thread(new JoinTest());
		thread1.setName("thread_B");
		thread1.start();
		
		for(int i = 0; i < 100000; i++) {
			
		}
		System.out.println("主线程执行");
		

	}

	@Override
	public void run() {
		
		System.out.println("线程开始执行任务" + Thread.currentThread().getName() + " | " + Thread.currentThread().getThreadGroup());
	}

}
