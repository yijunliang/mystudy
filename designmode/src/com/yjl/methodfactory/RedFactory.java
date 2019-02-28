package com.yjl.methodfactory;

public class RedFactory extends ProductFactory {
	
	public Product createProduct() {
		return new RedProduct("红色");
	}

}
