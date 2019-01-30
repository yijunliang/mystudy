package com.yjl.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorTest2 {
	private static final int loopNum = 1*10;  
    
    public static void main(String args[]) throws InterruptedException {  
    	ExecutorTest2 TestThreadPool = new ExecutorTest2();  
        long bt = System.currentTimeMillis();  
        TestThreadPool.m1();  
        long et2 = System.currentTimeMillis();  
        System.out.println("[1]耗时:"+(et2 - bt)+ "ms");  
        //Thread thread = new Thread();  
        long at = System.currentTimeMillis();  
        TestThreadPool.m2();
        long et3 = System.currentTimeMillis();
        System.out.println("[2]耗时:"+(et3 - at)+ "ms");
        
        long startTime3 = System.currentTimeMillis();  
        TestThreadPool.m3();
        long endTime3 = System.currentTimeMillis();
        System.out.println("[3]耗时:"+(endTime3 - startTime3)+ "ms");
    }  
    /**
     * 使用newCachedThreadPool线程池处理任务
     */
    public void m1() {
        ExecutorService pool = Executors.newCachedThreadPool();  
        for (int index = 0; index < loopNum; index++) {  
            Runnable run = new Runnable() {  
                public void run() {  
                    try {  
                        new Thread().sleep(1000);  //模拟耗时操作
                    	System.out.println("[1]" + Thread.currentThread().getName());
                    } catch (Exception e) {  
                    }  
                }  
            }; 
            pool.execute(run);  
        }  
        System.out.println("[1] done!");
        pool.shutdown();  
    }  
    
    public void m3() {
    	ExecutorService pool = Executors.newFixedThreadPool(5);
    	//ExecutorService pool = new ThreadPoolExecutor(2, 4, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(3),Executors.defaultThreadFactory(), new AbortPolicy());
    	for (int index = 0; index < loopNum; index++) { 
    		Runnable task = new Runnable() {
    			@Override
    			public void run() {
    				try {
    					new Thread().sleep(1000);  //模拟耗时操作
    					System.out.println("[3]" + Thread.currentThread().getName());
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
    			}
    		};
    		Future<?> submit = pool.submit(task);
    		/*try {//如果需要输出执行结果，将会大大影响执行效率，会需要等待所有线程执行完
    			if(submit.get() == null) {
    				System.out.println("任务完成");
    			}
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		} catch (ExecutionException e) {
    			e.printStackTrace();
    		}*/
    	}
    	System.out.println("[3] done!");
    	pool.shutdown(); 
    }
    /**
     * 不使用线程池，单线程处理任务
     */
    public void m2() { 
    	AtomicInteger connectionIds = new AtomicInteger(0);
        for (int index = 0; index < loopNum; index++) {  
            try {  
                new Thread().sleep(1000);  //模拟耗时操作
                System.out.println("[2]" + Thread.currentThread().getName());
                
            } catch (Exception e) {  
                e.printStackTrace();  
            } 
        }  
        System.out.println("[2] done!");
    }  
}

