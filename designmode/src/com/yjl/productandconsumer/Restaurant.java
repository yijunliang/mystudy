package com.yjl.productandconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 菜品窗口类，服务员要到窗口取菜品，厨师要生产菜品放到窗口
 * @author Administrator
 *
 */
public class Restaurant {
	//默认值为null,default访问权限在本包中可见
	Meal meal;//菜品
	
	Chef chef = new Chef(this);//厨师，拥有放菜品的窗口(this)
	
	ExecutorService executor = Executors.newCachedThreadPool();
	//服务员，拥有取菜品的窗口(this)，即和厨师的窗口是一样的
	WaitPerson waitPerson = new WaitPerson(this);
	
	public Restaurant() {
		executor.execute(chef);
		executor.execute(waitPerson);
	}
	
	public static void main(String[] args) {
		new Restaurant();
	}
	
	

}
