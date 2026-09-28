package com.example.TripSplit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TripSplit.entity.Expense;
import com.example.TripSplit.repository.ExpenseRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByTrip(Long tripId) {
        return expenseRepository.findByTripId(tripId);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
    
    public Expense updateExpense(Long id, Expense updatedExpense) {

        Expense existingExpense =
                expenseRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Expense not found with id: " + id));

        existingExpense.setDescription(updatedExpense.getDescription());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setPaidBy(updatedExpense.getPaidBy());

        return expenseRepository.save(existingExpense);
    }
}