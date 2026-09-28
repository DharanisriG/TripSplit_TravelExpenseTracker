package com.example.TripSplit.entity;

public class TripSummary {

    private Long tripId;

    private String tripName;

    private Double totalExpense;

    private Integer participantCount;

    private Integer expenseCount;

    private Integer settlementCount;

    public TripSummary() {
    }

    public TripSummary(Long tripId,
                       String tripName,
                       Double totalExpense,
                       Integer participantCount,
                       Integer expenseCount,
                       Integer settlementCount) {

        this.tripId = tripId;
        this.tripName = tripName;
        this.totalExpense = totalExpense;
        this.participantCount = participantCount;
        this.expenseCount = expenseCount;
        this.settlementCount = settlementCount;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Integer getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(Integer participantCount) {
        this.participantCount = participantCount;
    }

    public Integer getExpenseCount() {
        return expenseCount;
    }

    public void setExpenseCount(Integer expenseCount) {
        this.expenseCount = expenseCount;
    }

    public Integer getSettlementCount() {
        return settlementCount;
    }

    public void setSettlementCount(Integer settlementCount) {
        this.settlementCount = settlementCount;
    }
}