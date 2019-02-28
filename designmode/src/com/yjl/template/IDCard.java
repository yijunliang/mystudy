package com.yjl.template;

public class IDCard extends Product {

	@Override
	public void registerProduct() {
		System.out.println("到工商局注册产品");
	}

	@Override
	public void createProduct() {
		System.out.println("使用方案一生产产品");
	}

}
