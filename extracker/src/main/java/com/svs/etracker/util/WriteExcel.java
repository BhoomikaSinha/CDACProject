package com.svs.etracker.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Table.Cell;
import com.svs.etracker.model.UserExpense;


@Service
public class WriteExcel {

	@Value("${rootPath}")
	private String rootPath;

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
		
		writeImageOnexcel(workbook,sheet,"/PieChart.png",15,0);
		writeImageOnexcel(workbook,sheet,"/LineChart.png",18,13);



		workbook.write(new FileOutputStream(excelFilePath));
		workbook.close();

	}

	public void writeYearAnalyticsChartOnExcel( String excelFilePath) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("ChartWorkSheet");

		HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		HSSFFont font = sheet.getWorkbook().createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 18);
		cellStyle.setFont(font);

		HSSFRow row = sheet.createRow(0);
		HSSFCell cellTitle = row.createCell(6);


		cellTitle.setCellStyle(cellStyle);
		cellTitle.setCellValue("Yearly Analytics");

		writeAnalyticsImageOnexcel(workbook,sheet,"/PieChart.png",5,5,20,2,8);
		writeAnalyticsImageOnexcel(workbook,sheet,"/BarChart.png",5,5,20,8,16);
		writeAnalyticsImageOnexcel(workbook,sheet,"/MonthlyCategoryBarChart.png",10,20,35,1,16);
		writeAnalyticsImageOnexcel(workbook,sheet,"/LineChart.png",20,35,55,1,16);



		workbook.write(new FileOutputStream(excelFilePath));
		workbook.close();
	}

	private void writeAnalyticsImageOnexcel(HSSFWorkbook workbook ,HSSFSheet sheet,String imagePath,int imageRow,int row1,int row2,int cell1,int cell2) throws IOException {
		 InputStream inputStream = new FileInputStream(rootPath+imagePath);
		   //Get the contents of an InputStream as a byte[].
		   byte[] bytes = IOUtils.toByteArray(inputStream);
		   //Adds a picture to the workbook
		   int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
		   //close the input stream
		   inputStream.close();
		   //Returns an object that handles instantiating concrete classes
		   CreationHelper helper = workbook.getCreationHelper();
		   //Creates the top-level drawing patriarch.
		   Drawing drawing = sheet.createDrawingPatriarch();

		   //Create an anchor that is attached to the worksheet
		   ClientAnchor anchor = helper.createClientAnchor();

		   //create an anchor with upper left cell _and_ bottom right cell
		   anchor.setCol1(cell1); //Column B
		   anchor.setRow1(row1); //Row 3
		   anchor.setCol2(cell2); //Column C
		   anchor.setRow2(row2); //Row 4

		   //Creates a picture
		   Picture pict = drawing.createPicture(anchor, pictureIdx);

		   //Reset the image to the original size
		   //pict.resize(); //don't do that. Let the anchor resize the image!

		   //Create the Cell B3
		   HSSFCell cell11 = sheet.createRow(imageRow).createCell(1);
	}


	private void writeImageOnexcel(HSSFWorkbook workbook ,HSSFSheet sheet,String imagePath,int row,int cell) throws IOException {
		 InputStream inputStream = new FileInputStream(rootPath+imagePath);
		   //Get the contents of an InputStream as a byte[].
		   byte[] bytes = IOUtils.toByteArray(inputStream);
		   //Adds a picture to the workbook
		   int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
		   //close the input stream
		   inputStream.close();
		   //Returns an object that handles instantiating concrete classes
		   CreationHelper helper = workbook.getCreationHelper();
		   //Creates the top-level drawing patriarch.
		   Drawing drawing = sheet.createDrawingPatriarch();

		   //Create an anchor that is attached to the worksheet
		   ClientAnchor anchor = helper.createClientAnchor();

		   //create an anchor with upper left cell _and_ bottom right cell
		   anchor.setCol1(8); //Column B
		   anchor.setRow1(cell+4); //Row 3
		   anchor.setCol2(15); //Column C
		   anchor.setRow2(cell+15); //Row 4

		   //Creates a picture
		   Picture pict = drawing.createPicture(anchor, pictureIdx);

		   //Reset the image to the original size
		   //pict.resize(); //don't do that. Let the anchor resize the image!

		   //Create the Cell B3
		   HSSFCell cell1 = sheet.createRow(row).createCell(1);
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