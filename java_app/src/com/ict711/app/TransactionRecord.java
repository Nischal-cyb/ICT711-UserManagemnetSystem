package com.ict711.app;

public class TransactionRecord {
    private final String userId;
    private final String userName;
    private final String feedback;
    private final int amount;
    private final String date;

    public TransactionRecord(String userId, String userName, String feedback, int amount, String date) {
        this.userId = userId;
        this.userName = userName;
        this.feedback = feedback;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Date=" + date + ", UserID=" + userId + ", Name=" + userName + ", Feedback=" + feedback + ", Amount=" + amount;
    }
}
