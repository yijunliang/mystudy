package com.yjl.productandconsumer;
/**
 * 菜品类，所谓的生产者，消费者其实质是线程之间的协作(通信)
 * @author Administrator
 *
 */
public class Meal {
	
	private final int orderNum;
	
	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}
	//重写对象的toString方法，按照自定义格式输出。否则将会按照Object类的toString输出一些看不懂的hash码
	public String toString() {
		return "Meal " + orderNum;
	}

}
