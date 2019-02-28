package com.yjl.template;

public abstract class Product {
	
	public void useProduct() {
		createProduct();
		registerProduct();
		System.out.println("使用产品");
	}
	
	public abstract void registerProduct();
	
	public abstract void createProduct();

}
