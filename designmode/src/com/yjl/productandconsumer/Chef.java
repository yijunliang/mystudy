package com.yjl.productandconsumer;

import java.util.concurrent.TimeUnit;

/**
 * 厨师类，生产菜品
 * @author Administrator
 *
 */
public class Chef implements Runnable {
	
	private int count = 0;
	
	private Restaurant restaurant;
	
	public Chef(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				//获取厨师实例的对象锁
				synchronized(this) {
					System.out.println("线程"+Thread.currentThread().getName()+"获取到了厨师对象锁");
					//菜品还没有被消费，就等待菜品被消费
					while(restaurant.meal != null) {
						System.out.println("线程"+Thread.currentThread().getName()+"wait阻塞，释放了厨师对象锁");
						wait();
					}
				}
				System.out.println("线程"+Thread.currentThread().getName()+"wait条件检测执行完毕，释放了厨师对象锁");
				//菜品数量增加1
				if(++count == 10) {
					System.out.println("生产菜品到达界限，关闭生产菜品");
					restaurant.executor.shutdownNow();
				}
				//获取服务员锁对象，服务员需要等待这个锁解锁，即不能进入对窗口是否有菜品的判断
				synchronized(restaurant.waitPerson) {
					System.out.println("线程"+Thread.currentThread().getName()+"获取到了服务员对象锁");
					//生产一份菜品(标记第几份)，通知服务员消费菜品
					restaurant.meal = new Meal(count);
					System.out.println("生产一份菜品!!("+count+")");
					System.out.println("线程"+Thread.currentThread().getName()+"生产完菜品，释放了服务员对象锁，notifyAll通知服务员解除阻塞");
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("chef 中断");
		}
	}

}
