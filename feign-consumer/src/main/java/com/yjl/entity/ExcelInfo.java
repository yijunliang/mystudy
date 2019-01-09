package com.yjl.entity;

import java.util.Date;

/**
 * 文件信息实体类
 * @author Administrator
 *
 */
public class ExcelInfo {
	
	private int id;
	
	private String excelName;
	
	private Date createDate;
	
	private String savePath;
	
	private String tableName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	

}
