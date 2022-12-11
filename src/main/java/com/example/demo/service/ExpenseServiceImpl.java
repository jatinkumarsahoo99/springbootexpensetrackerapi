package com.example.demo.service;




import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Expense;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepo;

	@Override
	public List<Expense> getAllExpenses() {
		
		List<Expense> data = expenseRepo.findAll();
		
		data.forEach((p) ->{
			System.out.println(p.getName());
			
		});
		
		return expenseRepo.findAll();
	}

	@Override
	public Expense getExpenseById(Integer id) {
		// TODO Auto-generated method stub
		 Optional<Expense> expense = expenseRepo.findById(id);
		 
		 if(expense.isPresent()) {
			 return expense.get();
		 }
		 throw new ResourceNotFoundException("Expense is not found for the id "+id);
	}

	@Override
	public void deleteExpenseById(Integer id) {
		// TODO Auto-generated method stub
		Expense expense = getExpenseById(id);
		expenseRepo.delete(expense);
		
	}

	@Override
	public Expense saveExpenseDetails(Expense expense) {
		// TODO Auto-generated method stub
		return  expenseRepo.save(expense);
		
	}

	@Override
	public Expense updateExpenseDetails(Integer id, Expense expense) {
		// TODO Auto-generated method stub
		Expense existingExpense = getExpenseById(id);
		existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
		existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
		existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
		existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
		existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
		
		
		return expenseRepo.save(existingExpense);
	
	}

	@Override
	public Page<Expense> getAllExpenses(Pageable page) {
		// TODO Auto-generated method stub
		return expenseRepo.findAll(page);
	}

	@Override
	public List<Expense> readByCategory(String category, Pageable page) {
		// TODO Auto-generated method stub
		return expenseRepo.findByCategory(category, page).toList();
	}

	@Override
	public List<Expense> readByName(String keyword, Pageable page) {
		// TODO Auto-generated method stub
		return expenseRepo.findByNameContaining(keyword, page).toList();
	}

	@Override
	public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {
		if(startDate == null) {
			startDate = new Date(0);
			System.out.println(startDate.toString());
			
		}
		if(endDate == null) {
			endDate = new Date(System.currentTimeMillis());
			System.out.println(endDate.toString());
		}
		return expenseRepo.findByDateBetween(startDate, endDate, page).toList();
	}



}
