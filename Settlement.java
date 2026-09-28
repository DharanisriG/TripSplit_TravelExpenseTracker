package com.example.TripSplit.entity;

public class Settlement {

    private String fromParticipant;

    private String toParticipant;

    private Double amount;

    public Settlement() {
    }

    public Settlement(String fromParticipant,
                      String toParticipant,
                      Double amount) {
        this.fromParticipant = fromParticipant;
        this.toParticipant = toParticipant;
        this.amount = amount;
    }

    public String getFromParticipant() {
        return fromParticipant;
    }

    public void setFromParticipant(String fromParticipant) {
        this.fromParticipant = fromParticipant;
    }

    public String getToParticipant() {
        return toParticipant;
    }

    public void setToParticipant(String toParticipant) {
        this.toParticipant = toParticipant;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}