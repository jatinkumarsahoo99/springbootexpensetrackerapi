package com.example.demo.controller;

import java.sql.Date;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Expense;
import com.example.demo.service.ExpenseService;

@RestController
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/expenses")
	public List<Expense> getAllExpenses() {
		return expenseService.getAllExpenses();
		
	}
	
	@GetMapping("/expenses/pagination")
	public List<Expense> getAllExpenses1(Pageable page ) {
		return expenseService.getAllExpenses(page).toList();
		
	}
	
	/* pass the parameter in the  URL using the path Variable*/
	@GetMapping("/expenses/{id}")
	public Expense getExpenseById(@PathVariable("id") Integer id) {
		return expenseService.getExpenseById(id) ;
	}
	
	/* passing the parameter in the url using Query string */
	
	@GetMapping("/users/expenses")
	public String getExpenseById1(@RequestParam("userId") Long user,@RequestParam Long id) {
		return "The expense id is "+ id + user;
	}
	
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/expenses")
	public String deleteExpenseById(@RequestParam("id") Integer id) {
		expenseService.deleteExpenseById(id);
		return "Delete the expense object by its id" + id;
	}
	
	@ResponseStatus(value = HttpStatus.CREATED) // for status code
	@PostMapping("/expenses")
	public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
		System.out.println("Printing the expense details:: "+ expense);
		
	  return	expenseService.saveExpenseDetails(expense);
		
	}
	
	
	@PutMapping("/expenses/{id}")
	public Expense updateExpenseDetails(@RequestBody Expense expense , @PathVariable Integer id) {
		
		return expenseService.updateExpenseDetails(id, expense);
		
	}
	
	@GetMapping("/expenses/category")
	public List<Expense> getExpenseByCategory(@RequestParam String category,Pageable page){
		return expenseService.readByCategory(category, page);
	}
	
	
	@GetMapping("/expenses/name")
	public List<Expense>getExpensesByName(@RequestParam String keyword,Pageable page){
		return expenseService.readByName(keyword, page);
	}
	
	@GetMapping("/expenses/date")
	public List<Expense> getExpensesByDates(@RequestParam(required = false) Date startDate,@RequestParam(required = false)
	Date endDate,Pageable page ){
		
		return expenseService.readByDate(startDate, endDate, page);
		
	}
	

}
