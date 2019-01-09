package com.yjl.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * 读取excel文件
 * @author Administrator
 *
 */
public class ReadFileOfExcel extends ReadFile {
	
	private static final String EXCEL_HEAD_NAME = "head_";

	@Override
	public List<String[]> readFile(String fileName, String filePath) throws Exception {
		//检查文件  
		if(checkFile(fileName,filePath)) {

			//获得Workbook工作薄对象  
			Workbook workbook = getWorkBook(fileName, filePath);  
			//创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回  
			List<String[]> list = new ArrayList<String[]>();  
			if(workbook != null){
				//sheet表数量
				int sheets = workbook.getNumberOfSheets();
				for(int sheetNum = 0;sheetNum < sheets;sheetNum++){  
					//获得当前sheet工作表  
					Sheet sheet = workbook.getSheetAt(sheetNum);  
					if(sheet == null){  
						continue;  
					}  
					//获得当前sheet的开始行  
					int firstRowNum  = sheet.getFirstRowNum();  
					//获得当前sheet的结束行  
					int lastRowNum = sheet.getLastRowNum();  
					//循环除了第一行的所有行  
					for(int rowNum = firstRowNum;rowNum <= lastRowNum;rowNum++){  
						//获得当前行  
						Row row = sheet.getRow(rowNum);  
						if(row == null){  
							continue;  
						}  
						//获得当前行的开始列  
						int firstCellNum = row.getFirstCellNum();  
						//获得当前行的列数  
						int lastCellNum = row.getPhysicalNumberOfCells();  
						String[] cells = new String[lastCellNum];  
						if(firstCellNum < 0 || lastCellNum <= 0) {
							continue; 
						}
						//循环当前行  
						for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){  
							Cell cell = row.getCell(cellNum);  
							cells[cellNum] = getCellValue(cell);  
						}  
						list.add(cells);  
					}  
				}  
				workbook.close();  
			}  
			return list;
		}
		return null;
	}
	
	public List<String[]> readFile(String fileName, InputStream inputStream) throws Exception {
			//获得Workbook工作薄对象  
			Workbook workbook = getWorkBook(fileName, inputStream);  
			//创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回  
			List<String[]> list = new ArrayList<String[]>();  
			if(workbook != null){
				//sheet表数量
				int sheets = workbook.getNumberOfSheets();
				for(int sheetNum = 0;sheetNum < sheets;sheetNum++){  
					//获得当前sheet工作表  
					Sheet sheet = workbook.getSheetAt(sheetNum);  
					if(sheet == null){  
						continue;  
					}  
					//获得当前sheet的开始行  
					int firstRowNum  = sheet.getFirstRowNum();  
					//获得当前sheet的结束行  
					int lastRowNum = sheet.getLastRowNum();  
					//循环除了第一行的所有行  
					for(int rowNum = firstRowNum;rowNum <= lastRowNum;rowNum++){  
						//获得当前行  
						Row row = sheet.getRow(rowNum);  
						if(row == null){  
							continue;  
						}  
						//获得当前行的开始列  
						int firstCellNum = row.getFirstCellNum();  
						//获得当前行的列数  
						int lastCellNum = row.getPhysicalNumberOfCells();  
						String[] cells = new String[lastCellNum];  
						if(firstCellNum < 0 || lastCellNum <= 0) {
							continue; 
						}
						//循环当前行  
						for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){  
							Cell cell = row.getCell(cellNum);  
							cells[cellNum] = getCellValue(cell);  
						}  
						list.add(cells);  
					}  
				}  
				workbook.close();  
			}  
			return list;
	}
	
	public static boolean checkFile(String fileName, String filePath) {
		File file = new File(filePath+fileName);
		System.out.println(filePath+fileName);
		boolean exists = file.exists();
		//判断文件是否存在
		if(!exists) {
			System.out.println("文件不存在!");
			return false;
		}
        //判断文件是否是excel文件  
        if(!fileName.endsWith("xls") && !fileName.endsWith("xlsx")){  
        	System.out.println(fileName + "不是excel文件");  
            return  false;
        }  
        return true;
    }
	
	/**
	 * 通过本地路径读取文件
	 * @param fileName
	 * @param filePath
	 * @return
	 */
	public static Workbook getWorkBook(String fileName, String filePath) {  
        //创建Workbook工作薄对象，表示整个excel  
        Workbook workbook = null;  
        try {  
        	//获取excel文件的io流  
        	InputStream is = new FileInputStream(filePath+fileName);
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
            if(fileName.endsWith("xls")){  
                //2003  
                workbook = new HSSFWorkbook(is);  
            }else if(fileName.endsWith("xlsx")){  
                //2007  
                workbook = new XSSFWorkbook(is);  
            }  
        } catch (IOException e) {  
            System.out.println(e.getMessage());  
        }  
        return workbook;  
    }
	
	
	public static Workbook getWorkBook(String fileName, InputStream is) {  
        //创建Workbook工作薄对象，表示整个excel  
        Workbook workbook = null;  
        try {  
        	//获取excel文件的io流  
        	//InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
            if(fileName.endsWith("xls")){  
                //2003  
                workbook = new HSSFWorkbook(is);  
            }else if(fileName.endsWith("xlsx")){  
                //2007  
                workbook = new XSSFWorkbook(is);  
            }  
        } catch (IOException e) {  
            System.out.println(e.getMessage());  
        }  
        return workbook;  
    }
	
	
	
    public static String getCellValue(Cell cell){  
        String cellValue = "";  
        if(cell == null){  
            return cellValue;  
        }  
        //把数字当成String来读，避免出现1读成1.0的情况  
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){  
            cell.setCellType(Cell.CELL_TYPE_STRING);  
        }  
        //判断数据的类型  
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字  
                cellValue = String.valueOf(cell.getNumericCellValue());  
                break;  
            case Cell.CELL_TYPE_STRING: //字符串  
                cellValue = String.valueOf(cell.getStringCellValue());  
                break;  
            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
                cellValue = String.valueOf(cell.getBooleanCellValue());  
                break;  
            case Cell.CELL_TYPE_FORMULA: //公式  
                cellValue = String.valueOf(cell.getCellFormula());  
                break;  
            case Cell.CELL_TYPE_BLANK: //空值   
                cellValue = "";  
                break;  
            case Cell.CELL_TYPE_ERROR: //故障  
                cellValue = "非法字符";  
                break;
            default:  
                cellValue = "未知类型";  
                break;  
        }  
        return cellValue;  
    }  
    
    public static Map<String,Object> getExcelHead(String[] heads) {
    	Map<String,Object> headMap = new HashMap<String, Object>();
    	for(int i = 0; i < heads.length; i++) {
    		headMap.put(EXCEL_HEAD_NAME+i, heads[i]);
    	}
    	return headMap;
    }
    
    public static void main(String[] args) {
    	ReadFileOfExcel readFileOfExcel = new ReadFileOfExcel();
    	try {
			List<String[]> readFile = readFileOfExcel.readFile("测试.xls", "D://框架学习//springboot前后端不分离例子//springbootstudy2-3//springbootstudy2//src//main//webapp//uploadFile//");
			for(String str : readFile.get(0)) {
				System.out.println(str);
			}
			Map<String, Object> excelHead = getExcelHead(readFile.get(0));
			for(int i = 0; i < excelHead.size(); i++) {
				System.out.println(excelHead.get(EXCEL_HEAD_NAME+i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
