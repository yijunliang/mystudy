package com.yjl.methodfactory;

public class BlueFactory extends ProductFactory {

	@Override
	public Product createProduct() {
		return new BlueProduct("蓝色");
	}

}
