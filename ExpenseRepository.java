package com.example.TripSplit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TripSplit.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByTripId(Long tripId);
}