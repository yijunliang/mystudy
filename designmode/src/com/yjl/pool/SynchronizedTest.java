package com.yjl.pool;
/**
 * 同步测试
 * @author Administrator
 *
 */
public class SynchronizedTest implements Runnable {
	
	private final String NAME = "yijunliang";
	
	private synchronized String getName() {
		System.out.println(Thread.currentThread().getName()+"进入获取名字的同步方法，然后等待10000ms");
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		waitTest();
		return NAME;
	}
	
	public synchronized void waitTest() {
		System.out.println(Thread.currentThread().getName()+"进入waitTest()方法");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//测试1，多线程对同一个实例的同步方法的访问会阻塞，即对访问synchronizedTest的对象锁会阻塞。
		/*SynchronizedTest synchronizedTest = new SynchronizedTest();
		//线程A持有的是synchronizedTest对象锁
		Thread threadA = new Thread(synchronizedTest);
		threadA.setName("threadA");
		//线程B也是要持有synchronizedTest对象锁，所以线程A和线程B会相互阻塞
		Thread threadB = new Thread(synchronizedTest);
		threadB.setName("threadB");
		threadA.start();
		threadB.start();*/
		
		//测试2，多线程对多个实例的synchronized方法不会阻塞，只会阻塞各自的实例锁
		/*SynchronizedTest objC = new SynchronizedTest();
		SynchronizedTest objD = new SynchronizedTest();
		//线程C持有的是objC的锁
		Thread threadC = new Thread(objC);
		threadA.setName("threadC");
		//线程D持有的是objD的对象锁，所以线程C和线程D不会相互阻塞。
		Thread threadD = new Thread(objD);
		threadB.setName("threadD");
		threadC.start();
		threadD.start();*/
		
		//测试3，多线程对同一个实例的同步方法的访问会阻塞，即对访问synchronizedTest的对象锁会阻塞。
		SynchronizedTest synchronizedTest = new SynchronizedTest();
		//线程A持有的是synchronizedTest对象锁
		Thread threadA = new Thread(synchronizedTest);
		threadA.setName("threadA");
		//线程B也是要持有synchronizedTest对象锁，所以线程A和线程B会相互阻塞
		Thread threadB = new Thread(synchronizedTest);
		threadB.setName("threadB");
		threadA.start();
		threadB.start();
		//主线程调用waitTest需等A、B线程所有synchronized方法执行完毕才能执行
		synchronizedTest.waitTest();
		
		
	}

	@Override
	public synchronized void run() {
		System.out.println("线程"+Thread.currentThread().getName()+"开始获取名字:"+getName());
	}

}
