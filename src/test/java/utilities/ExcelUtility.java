package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public FileInputStream fis;
	String path;
	public Workbook workbook;
	public Sheet sheet;
	public Row row;
	public Cell cell;
	public FileOutputStream fos;
	public CellStyle style;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();

		workbook.close();
		fis.close();
		return rowcount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellcount;

	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter(); // DataFormatter contains methods for formatting the value stored
														// in a Cell

		String data;
		try {
			data = formatter.formatCellValue(cell); // returns the formatted value of a cell as a String regardless of
													// the cell type.
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fis.close();
		return data;
	}

	public void setCellDta(String sheetName, int rownum, int colnum, String data) throws IOException {
		File file = new File(path);
		if (!file.exists()) {

			workbook = new XSSFWorkbook();
			fos = new FileOutputStream(path);
			workbook.write(fos);
		}
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		if (workbook.getSheetIndex(sheetName) == -1) // if sheet not exists then create new sheet
			;// if sheet not exits then create new sheet
		workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);

		if (sheet.getRow(rownum) == null)// if row not exists then create new Row
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fos.close();

	}

	public void fillGreanColor(String sheetName, int rownum, int colnum) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}
	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}

}
