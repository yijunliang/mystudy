package com.yjl.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.yj.utils.FileUtil;
import com.yjl.dao.UploadFileDao;
import com.yjl.entity.ExcelInfo;

/**
 * 文件上传控制器类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/file")
public class UploadFileController {
	@Autowired
	private UploadFileDao uploadFileDao;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@RequestMapping("/uploadFile")
	@ResponseBody
	public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		//String contentType = file.getContentType();
	    String fileName = file.getOriginalFilename();
	    //System.out.println("fileName-->" + fileName);
	    //System.out.println("getContentType-->" + contentType);
	    String filePath = request.getSession().getServletContext().getRealPath("uploadFile/");
	    //System.out.println("filePath-->" + filePath);
	    String msg = "文件上传成功";
	    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
	    try {
	    	//查询文件是否存在的参数
	    	Map<String, Object> paramMap = new HashMap<String, Object>();
	        paramMap.put("excelName", fileName);
	        paramMap.put("createDate", new Date());
	        paramMap.put("savePath", filePath);
	        //paramMap.put("tableName", FileUtil.EXCEL_TEMPLATE_NAME);//模板对应的数据库表名
			//List<ExcelInfo> fileInfo = uploadFileDao.findFileInfo(paramMap);
			if(fileIsExist(fileName)) {
				msg = "该文件已存在，请勿重复上传!!";
				return JSON.toJSONString(msg);
			}
	        //最大模板数id
	       /* Integer maxId = uploadFileDao.findMaxId();
	        int nextId = 0;
	        if(null != maxId) {
	        	nextId = maxId + 1;
	        }*/
	        String tableName = FileUtil.EXCEL_TEMPLATE_NAME+getNextId();
	        paramMap.put("tableName", tableName);
			//文件上传成功
	        FileUtil.uploadFile(file.getBytes(), filePath, fileName);
			System.out.println(filePath+fileName);
			List<String[]> readFileList = FileUtil.readFile(fileName, filePath);
			System.out.println(readFileList);
			for(String[] strArray : readFileList) {
				for(String str : strArray) {
					System.out.println("excel数据:"+str);
				}
			}
			//删除表
			uploadFileDao.dropTable(tableName);
			//创建模板表
			uploadFileDao.createTemplateTable(tableName);
			//生成字段跟表的列对应关系
			Map<String, Object> excelHead = FileUtil.getExcelHead(readFileList.get(0));
			Set<String> keySet = excelHead.keySet();
			//为表添加字段
			for(String key : keySet) {
				Map<String, Object> excelHeadMap = new HashMap<String, Object>();
				excelHeadMap.put("tableName", tableName);
				excelHeadMap.put("excelHeadName", key);
				uploadFileDao.addTemplateTableField(excelHeadMap);
			}
			//添加记录到信息表
			uploadFileDao.addFile(paramMap);
			transactionManager.commit(status);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	transactionManager.rollback(status);
	    	return JSON.toJSONString("文件上传失败");
	    }
	    //返回json
	    return JSON.toJSONString(msg);
	}
	/**
	 * 查询文件是否存在
	 * @param excelName
	 * @return
	 */
	private boolean fileIsExist(String excelName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("excelName",excelName);
		List<ExcelInfo> fileInfo = uploadFileDao.findFileInfo(paramMap );
		if(fileInfo.size() > 0) {
			System.out.println("文件路径:" + fileInfo.get(0).getSavePath());
			return true;
		}
		return false;
	}
	/**
	 * 获取数据库中最大id的下一个id，用来拼接模板名
	 * @return
	 */
	private int getNextId() {
		 Integer maxId = uploadFileDao.findMaxId();
		 int nextId = 0;
		 if(null != maxId) {
			 nextId = maxId + 1;
		 }
		 return nextId;
	}
}
