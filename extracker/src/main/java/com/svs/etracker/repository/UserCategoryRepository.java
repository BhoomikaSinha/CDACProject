package com.svs.etracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.svs.etracker.model.Category;
import com.svs.etracker.model.User;
import com.svs.etracker.model.UserCategory;

@Repository
public interface UserCategoryRepository extends CrudRepository<UserCategory, Integer>{

	@Query("select c.category from UserCategory c where c.user = :user")
	public List<String> getCategoriesForUser(@Param("user")User user);
	
	@Query("From UserCategory c where c.category = :categoryName AND c.user = :user")
	public UserCategory getCategoryForUserByName(@Param("user")User user, @Param("categoryName")String categoryName);
}
