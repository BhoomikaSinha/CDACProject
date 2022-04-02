package com.svs.etracker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.svs.etracker.model.User;
import com.svs.etracker.model.UserExpense;

@Repository
public interface UserExpenseRepository extends CrudRepository<UserExpense, Integer> {

	@Query("SELECT e.userCategory, SUM(e.amount) FROM UserExpense e WHERE e.user = :user GROUP BY e.userCategory")
	public List<Object[]> getPieData(@Param("user")User user);
	
	@Query("SELECT e.userCategory, SUM(e.amount) FROM UserExpense e WHERE e.user = :user AND e.createdDate BETWEEN :fromDate AND :tillDate GROUP BY e.userCategory")
	public List<Object[]> getPieDataByDate(@Param("user")User user, @Param("fromDate") Date fromDate, @Param("tillDate") Date tillDate);
	
	@Query("FROM UserExpense e WHERE e.user = :user AND e.createdDate BETWEEN :fromDate AND :tillDate")
	public List<UserExpense> getExpenseByDate(@Param("user")User user, @Param("fromDate") Date fromDate, @Param("tillDate") Date tillDate);
	
	@Query("FROM UserExpense e WHERE e.user = :user")
	public List<UserExpense> getAllExpenses(@Param("user")User user);
}
