package com.example.demo.repository;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Expense;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
	
	
//	https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	
    //	SELECT * FROM tbl_expenses WHERE category=?
	Page<Expense>	findByCategory(String category,Pageable page);
	
	
//	SELECT * FROM tbl_expense WHERE name LIKE '%keyword%'
	Page<Expense> findByNameContaining(String keyword,Pageable page);
	
	
   Page<Expense> findByDateBetween(Date startDate,Date endDate,Pageable page);

}
