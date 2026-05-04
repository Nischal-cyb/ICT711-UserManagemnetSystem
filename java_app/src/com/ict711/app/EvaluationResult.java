package com.ict711.app;

public class EvaluationResult {
    private final String feedback;
    private final int transactionAmount;
    private final int updatedScore;
    private final String updatedStatus;

    public EvaluationResult(String feedback, int transactionAmount, int updatedScore, String updatedStatus) {
        this.feedback = feedback;
        this.transactionAmount = transactionAmount;
        this.updatedScore = updatedScore;
        this.updatedStatus = updatedStatus;
    }

    public String getFeedback() { return feedback; }
    public int getTransactionAmount() { return transactionAmount; }
    public int getUpdatedScore() { return updatedScore; }
    public String getUpdatedStatus() { return updatedStatus; }
}
