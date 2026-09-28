package com.example.TripSplit.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TripSplit.entity.Settlement;
import com.example.TripSplit.service.SettlementService;

@RestController
@RequestMapping("/api/settlements")
public class SettlementController {

    private final SettlementService settlementService;

    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @GetMapping("/trip/{tripId}")
    public List<Settlement> getSettlements(@PathVariable Long tripId) {
        return settlementService.generateSettlements(tripId);
    }
}