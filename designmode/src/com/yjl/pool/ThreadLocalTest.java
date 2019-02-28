package com.yjl.pool;

public class ThreadLocalTest implements Runnable {
    private static int count = 10;
    
    public synchronized void setCount(int count) {
    	count = count;
    }
    
    public synchronized int getCount() {
    	return count;
    }
    
    public synchronized void outPutCout() {
    	System.out.println(Thread.currentThread().getName()+" | "+getCount());
    	if(getCount() > 5) {
    		try {
    			System.out.println("线程"+Thread.currentThread().getName()+"让出锁进入等待");
    			//线程会进入等待状态(阻塞)，直到外部条件改变，并且这期间锁是释放的，也就是说别的线程
    			//能访问这个被synchronized修饰的方法
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	System.out.println("线程"+Thread.currentThread().getName()+"被唤醒了");
    	//唤醒wait()阻塞的线程
    	notify();
    }
	
	public static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};
	
	public static void main(String[] args) {
		//测试1，
		ThreadLocalTest threadLocalTest = new ThreadLocalTest();
		Thread threadA = new Thread(threadLocalTest);
		threadA.setName("threadA");
		Thread threadB = new Thread(threadLocalTest);
		threadB.setName("threadB");
		threadA.start();
		threadB.start();
		
		//测试2，
		count = 0;
	}

	@Override
	public void run() {
		//使用threadlocal之后线程A和线程B都有各自的计数，从0开始
		Integer integer = value.get();
		System.out.println(integer);
		integer++;
		System.out.println(Thread.currentThread().getName()+"修改count的值为：" + integer);
		
		outPutCout();
		
	}

}
