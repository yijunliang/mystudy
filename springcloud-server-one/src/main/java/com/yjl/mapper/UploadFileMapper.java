package com.yjl.mapper;

import java.util.List;
import java.util.Map;

import com.yjl.entity.ExcelInfo;

/**
 * 文件上传mybatis执行SQL接口
 * @author Administrator
 *
 */
public interface UploadFileMapper {
	
	public List<ExcelInfo> findExcelInfo(Map<String, Object> paramMap);
	
	public void deleteExcelInfo(Map<String, Object> paramMap);
	
	public void updateExcelInfo(Map<String, Object> paramMap);
	
	public void addExcelInfo(Map<String, Object> paramMap);
	
	public Integer findMaxId();
	
	public void dropTable(String tableName);
	
	public void createTable(String tableName);
	
	public void addTableField(Map<String, Object> paramMap);
	
	public void addExcelData(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> queryExcelDataByModelName(Map<String, Object> paramMap);
	
	public int queryExcelDataTotal(Map<String, Object> paramMap);
	
	

}
