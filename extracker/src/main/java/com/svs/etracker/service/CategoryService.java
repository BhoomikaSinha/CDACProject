package com.svs.etracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.svs.etracker.model.Category;
import com.svs.etracker.model.User;
import com.svs.etracker.model.UserCategory;
import com.svs.etracker.repository.CategoryRepository;
import com.svs.etracker.repository.UserCategoryRepository;
import com.svs.etracker.security.service.UserService;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserCategoryRepository userCategoryRepository;
	@Autowired
	private UserService userService;

	public List<String> getOnlyCategories(){
		User currentUser = userService.getCurrentUser();
		return userCategoryRepository.getCategoriesForUser(currentUser);
	}
	
	public  int getCategoryId(String category) {
		return getCategory(category).getCategoryId();
	}

	public List<Category> getCategory(){
		List<Category> categories = new ArrayList<Category>();
		categoryRepository.findAll()
		.forEach(categories::add);
		return categories;
	}

	public void addCategory(Category category){
		categoryRepository.save(category);
		
		////////
		UserCategory userCategory = new UserCategory();
		userCategory.setCategory(category.getCategory());
		userCategory.setUser(userService.getCurrentUser());
		userCategoryRepository.save(userCategory);
		/////
	}

	public UserCategory getCategory(String category){		
		User currentUser = userService.getCurrentUser();
		return userCategoryRepository.getCategoryForUserByName(currentUser, category);
	}

	public void deleteCategory(String category){

		Category deleteCategory = categoryRepository.findByCategory(category);
		categoryRepository.delete(deleteCategory.getCategoryId());
		
		UserCategory  userCategory = userCategoryRepository.getCategoryForUserByName(userService.getCurrentUser(), category);
		userCategoryRepository.delete(userCategory.getCategoryId());		
	}

	public int saveOrgetCategory(String category){
		int id=0;
		category.trim();

		/*id = categoryRepository.getIdForCategory(category);
		if(id==0){
			Category obj = new Category();
			obj.setCategory(category);
			addCategory(obj);
			return categoryRepository.getIdForCategory(category);
		}
		else{
			return id;
		}*/

		List<String> categories = getOnlyCategories();
		if(categories.contains(category)){
			id = categoryRepository.getIdForCategory(category);
			return id;
		}
		else{
			Category obj = new Category();
			obj.setCategory(category);
		    addCategory(obj);
			return categoryRepository.getIdForCategory(category);
		}
	}

	public Category getCategoryById(int id){
		return categoryRepository.findOne(id);
	}


	public Map<String, Double> getPieData(){
		
		List<Object[]> object=categoryRepository.getPieData();
		Map<String, Double> pie = new HashMap<String, Double>();
		for (int i=0; i<object.size(); i++){
			Object[] pieRow = object.get(i);
			String category= (String)pieRow[0];
			Double amount = (Double)pieRow[1];
			pie.put(category,amount);
		}
		return pie;
	}

	public Map<String, Double> getPieData(Date fDate, Date tDate){

		List<Object[]> object=categoryRepository.getPieDataByDate(fDate, tDate);
		Map<String, Double> pie = new HashMap<String, Double>();
		for (int i=0; i<object.size(); i++){
			Object[] pieRow = object.get(i);
			String category= (String)pieRow[0];
			Double amount = (Double)pieRow[1];
			pie.put(category,amount);
		}
		return pie;
	}


}
