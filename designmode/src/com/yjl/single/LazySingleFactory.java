package com.yjl.single;
/**
 * 懒加载单例模式
 * @author Administrator
 *
 */
public class LazySingleFactory {
	
	private static LazySingleFactory instance;
	
	private LazySingleFactory() {
		
	}
	
	public static LazySingleFactory getInstance() {
		if(instance == null) {
			instance = new LazySingleFactory();
		}
		return instance;
	}

}
