package com.yjl.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjl.dao.UploadFileDao;
import com.yjl.entity.ExcelInfo;
import com.yjl.mapper.UploadFileMapper;
@Service
public class UploadFileDaoImpl implements UploadFileDao {
	@Autowired
	private UploadFileMapper uploadFileMapper;

	@Override
	public void addFile(Map<String, Object> paramMap) {
		uploadFileMapper.addExcelInfo(paramMap);
	}

	@Override
	public List<ExcelInfo> findFileInfo(Map<String, Object> paramMap) {
		
		return uploadFileMapper.findExcelInfo(paramMap);
	}

	@Override
	public void deleteFile(Map<String, Object> paramMap) {
		uploadFileMapper.deleteExcelInfo(paramMap);

	}

	@Override
	public void updateFile(Map<String, Object> paramMap) {
		uploadFileMapper.updateExcelInfo(paramMap);
	}

	@Override
	public Integer findMaxId() {
		return uploadFileMapper.findMaxId();
	}

	@Override
	public void createTemplateTable(String tableName) {
		uploadFileMapper.createTable(tableName);
	}

	@Override
	public void addTemplateTableField(Map<String, Object> paramMap) {
		uploadFileMapper.addTableField(paramMap);
	}

	@Override
	public void dropTable(String tableName) {
		uploadFileMapper.dropTable(tableName);
	}

	@Override
	public void addExcelData(Map<String, Object> paramMap) {
		uploadFileMapper.addExcelData(paramMap);
	}

}
