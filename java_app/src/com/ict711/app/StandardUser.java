package com.ict711.app;

public class StandardUser extends User {
    public StandardUser(String id, String name, String email, String department, int performanceScore, int walletBalance, String status) {
        super(id, name, email, department, performanceScore, walletBalance, status);
    }

    @Override
    public String getUserType() {
        return "Standard";
    }

    @Override
    public EvaluationResult evaluateMonthlyPerformance(int scoreChange) {
        performanceScore += scoreChange;
        String feedback;
        int transactionAmount;

        if (scoreChange >= 10) {
            feedback = "Positive performance review";
            transactionAmount = 25;
            walletBalance += transactionAmount;
            status = "Rewarded";
        } else if (scoreChange <= -10) {
            feedback = "Negative performance review";
            transactionAmount = -15;
            walletBalance += transactionAmount;
            status = "Penalised";
        } else {
            feedback = "Stable monthly review";
            transactionAmount = 0;
            status = "Active";
        }

        return new EvaluationResult(feedback, transactionAmount, performanceScore, status);
    }
}
