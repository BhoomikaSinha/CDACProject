package com.svs.etracker.util;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections4.map.HashedMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.svs.etracker.model.UserExpense;

@Service
public class PieChart {

	@Value("${rootPath}")
	private String rootPath;

	public void createAndSavePieChart(Map<String, Double> pieData) throws IOException{

		DefaultPieDataset dataset = new DefaultPieDataset( );
		for (Map.Entry<String, Double> entry : pieData.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			dataset.setValue(entry.getKey(), new Double( entry.getValue() ) );
		}


		JFreeChart chart = ChartFactory.createPieChart(
				"Expense Chart", // chart title
				dataset, // data
				true, // include legend
				true,
				false);

		chart.setBackgroundPaint(Color.WHITE);
		chart.setBorderVisible(false);
		chart.setBorderPaint(Color.WHITE);

		int width = 640; /* Width of the image */
		int height = 480; /* Height of the image */
		File pieChart = new File(rootPath+"/PieChart.png");
		ChartUtilities.saveChartAsPNG( pieChart , chart , width , height );
	}
	
	public void createPieChartByCategory(List<UserExpense> expenses) throws IOException {
		
		Map<String, Double> charData = new HashMap<String, Double>();
		
		for(UserExpense expense : expenses) {
			String category = expense.getUserCategory().getCategory();
			if(charData.containsKey(category)) {
				Double amount = charData.get(category);
				charData.put(category, amount+expense.getAmount());
			}else {
				charData.put(category, expense.getAmount());
			}
		}
		
		DefaultPieDataset dataset = new DefaultPieDataset( );
		for (Map.Entry<String, Double> entry : charData.entrySet()) {
			dataset.setValue(entry.getKey(), new Double( entry.getValue() ) );
		}
		
		JFreeChart chart = ChartFactory.createPieChart(
				"Expense Chart", // chart title
				dataset, // data
				true, // include legend
				true,
				false);

		chart.setBackgroundPaint(Color.WHITE);
		chart.setBorderVisible(false);
		chart.setBorderPaint(Color.WHITE);

		int width = 640; /* Width of the image */
		int height = 480; /* Height of the image */
		File pieChart = new File(rootPath+"/PieChart.png");
		ChartUtilities.saveChartAsPNG( pieChart , chart , width , height );
	}
	
	public void createGraphByDate(List<UserExpense> expenses) throws IOException {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		for(UserExpense expense : expenses) {
			dataset.addValue(expense.getAmount(), "Expense", expense.getCreatedDate());
		}
		
	      JFreeChart lineChartObject = ChartFactory.createLineChart(
	    	         "Daily Expenses","Date",
	    	         "Amount",
	    	         dataset,PlotOrientation.VERTICAL,
	    	         true,true,false);

	    	      int width = 1240;    /* Width of the image */
	    	      int height = 480;   /* Height of the image */ 
	    	      File lineChart = new File(rootPath+"/LineChart.png"); 
	    	      ChartUtilities.saveChartAsPNG( lineChart , lineChartObject , width , height );
	}
	
	public void createBarChartByMonth(List<UserExpense> expenses) throws IOException {
		
		Map<Month, Double> charData = new TreeMap<Month, Double>();
		
		for(UserExpense expense : expenses) {
			int month = expense.getCreatedDate().getMonth();
			Month monthEnum = Month.of(month+1);
			if(charData.containsKey(monthEnum)) {
				Double amount = charData.get(monthEnum);
				charData.put(monthEnum, amount+expense.getAmount());
			}else {
				charData.put(monthEnum, expense.getAmount());
			}
		}
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		for(Map.Entry<Month, Double> entry : charData.entrySet()) {
			dataset.addValue(entry.getValue(), "Monthly Expense", entry.getKey());
		}
		
	      JFreeChart lineChartObject = ChartFactory.createBarChart(
	    	         "Monthly Expenses","Month",
	    	         "Amount",
	    	         dataset,PlotOrientation.VERTICAL,
	    	         true,true,false);

	    	      int width = 1240;    /* Width of the image */
	    	      int height = 480;   /* Height of the image */ 
	    	      File lineChart = new File(rootPath+"/BarChart.png"); 
	    	      ChartUtilities.saveChartAsPNG( lineChart , lineChartObject , width , height );
	}
	
	
	public void createBarChartByMonthCategory(List<UserExpense> expenses) throws IOException {
		
		Map<String, Map<Month, Double>> charData = new TreeMap<String, Map<Month, Double>>();
		
		for(UserExpense expense : expenses) {
			String category = expense.getUserCategory().getCategory();
			int month = expense.getCreatedDate().getMonth();
			Month monthEnum = Month.of(month+1);
			
			if(charData.containsKey(category)) {
				Map<Month, Double> monthlyExpenseMap = charData.get(category);
				if(monthlyExpenseMap.containsKey(monthEnum)) {
					Double amount = monthlyExpenseMap.get(monthEnum);
					monthlyExpenseMap.put(monthEnum, amount+expense.getAmount());
				}else {
					monthlyExpenseMap.put(monthEnum, expense.getAmount());
				}
			}else {
				Map<Month, Double> monthlyExpenseMap = new TreeMap<Month, Double>();
				monthlyExpenseMap.put(monthEnum, expense.getAmount());
				charData.put(category, monthlyExpenseMap);
			}
		}
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		for(Map.Entry<String, Map<Month, Double>> categoryEntry : charData.entrySet()) {
			for(Map.Entry<Month, Double> monthlyEntry : categoryEntry.getValue().entrySet() ) {
				dataset.addValue(monthlyEntry.getValue(), categoryEntry.getKey(), monthlyEntry.getKey());
			}
		}
		
	      JFreeChart lineChartObject = ChartFactory.createBarChart(
	    	         "Monthly Category Expenses","Month",
	    	         "Amount",
	    	         dataset,PlotOrientation.VERTICAL,
	    	         true,true,false);

	    	      int width = 1240;    /* Width of the image */
	    	      int height = 480;   /* Height of the image */ 
	    	      File lineChart = new File(rootPath+"/MonthlyCategoryBarChart.png"); 
	    	      ChartUtilities.saveChartAsPNG( lineChart , lineChartObject , width , height );
	}
	
	public void createGraphByMonthlyCategory(List<UserExpense> expenses) throws IOException {
		
		Map<Month, Double> monthlyData = new TreeMap<Month, Double>();
		
		for(UserExpense expense : expenses) {
			int month = expense.getCreatedDate().getMonth();
			Month monthEnum = Month.of(month+1);
			if(monthlyData.containsKey(monthEnum)) {
				Double amount = monthlyData.get(monthEnum);
				monthlyData.put(monthEnum, amount+expense.getAmount());
			}else {
				monthlyData.put(monthEnum, expense.getAmount());
			}
		}
		
		Map<String, Map<Month, Double>> categoryData = new TreeMap<String, Map<Month, Double>>();
		
		for(UserExpense expense : expenses) {
			String category = expense.getUserCategory().getCategory();
			int month = expense.getCreatedDate().getMonth();
			Month monthEnum = Month.of(month+1);
			
			if(categoryData.containsKey(category)) {
				Map<Month, Double> monthlyExpenseMap = categoryData.get(category);
				if(monthlyExpenseMap.containsKey(monthEnum)) {
					Double amount = monthlyExpenseMap.get(monthEnum);
					monthlyExpenseMap.put(monthEnum, amount+expense.getAmount());
				}else {
					monthlyExpenseMap.put(monthEnum, expense.getAmount());
				}
			}else {
				Map<Month, Double> monthlyExpenseMap = new TreeMap<Month, Double>();
				monthlyExpenseMap.put(monthEnum, expense.getAmount());
				categoryData.put(category, monthlyExpenseMap);
			}
		}
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		for( Map.Entry<Month, Double> entry : monthlyData.entrySet()) {
			dataset.addValue(entry.getValue(), "Total Expense", entry.getKey());
		}
		
		for(Map.Entry<String, Map<Month, Double>> categoryEntry : categoryData.entrySet()) {
			for(Map.Entry<Month, Double> monthlyEntry : categoryEntry.getValue().entrySet() ) {
				dataset.addValue(monthlyEntry.getValue(), categoryEntry.getKey(), monthlyEntry.getKey());
			}
		}
		
	      JFreeChart lineChartObject = ChartFactory.createLineChart(
	    	         "Monthly Expenses Pattern","Month",
	    	         "Amount",
	    	         dataset,PlotOrientation.VERTICAL,
	    	         true,true,false);

	    	      int width = 1240;    /* Width of the image */
	    	      int height = 480;   /* Height of the image */ 
	    	      File lineChart = new File(rootPath+"/LineChart.png"); 
	    	      ChartUtilities.saveChartAsPNG( lineChart , lineChartObject , width , height );
	}
}
