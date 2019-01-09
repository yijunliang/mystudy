package com.yjl.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yj.utils.ExportUtil;
import com.yj.utils.ParamUtil;
import com.yjl.dao.ImportExcelDao;
import com.yjl.dao.UploadFileDao;
import com.yjl.entity.ExcelInfo;

@Controller
@RequestMapping("/export")
public class ExportExcelController {
	//@Autowired
	//private UserDao userDao;
	@Autowired
	private UploadFileDao uploadFileDao;
	@Autowired
	private ImportExcelDao importExcelDao;
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	@ResponseBody
	public Object exportExcel(@RequestParam Map<String, Object> paramMap/*, HttpServletRequest request, HttpServletResponse response*/) throws IOException {
		System.out.println("请求参数:"+paramMap);
		//String fileName = paramMap.get("fileName").toString();
		String modelName = ParamUtil.getStringParamByName(paramMap, "fileName");
		Map<String,Object> queryFileInfoMap = new HashMap<String, Object>();
		queryFileInfoMap.put("excelName", modelName);
		List<ExcelInfo> findFileInfo = uploadFileDao.findFileInfo(queryFileInfoMap);
		
		Map<String, Object> queryParamMap = ParamUtil.wrapupParamForPaging(paramMap);
		String tableName = findFileInfo.get(0).getTableName();
		queryParamMap.put("tableName", tableName);
		
		List<Map<String, Object>> list = importExcelDao.queryExcelData(queryParamMap);
		String[] heads = ParamUtil.stringToArray(paramMap, "title", ",");
		String[] cols  = ParamUtil.stringToArray(paramMap, "field", ",");
		int[] numerics = {0};
		HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
		HttpServletResponse response = (HttpServletResponse) paramMap.get("response");
		//设置响应头信息
		ExportUtil.setExportExcelResponseHeader(modelName, request, response);
		//可以考虑使用设计模式，以便需要使用不同的导出实现方式时能方便的替换
		ExportUtil.exportExcel(modelName, heads, cols, list, numerics, response.getOutputStream());
		return "success";
	}

}
