package com.yjl.productandconsumer;
/**
 * 服务员类，等待厨师生产菜品，然后才能消费菜品
 * @author Administrator
 *
 */
public class WaitPerson implements Runnable {
	
	private Restaurant restaurant;
	
	public WaitPerson(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()) {
			try {
				//获得服务员对象锁
				synchronized(this) {
					System.out.println("线程"+Thread.currentThread().getName()+"获取到了服务员对象锁");
					while(restaurant.meal == null) {
						//wait方法一定要处于synchronized块中
						//服务员停止消费菜品
						System.out.println("线程"+Thread.currentThread().getName()+"wait阻塞，释放了服务员对象锁");
						wait();
					}
				}
				//获得厨师对象锁，此锁不释放，厨师就不能进入等待状态(等待消费菜品)
				synchronized(restaurant.chef) {
					System.out.println("线程"+Thread.currentThread().getName()+"获取到了厨师对象锁");
					//菜品置为空，通知厨师生产菜品
					System.out.println("服务员消费菜品：" + restaurant.meal);
					restaurant.meal = null;
					System.out.println("线程"+Thread.currentThread().getName()+"消费完菜品，释放了厨师对象锁，notifyAll通知厨师解除阻塞");
					restaurant.chef.notifyAll();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
