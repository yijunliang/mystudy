package com.yjl.factory;
/**
 * 产品工厂类
 * @author Administrator
 *
 */
public class ProductFactory {
	
	public static Product getProduct(String productColor) {
		if(null != productColor && productColor.equals("red")) {
			return new RedProduct();
		}
		if(null != productColor && productColor.equals("green")) {
			return new GreenProduct();
		}
		return null;
	}

}
