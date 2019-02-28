package com.yjl.template;

public class Book extends Product {

	@Override
	public void registerProduct() {
		System.out.println("通过第三方注册产品");
	}

	@Override
	public void createProduct() {
		System.out.println("使用方案二生产产品");
	}

}
