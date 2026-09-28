package com.example.TripSplit.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.TripSplit.entity.Expense;
import com.example.TripSplit.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.createExpense(expense);
    }

    @GetMapping("/trip/{tripId}")
    public List<Expense> getExpensesByTrip(@PathVariable Long tripId) {
        return expenseService.getExpensesByTrip(tripId);
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "Expense deleted successfully";
    }
    
    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expense) {

        return expenseService.updateExpense(id, expense);
    }
}
