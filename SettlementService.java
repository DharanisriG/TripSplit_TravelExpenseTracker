package com.example.TripSplit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TripSplit.entity.Balance;
import com.example.TripSplit.entity.Settlement;

@Service
public class SettlementService {

    private final BalanceService balanceService;

    public SettlementService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public List<Settlement> generateSettlements(Long tripId) {

        List<Balance> balances = balanceService.calculateBalances(tripId);

        List<Balance> creditors = new ArrayList<>();
        List<Balance> debtors = new ArrayList<>();

        for (Balance balance : balances) {

            if (balance.getNetBalance() > 0) {
                creditors.add(balance);
            }

            if (balance.getNetBalance() < 0) {
                debtors.add(balance);
            }
        }

        List<Settlement> settlements = new ArrayList<>();

        int creditorIndex = 0;
        int debtorIndex = 0;

        while (creditorIndex < creditors.size()
                && debtorIndex < debtors.size()) {

            Balance creditor = creditors.get(creditorIndex);
            Balance debtor = debtors.get(debtorIndex);

            double receiveAmount = creditor.getNetBalance();
            double payAmount = Math.abs(debtor.getNetBalance());

            double settlementAmount =
                    Math.min(receiveAmount, payAmount);

            Settlement settlement = new Settlement(
                    debtor.getParticipantName(),
                    creditor.getParticipantName(),
                    settlementAmount
            );

            settlements.add(settlement);

            creditor.setNetBalance(
                    creditor.getNetBalance() - settlementAmount
            );

            debtor.setNetBalance(
                    debtor.getNetBalance() + settlementAmount
            );

            if (Math.abs(creditor.getNetBalance()) < 0.01) {
                creditorIndex++;
            }

            if (Math.abs(debtor.getNetBalance()) < 0.01) {
                debtorIndex++;
            }
        }

        return settlements;
    }
}