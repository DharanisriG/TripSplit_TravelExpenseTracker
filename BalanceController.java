package com.example.TripSplit.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TripSplit.entity.Balance;
import com.example.TripSplit.service.BalanceService;

@RestController
@RequestMapping("/api/balances")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/trip/{tripId}")
    public List<Balance> getBalances(@PathVariable Long tripId) {
        return balanceService.calculateBalances(tripId);
    }
}