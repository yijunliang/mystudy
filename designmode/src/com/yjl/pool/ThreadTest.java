package com.yjl.pool;
/**
 * 测试线程驱动的任务
 * @author Administrator
 *
 */
public class ThreadTest implements Runnable {
	protected int countDown = 10;
	//共享变量，所有线程都可以访问，存放于方法区，不管有多少个实例都只有一份。
	private static int taskCount = 0;
	//常量，一经赋值，不可修改，第一个线程访问时id为0，taskCount为1
	//第二个线程时，id为1，taskCount为2
	private final int id = taskCount++;
	
	public ThreadTest() {
	}
	public ThreadTest(int countDown) {
		this.countDown = countDown;
	}
	
	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "OVER" ) + "), ";
	}
    /**
     * 要执行的任务
     */
	@Override
	public void run() {
		while(countDown-- > 0) {
			System.out.print(status());
			//将CPU从一个线程转让给另一个线程的建议
			Thread.yield();
		}
	}
	
	public static void main(String[] args) {
		//测试1，只有main线程在执行任务
		/*ThreadTest threadTest = new ThreadTest();
		//不是启动另一个线程执行run任务，只是将run任务分给main线程，这里只有main线程存在
		threadTest.run();*/
		
		//测试2，main线程和t线程同时执行任务，所以更快
		/*Thread t =new Thread(new ThreadTest());
		t.start();
		System.out.println("等待  over ");*/
		
		//测试3
		for(int i = 0; i < 5; i++) {
			//每次都创建新的new ThreadTest()，即每次都需要初始化，id需重新赋值。
			new Thread(new ThreadTest()).start();
		}
		System.out.println("等待  over ");
	}

}
