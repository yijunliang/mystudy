package com.yjl.single;

public class RunMain {

	public static void main(String[] args) {
		SingleFactory singleFactory = SingleFactory.getInstance();
		System.out.println(singleFactory);
		SingleFactory singleFactory1 = SingleFactory.getInstance();
		System.out.println(singleFactory1);
		System.out.println(singleFactory == singleFactory1);
		
		LazySingleFactory instance = LazySingleFactory.getInstance();
		LazySingleFactory instance1 = LazySingleFactory.getInstance();
		System.out.println(instance == instance1);
		
		System.out.println(Test.NAME);
		
		Test.myTest();
	}

}
