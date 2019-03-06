package com.cjc.ReadExcelData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig 
{
	XSSFWorkbook wb;
	XSSFSheet sheet;
	
	public void exceldata(String file) throws IOException
	{
		FileInputStream fis=new FileInputStream(file);
		wb=new XSSFWorkbook(fis);
	}
	
	public int getrowcount(String sheetname)
	{
		sheet=wb.getSheet(sheetname);
		int rows=sheet.getLastRowNum();
		int rowcount=rows+1;
		System.out.println("Number of Rows: "+rowcount);
		return rowcount;
	}
	
	public int getcolumncount(String sheetname)
	{
		sheet=wb.getSheet(sheetname);
		int rows1=sheet.getLastRowNum();
		int column=sheet.getRow(rows1).getLastCellNum();
		System.out.println("number of column: "+column);
		return column;
	}
	
	public String getdata(String sheetname,int row,int column)
	{
		sheet=wb.getSheet(sheetname);
		String s=sheet.getRow(row).getCell(column).getStringCellValue();
		return s;
	}
}
