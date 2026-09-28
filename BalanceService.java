package com.example.TripSplit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TripSplit.entity.Balance;
import com.example.TripSplit.entity.Expense;
import com.example.TripSplit.entity.Participant;
import com.example.TripSplit.repository.ExpenseRepository;
import com.example.TripSplit.repository.ParticipantRepository;

@Service
public class BalanceService {

    private final ExpenseRepository expenseRepository;
    private final ParticipantRepository participantRepository;

    public BalanceService(ExpenseRepository expenseRepository,
                           ParticipantRepository participantRepository) {
        this.expenseRepository = expenseRepository;
        this.participantRepository = participantRepository;
    }

    public List<Balance> calculateBalances(Long tripId) {

        List<Participant> participants =
                participantRepository.findByTripId(tripId);

        List<Expense> expenses =
                expenseRepository.findByTripId(tripId);

        List<Balance> balances = new ArrayList<>();

        double totalExpense = 0;

        for (Expense expense : expenses) {
            totalExpense += expense.getAmount();
        }

        double share = 0;

        if (!participants.isEmpty()) {
            share = totalExpense / participants.size();
        }

        for (Participant participant : participants) {

            double totalPaid = 0;

            for (Expense expense : expenses) {

                if (expense.getPaidBy().getId()
                        .equals(participant.getId())) {

                    totalPaid += expense.getAmount();
                }
            }

            double netBalance = totalPaid - share;

            Balance balance = new Balance(
                    participant.getId(),
                    participant.getName(),
                    totalPaid,
                    share,
                    netBalance
            );

            balances.add(balance);
        }

        return balances;
    }
}