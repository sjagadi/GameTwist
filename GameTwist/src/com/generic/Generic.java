package com.generic;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Generic 
{
	public static String getConfigValue(String Key)
	{
		String value = "";
		try
		{
			String path = "./Config.properties";
			FileInputStream fis = new FileInputStream(path);
			Properties p = new Properties();
			p.load(fis);
			value = p.getProperty(Key);
		}
		catch(Exception e)
		{
			
		}
		return value;
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public static String getCellValue(int row, int cell)
	{
		String xlPath = "./GreenTube/xlsx/Login.xlsx";
		String sheetName = "Login";
		String v = "";
		try
		{
			FileInputStream fis = new FileInputStream(xlPath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(row);
			Cell c = r.getCell(cell);
			
			if(c.getCellType()==c.CELL_TYPE_NUMERIC)
			{
				v = ""+c.getNumericCellValue();
			}
			else
			{
				v = c.getStringCellValue();
			}
		}
		catch(Exception e)
		{
			
		}
		return v;
	}
	public static int getRowCount(String xlPath, String sheetName)
	{
		int rc = 0;
		try
		{
			FileInputStream fis = new FileInputStream(xlPath);
			Workbook wb = WorkbookFactory.create(fis);
			rc = wb.getSheet(sheetName).getLastRowNum();
		}
		catch(Exception e)
		{
			
		}
		return rc;
	}
}
