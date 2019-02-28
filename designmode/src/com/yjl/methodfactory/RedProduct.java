package com.yjl.methodfactory;

public class RedProduct extends Product {
	
	private String color;
	
	public RedProduct(String color) {
		this.color = color;
	}

	@Override
	public void use() {
		System.out.println("使用" + color + "的产品");
	}

}
