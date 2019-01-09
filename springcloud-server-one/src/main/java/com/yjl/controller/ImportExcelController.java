package com.yjl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yj.utils.FileUtil;
import com.yj.utils.ParamUtil;
import com.yj.utils.ResultUtil;
import com.yjl.dao.ImportExcelDao;
import com.yjl.dao.UploadFileDao;
import com.yjl.entity.ExcelInfo;

@Controller
@RequestMapping("/importExcelController")
public class ImportExcelController {
	@Autowired
	private UploadFileDao uploadFileDao;
	@Autowired
	private ImportExcelDao importExcelDao;
	/**
	 * 查询已导入的excel模板名(下拉框数据)
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/queryExcelModel", method = RequestMethod.GET)
	@ResponseBody
	public Object queryExcelModel(@RequestParam Map<String, Object> paramMap) {
		List<ExcelInfo> findFileInfo = uploadFileDao.findFileInfo(null);
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		for(ExcelInfo excelInfo : findFileInfo) {
			Map<String, Object> modelMap = new HashMap<String, Object>();
			modelMap.put("id", excelInfo.getId());
			modelMap.put("text", excelInfo.getExcelName());
			resultList.add(modelMap);
		}
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("results",resultList);
        map.put("pagination","");
        return JSON.toJSONString(map);
	}
	@RequestMapping(value="/importExcelData",method=RequestMethod.POST)
	@ResponseBody
	public String importExcelData(@RequestParam("fileData") MultipartFile file, HttpServletRequest request) throws Exception {
		String fileModelName = request.getParameter("fileModelName");//模板名
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("excelName", fileModelName);
		List<ExcelInfo> findFileInfo = uploadFileDao.findFileInfo(paramMap );
	    //根据模板名找出对应的模板数据信息
		String savePath = findFileInfo.get(0).getSavePath();
		String tableName = findFileInfo.get(0).getTableName();
		//读取上传的文件
		//导入数据到模板表中
		String fileName = file.getOriginalFilename();
		List<String[]> readFileList = FileUtil.readFile(fileName, file.getInputStream());
		System.out.println(fileName + "|" + fileModelName  + "|" + tableName + "|" + savePath);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("tableName", tableName);
		for(int i = 1; i < readFileList.size(); i++) {
			String inserValues = "'";
			String headName = "";
			String[] headArray = readFileList.get(i);
			int len = headArray.length;
			for(int j = 0; j < len; j++) {
				inserValues += headArray[j] + "','";
				headName += FileUtil.EXCEL_HEAD_NAME+j + ",";
				//列名
				param.put(FileUtil.EXCEL_HEAD_NAME+j, headArray[j]);
				//插入数据操作
			}
			inserValues = inserValues.substring(0, inserValues.length()-2);
			headName = headName.substring(0, headName.length()-1);
			System.out.println("inserValues的值:"+inserValues);
			System.out.println("headName的值:"+headName);
			param.put("inserValues", inserValues);
			param.put("headName", headName);
			uploadFileDao.addExcelData(param);
		}
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("results","success");
        map.put("pagination","");
        System.out.println("返回的json字符串是:"+JSON.toJSONString(map));
        return JSON.toJSONString(map);
		//获取到数据库列名
	}
	/**
	 * 显示模板数据信息
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value="/showExcelData",method = RequestMethod.POST)
	@ResponseBody
	public Object showExcelData(@RequestParam Map<String, Object> paramMap) {
		System.out.println(paramMap);
		String modelName = ParamUtil.getStringParamByName(paramMap, "modelName");
		Map<String,Object> queryFileInfoMap = new HashMap<String, Object>();
		queryFileInfoMap.put("excelName", modelName);
		List<ExcelInfo> findFileInfo = uploadFileDao.findFileInfo(queryFileInfoMap);
		
		Map<String, Object> queryParamMap = ParamUtil.wrapupParamForPaging(paramMap);
		String tableName = findFileInfo.get(0).getTableName();
		queryParamMap.put("tableName", tableName);
		List<Map<String, Object>> list = importExcelDao.queryExcelData(queryParamMap);
		int total = importExcelDao.queryExcelDataTotal(paramMap);
		return ResultUtil.wrapupResult(list, total);
	}
	//获得模板列名和对应数据库列名
	@RequestMapping("/queryExcelHeadsAndDataIndexs")
	@ResponseBody
	public Object queryExcelHeadsAndDataIndexs(@RequestParam Map<String, Object> paramMap) throws Exception {
		//根据模板名获取模板文件保存位置等信息
		String modelName = ParamUtil.getStringParamByName(paramMap, "modelName");
		System.out.println(modelName);
		Map<String,Object> queryFileInfoMap = new HashMap<String, Object>();
		queryFileInfoMap.put("excelName", modelName);
		List<ExcelInfo> findFileInfo = uploadFileDao.findFileInfo(queryFileInfoMap);
		String excelName = findFileInfo.get(0).getExcelName();
		String savePath = findFileInfo.get(0).getSavePath();
		
		List<String[]> readFile = FileUtil.readFile(excelName, savePath);
		//excel表头
		String[] heads = readFile.get(0);
		
		List<String> headList = new ArrayList<String>();
		List<String> dataIndexList = new ArrayList<String>();
		
		for(int i = 0; i < heads.length; i++) {
			headList.add(heads[i]);
			String tmepHead = FileUtil.EXCEL_HEAD_NAME + i;
			dataIndexList.add(tmepHead);
		}
		//Map<String,List<String>> resultMap = new HashMap<String, List<String>>();
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("head", headList);
		resultMap.put("dataIndex", dataIndexList);
		//根据文件名和路径读取excel表
		//return JSON.toJSON(resultMap);
		//System.out.println(JSONObject.toJSONString(resultMap));
		return JSONObject.toJSONString(resultMap);
	}

}
