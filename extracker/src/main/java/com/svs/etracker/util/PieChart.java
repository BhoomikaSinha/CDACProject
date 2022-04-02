package com.svs.etracker.util;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
	
	public void createGraphByDate(List<UserExpense> expenses) throws IOException {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		for(UserExpense expense : expenses) {
			dataset.addValue(expense.getAmount(), "Expense", expense.getCreatedDate());
		}
		
	      JFreeChart lineChartObject = ChartFactory.createLineChart(
	    	         "Daily Expenses","",
	    	         "",
	    	         dataset,PlotOrientation.VERTICAL,
	    	         true,true,false);

	    	      int width = 1240;    /* Width of the image */
	    	      int height = 480;   /* Height of the image */ 
	    	      File lineChart = new File(rootPath+"/LineChart.png"); 
	    	      ChartUtilities.saveChartAsPNG( lineChart , lineChartObject , width , height );
	}
}
