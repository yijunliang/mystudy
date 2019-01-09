package com.yjl.dao;

import java.util.List;
import java.util.Map;

public interface ImportExcelDao {
	/**
	 * 查询模板数据
	 * @return
	 */
	public List<Map<String, Object>> queryExcelData(Map<String, Object> paramMap);
	/**
	 * 查询模板数据总数
	 * @param paramMap
	 * @return
	 */
	public int queryExcelDataTotal(Map<String, Object> paramMap);

}
