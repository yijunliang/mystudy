package com.yjl.pool;
/**
 * 测试静态变量有多少份，类的静态变量只有一份，不管有多少个实例。
 * 静态变量存放于方法区。编译时初始化。
 * @author Administrator
 *
 */
public class TestStatic {
	
	public static int count = 0;

	public static void main(String[] args) {
		
		System.out.println(count);
		TestStatic testStatic2 = new TestStatic();
		testStatic2.count = 10;
		System.out.println(count);
		TestStatic testStatic = new TestStatic();
		System.out.println(testStatic.count);

	}

}
