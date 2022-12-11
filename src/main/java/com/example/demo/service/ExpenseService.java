package com.example.demo.service;



import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Expense;

public interface ExpenseService {
	
	List<Expense>getAllExpenses();
	

	Page<Expense>getAllExpenses(Pageable page);
	
	Expense getExpenseById(Integer id);
	
	void deleteExpenseById(Integer id);
	
   Expense	saveExpenseDetails(Expense expense);
   
  Expense updateExpenseDetails(Integer id,Expense expense);
  
  List<Expense> readByCategory(String category,Pageable page);
  
  List<Expense>readByName(String keyword,Pageable page);
  
  List<Expense> readByDate(Date startDate,Date endDate,Pageable page);
  
  
   

}
