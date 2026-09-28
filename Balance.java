package com.example.TripSplit.entity;

public class Balance {

    private Long participantId;

    private String participantName;

    private Double totalPaid;

    private Double totalOwed;

    private Double netBalance;

    public Balance() {
    }

    public Balance(Long participantId, String participantName,
                   Double totalPaid, Double totalOwed, Double netBalance) {
        this.participantId = participantId;
        this.participantName = participantName;
        this.totalPaid = totalPaid;
        this.totalOwed = totalOwed;
        this.netBalance = netBalance;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Double getTotalOwed() {
        return totalOwed;
    }

    public void setTotalOwed(Double totalOwed) {
        this.totalOwed = totalOwed;
    }

    public Double getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(Double netBalance) {
        this.netBalance = netBalance;
    }
}