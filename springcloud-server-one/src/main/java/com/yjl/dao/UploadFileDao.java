package com.yjl.dao;

import java.util.List;
import java.util.Map;

import com.yjl.entity.ExcelInfo;

/**
 * 定义文件上传方法接口
 * @author Administrator
 *
 */
public interface UploadFileDao {
	/**
	 * 上传文件的时候，把文件信息添加到文件信息表中
	 * @param paramMap
	 */
	public void addFile(Map<String, Object> paramMap);
	/**
	 * 查询文件信息
	 * @param paramMap
	 */
	public List<ExcelInfo> findFileInfo(Map<String, Object> paramMap);
	/**
	 * 删除文件信息
	 * @param paramMap
	 */
	public void deleteFile(Map<String, Object> paramMap);
	/**
	 * 更新文件信息
	 * @param paramMap
	 */
	public void updateFile(Map<String, Object> paramMap);
	/**
	 * 获取到表中最大数据id
	 * @return
	 */
	public Integer findMaxId();
	/**
	 * 删除表
	 * @param tableName
	 */
	public void dropTable(String tableName);
	/**
	 * 创建模板表
	 * @param tableName
	 */
	public void createTemplateTable(String tableName);
	/**
	 * 为模板表添加字段(excel的列)
	 * @param paramMap
	 */
	public void addTemplateTableField(Map<String, Object> paramMap);
	/**
	 * 添加指定模板的数据
	 * @param paramMap
	 */
	public void addExcelData(Map<String, Object> paramMap);

}
