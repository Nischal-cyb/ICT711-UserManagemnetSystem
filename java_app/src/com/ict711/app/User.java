package com.ict711.app;

public abstract class User {
    protected String id;
    protected String name;
    protected String email;
    protected String department;
    protected int performanceScore;
    protected int walletBalance;
    protected String status;

    public User(String id, String name, String email, String department, int performanceScore, int walletBalance, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.performanceScore = performanceScore;
        this.walletBalance = walletBalance;
        this.status = status;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public int getPerformanceScore() { return performanceScore; }
    public int getWalletBalance() { return walletBalance; }
    public String getStatus() { return status; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartment(String department) { this.department = department; }
    public void setPerformanceScore(int performanceScore) { this.performanceScore = performanceScore; }
    public void setWalletBalance(int walletBalance) { this.walletBalance = walletBalance; }
    public void setStatus(String status) { this.status = status; }

    public abstract String getUserType();

    public abstract EvaluationResult evaluateMonthlyPerformance(int scoreChange);

    public String toCsv() {
        return String.join(",",
                getUserType(), id, name, email, department,
                String.valueOf(performanceScore),
                String.valueOf(walletBalance),
                status);
    }

    @Override
    public String toString() {
        return "Type=" + getUserType() +
                ", ID=" + id +
                ", Name=" + name +
                ", Email=" + email +
                ", Department=" + department +
                ", Score=" + performanceScore +
                ", Wallet=" + walletBalance +
                ", Status=" + status;
    }
}
