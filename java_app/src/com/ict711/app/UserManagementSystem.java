package com.ict711.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UserManagementSystem {
    private final List<User> users;
    private final Queue<TransactionRecord> transactions;

    public UserManagementSystem() {
        this.users = new ArrayList<>();
        this.transactions = new LinkedList<>();
    }

    public void setUsers(List<User> loadedUsers) {
        users.clear();
        users.addAll(loadedUsers);
    }

    public List<User> getUsers() {
        return users;
    }

    public Queue<TransactionRecord> getTransactions() {
        return transactions;
    }

    public boolean addUser(User user) {
        if (findUserById(user.getId()) != null) {
            return false;
        }
        return users.add(user);
    }

    public User findUserById(String id) {
        for (User user : users) {
            if (user.getId().equalsIgnoreCase(id)) {
                return user;
            }
        }
        return null;
    }

    public List<User> findUsersByName(String name) {
        List<User> matches = new ArrayList<>();
        for (User user : users) {
            if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                matches.add(user);
            }
        }
        return matches;
    }

    public boolean updateUser(String id, String name, String email, String department) {
        User user = findUserById(id);
        if (user == null) {
            return false;
        }
        user.setName(name);
        user.setEmail(email);
        user.setDepartment(department);
        return true;
    }

    public boolean deleteUser(String id) {
        User user = findUserById(id);
        if (user == null) {
            return false;
        }
        return users.remove(user);
    }

    public EvaluationResult evaluateUser(String id, int scoreChange) {
        User user = findUserById(id);
        if (user == null) {
            return null;
        }

        EvaluationResult result = user.evaluateMonthlyPerformance(scoreChange);
        transactions.add(new TransactionRecord(
                user.getId(),
                user.getName(),
                result.getFeedback(),
                result.getTransactionAmount(),
                LocalDate.now().toString()
        ));
        return result;
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
            return;
        }
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void displayTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transaction records available.");
            return;
        }
        for (TransactionRecord record : transactions) {
            System.out.println(record);
        }
    }
}
