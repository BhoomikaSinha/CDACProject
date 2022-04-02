package com.svs.etracker.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.svs.etracker.model.UserExpense;


@Service
public class WriteExcel {

	private static void createHeaderRow(HSSFSheet sheet) {
		HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		HSSFFont font = sheet.getWorkbook().createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 16);
		cellStyle.setFont(font);

		HSSFRow row = sheet.createRow(0);
		HSSFCell cellTitle = row.createCell(1);


		cellTitle.setCellStyle(cellStyle);
		cellTitle.setCellValue("Expense Name");
		sheet.autoSizeColumn(1);

		HSSFCell cellAuthor = row.createCell(2);
		cellAuthor.setCellStyle(cellStyle);
		cellAuthor.setCellValue("Created Date");
		sheet.autoSizeColumn(2);

		HSSFCell cellPrice = row.createCell(3);
		cellPrice.setCellStyle(cellStyle);
		cellPrice.setCellValue("Comments");
		sheet.autoSizeColumn(3);

		HSSFCell comments = row.createCell(5);
		comments.setCellStyle(cellStyle);
		comments.setCellValue("Amount");
		sheet.autoSizeColumn(4);

		HSSFCell category = row.createCell(4);
		category.setCellStyle(cellStyle);
		category.setCellValue("Category");
		sheet.autoSizeColumn(5);

	}

	private void writeBook(HSSFWorkbook workbook, UserExpense expense, HSSFRow row) {

		HSSFCell cell = row.createCell(1);
		String expenseName = expense.getExpenseName();
		cell.setCellValue(expenseName);

		cell = row.createCell(2);
		Date createdDate = (Date) expense.getCreatedDate();
		HSSFDataFormat format = workbook.createDataFormat();
		HSSFCellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
		cell.setCellStyle(dateStyle);
		cell.setCellValue(createdDate);

		cell = row.createCell(3);
		String comments = expense.getComments();
		cell.setCellValue(comments);

		cell = row.createCell(5);
		double amount = expense.getAmount();
		cell.setCellValue(amount);

		cell = row.createCell(4);
		String category = expense.getUserCategory().getCategory();
		cell.setCellValue(category);
	}

	public void writeExcel(List<UserExpense> listBook, String excelFilePath) throws IOException {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("FirstWorkSheet");

		createHeaderRow(sheet);

		
		double total = 0;
		int rowCount = 1;
		for(int i=0;i<listBook.size();i++) {
			UserExpense expense = listBook.get(i);
			HSSFRow row = sheet.createRow(++rowCount);
			writeBook(workbook,expense, row);
			total = total + expense.getAmount();
		}

		HSSFRow rowTotal = sheet.createRow(rowCount + 2);
		HSSFCell cellTotalText = rowTotal.createCell(4);
		cellTotalText.setCellValue("Total:");

		HSSFCell cellTotal = rowTotal.createCell(5);
		cellTotal.setCellValue(total);

		workbook.write(new FileOutputStream(excelFilePath));
		workbook.close();

	}



	/*
	private List<Book> getListBook() {
		Book book1 = new Book("Head First Java", "Kathy Serria", 79);
		Book book2 = new Book("Effective Java", "Joshua Bloch", 36);
		Book book3 = new Book("Clean Code", "Robert Martin", 42);
		Book book4 = new Book("Thinking in Java", "Bruce Eckel", 35);

		List<Book> listBook = Arrays.asList(book1, book2, book3, book4);

		return listBook;
	}*/

	/*public static void main(String[] args) throws IOException {
		WriteExcel excelWriter = new WriteExcel();
		List<Book> listBook = excelWriter.getListBook();
		String excelFilePath = "excel.xls";
		excelWriter.writeExcel(listBook, excelFilePath);
		System.out.println("Success!!!!!!");
	}*/
}