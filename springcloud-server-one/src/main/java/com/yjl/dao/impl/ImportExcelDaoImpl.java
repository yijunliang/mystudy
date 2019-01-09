package com.yjl.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjl.dao.ImportExcelDao;
import com.yjl.mapper.UploadFileMapper;
@Service
public class ImportExcelDaoImpl implements ImportExcelDao {
	@Autowired
	private UploadFileMapper uploadFileMapper;

	@Override
	public List<Map<String, Object>> queryExcelData(Map<String, Object> paramMap) {
		return uploadFileMapper.queryExcelDataByModelName(paramMap);
	}

	@Override
	public int queryExcelDataTotal(Map<String, Object> paramMap) {
		return uploadFileMapper.queryExcelDataTotal(paramMap);
	}

}
