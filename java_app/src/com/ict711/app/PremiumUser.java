package com.ict711.app;

public class PremiumUser extends User {
    public PremiumUser(String id, String name, String email, String department, int performanceScore, int walletBalance, String status) {
        super(id, name, email, department, performanceScore, walletBalance, status);
    }

    @Override
    public String getUserType() {
        return "Premium";
    }

    @Override
    public EvaluationResult evaluateMonthlyPerformance(int scoreChange) {
        performanceScore += scoreChange;
        String feedback;
        int transactionAmount;

        if (scoreChange >= 10) {
            feedback = "Positive premium performance review";
            transactionAmount = 40;
            walletBalance += transactionAmount;
            status = "Rewarded";
        } else if (scoreChange <= -10) {
            feedback = "Negative premium performance review";
            transactionAmount = -10;
            walletBalance += transactionAmount;
            status = "Penalised";
        } else {
            feedback = "Stable premium monthly review";
            transactionAmount = 5;
            walletBalance += transactionAmount;
            status = "Active";
        }

        return new EvaluationResult(feedback, transactionAmount, performanceScore, status);
    }
}
