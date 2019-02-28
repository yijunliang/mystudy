package com.yjl.single;
/**
 * 单例模式，只能产生一个实例
 * @author Administrator
 *
 */
public class SingleFactory {
	
	private final static SingleFactory singleFactory = new SingleFactory();
	
	private SingleFactory() {
		
	}
	
	public static SingleFactory getInstance() {
		return singleFactory;
	}

}
