package com.svs.etracker.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.svs.etracker.model.UserExpense;
import com.svs.etracker.service.ExpenseService;

@Controller
public class DashboardController {
	
	@Autowired
	private ExpenseService expenseService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String trackEMployee(Model model) throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		
		Date tDate = cal.getTime();
		cal.add(Calendar.YEAR, -1);
		Date fDate = cal.getTime();

        double total = 0;
        List<UserExpense> expenses = expenseService.getBydate(fDate, tDate);
	        for(UserExpense expense : expenses) {
	        	total = total+expense.getAmount();
	        }
	
			model.addAttribute("total", total);
			model.addAttribute("expenses", expenses);
			return "dashboard";
	}
}