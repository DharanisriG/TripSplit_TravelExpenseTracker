package com.example.TripSplit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TripSplit.entity.TripSummary;
import com.example.TripSplit.service.TripSummaryService;

@RestController
@RequestMapping("/api/trip-summary")
public class TripSummaryController {

    private final TripSummaryService tripSummaryService;

    public TripSummaryController(TripSummaryService tripSummaryService) {
        this.tripSummaryService = tripSummaryService;
    }

    @GetMapping("/{tripId}")
    public TripSummary getTripSummary(@PathVariable Long tripId) {
        return tripSummaryService.getTripSummary(tripId);
    }
}