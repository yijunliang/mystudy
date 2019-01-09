package com.yjl.excel;

import java.io.InputStream;
import java.util.List;

/**
 * 读取文件抽象类
 * @author Administrator
 *
 */
public abstract class ReadFile {
	
	public abstract List<String[]> readFile(String fileName, String filePath) throws Exception ;

	public abstract List<String[]> readFile(String fileName, InputStream inputStream) throws Exception ;

}
