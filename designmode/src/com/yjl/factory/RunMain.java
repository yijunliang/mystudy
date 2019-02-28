package com.yjl.factory;

public class RunMain {

	public static void main(String[] args) {
		//不使用工厂模式的情况下，使用产品的方式
		Product redProduct = new RedProduct();
		redProduct.use();
		//如果需要,改变使用的产品那么就需要修改源码中new实例的位置，此时可能许多地方都使用了new RedProduct();
		//那么就要一一修改这些地方,如果是使用工厂模式那么就只需要修改工厂类即可
		Product product = ProductFactory.getProduct("green");
		product.use();

	}

}
