package com.svs.etracker.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svs.etracker.model.Expense;
import com.svs.etracker.model.UserExpense;
import com.svs.etracker.model.User;
import com.svs.etracker.model.UserCategory;
import com.svs.etracker.repository.CategoryRepository;
import com.svs.etracker.repository.ExpenseRepository;
import com.svs.etracker.repository.UserCategoryRepository;
import com.svs.etracker.repository.UserExpenseRepository;
import com.svs.etracker.security.service.UserService;


@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private UserExpenseRepository userExpenseRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserService userService;

	public void addexpense(Expense expense){
		expenseRepository.save(expense);
	}
	
	public void addUserExpense(UserExpense expense){
		userExpenseRepository.save(expense);
	}

	public Map<String, Double> getPieData(){
		List<Object[]> dbData = userExpenseRepository.getPieData(userService.getCurrentUser());
		
		Map<String, Double> pieData = new HashMap<String, Double>();
		for(Object[] data : dbData ) {
			UserCategory category = (UserCategory)data[0];
			Double amount = (Double)data[1];
			pieData.put(category.getCategory(), amount);
		}
		return pieData;
	}
	
	public Map<String, Double> getPieDataByDate(Date fromDate, Date tillDate){
		List<Object[]> dbData = userExpenseRepository.getPieDataByDate(userService.getCurrentUser(), fromDate, tillDate);
		Map<String, Double> pieData = new HashMap<String, Double>();
		for(Object[] data : dbData ) {
			UserCategory category = (UserCategory)data[0];
			Double amount = (Double)data[1];
			pieData.put(category.getCategory(), amount);
		}
		return pieData;
	}
	
	public List<UserExpense> getBydate(Date fromDate, Date tillDate) {
		return userExpenseRepository.getExpenseByDate(userService.getCurrentUser(), fromDate, tillDate);
	}
	
	public List<UserExpense> getAllExpenses() {
		return userExpenseRepository.getAllExpenses(userService.getCurrentUser());
	}

	public List<Object[]> getbyCategory(String category) {
		int categoryId = categoryRepository.getIdForCategory(category);		
		return expenseRepository.getExpenseByCategory(categoryId);

	}
	public List<Object[]> getByCategoryAndDate(Date fromDate, Date tillDate, String category){
		int categoryId = categoryRepository.getIdForCategory(category);
		return expenseRepository.getExpenseByCategoryAndDate(fromDate, tillDate, categoryId);
	}

	public UserExpense getExpenseById(int expenseId){
		return userExpenseRepository.findOne(expenseId);
	}

	public void deleteExpenses(int[] ids){
		for (int i : ids) {
			userExpenseRepository.delete(i);
		}
	}

}
