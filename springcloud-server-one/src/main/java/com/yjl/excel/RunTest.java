package com.yjl.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.yj.utils.ExportUtil;
import com.yjl.entity.User;

public class RunTest {

	public static void main(String[] args) throws FileNotFoundException {
		//准备数据
		List<User> list = new ArrayList<User>();
		for(int i = 1; i <= 10; i++) {
			User user = new User();
			user.setId(i);
			user.setUserName("yi_"+i);
			user.setPassword("rhedsf_"+i);
			list.add(user);
		}
		//执行导出
		String fileName = "自定义文件名称.xls";
		//ServletUtil su = new ServletUtil(fileName, req, resp);
		//su.poiExcelServlet();
		
		OutputStream out = new FileOutputStream("C:/"+fileName);
		String[] heads = {"id","用户名","密码"};//表头
		String[] cols = {"id","userName","password"};
		//这里传第几列字段是数字，从0开始
		int[] numerics = {0};
		//ServletUtil suresp = new ServletUtil(resp);
		ExportUtil.exportExcel(fileName, heads, cols, list,numerics, out);

	}

}
