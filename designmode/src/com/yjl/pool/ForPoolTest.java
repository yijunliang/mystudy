package com.yjl.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class ForPoolTest {
	
	public static void main(String[] args) {
		
		List<ApplyHistory> applyHistoryList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			ApplyHistory applyHistory = new ApplyHistory();
			applyHistory.setComment("a");
			applyHistoryList.add(applyHistory);
		}
		long currentTimeMillis1 = System.currentTimeMillis();
		for(ApplyHistory applyHistory : applyHistoryList) {
			System.out.println(applyHistory.getComment());
		}
		System.out.println("不使用线程池一共执行："+String.valueOf(System.currentTimeMillis()-currentTimeMillis1)+"ms");
		
		long currentTimeMillis = System.currentTimeMillis();
		ExecutorService pool = Executors.newFixedThreadPool(5);
		for(ApplyHistory applyHistory : applyHistoryList){
			Callable<ApplyHistory> run = new Callable<ApplyHistory>(){
				@Override
				public ApplyHistory call() throws InterruptedException{
					String comment = "A";
					applyHistory.setComment(comment);
					//Thread.sleep(1000);
					System.out.println(applyHistory.getComment());
					return applyHistory;
				}
			};
			pool.submit(run); 
			
		}
		pool.shutdown();
		System.out.println("使用线程池一共执行："+String.valueOf(System.currentTimeMillis()-currentTimeMillis)+"ms");
	}
 
}

