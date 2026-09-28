package com.example.TripSplit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TripSplit.entity.Expense;
import com.example.TripSplit.entity.Settlement;
import com.example.TripSplit.entity.Trip;
import com.example.TripSplit.entity.TripSummary;
import com.example.TripSplit.repository.ExpenseRepository;
import com.example.TripSplit.repository.ParticipantRepository;
import com.example.TripSplit.repository.TripRepository;
import com.example.TripSplit.exception.TripNotFoundException;

@Service
public class TripSummaryService {

    private final TripRepository tripRepository;
    private final ParticipantRepository participantRepository;
    private final ExpenseRepository expenseRepository;
    private final SettlementService settlementService;

    public TripSummaryService(TripRepository tripRepository,
                               ParticipantRepository participantRepository,
                               ExpenseRepository expenseRepository,
                               SettlementService settlementService) {

        this.tripRepository = tripRepository;
        this.participantRepository = participantRepository;
        this.expenseRepository = expenseRepository;
        this.settlementService = settlementService;
    }

    public TripSummary getTripSummary(Long tripId) {

    	Trip trip = tripRepository.findById(tripId)
    	        .orElseThrow(() -> new TripNotFoundException(tripId));

        int participantCount =
                participantRepository.findByTripId(tripId).size();

        List<Expense> expenses =
                expenseRepository.findByTripId(tripId);

        double totalExpense = 0;

        for (Expense expense : expenses) {
            totalExpense += expense.getAmount();
        }

        int expenseCount = expenses.size();

        List<Settlement> settlements =
                settlementService.generateSettlements(tripId);

        int settlementCount = settlements.size();

        return new TripSummary(
                trip.getId(),
                trip.getName(),
                totalExpense,
                participantCount,
                expenseCount,
                settlementCount
        );
    }
}
