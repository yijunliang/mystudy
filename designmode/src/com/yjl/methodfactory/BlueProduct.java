package com.yjl.methodfactory;

public class BlueProduct extends Product {
	
	private String color;
	
	public BlueProduct(String color) {
		this.color =color;
	}

	@Override
	public void use() {
		System.out.println("使用" + color + "的产品");
	}

}
