package com.yjl.methodfactory;

public class RunMain {

	public static void main(String[] args) {
		
		ProductFactory ProductFactory = new RedFactory();
		Product createProduct = ProductFactory.createProduct();
		createProduct.use();

	}

}
