package com.ict711.app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public List<User> loadUsers(String filePath) throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 8) {
                    continue;
                }

                String type = parts[0].trim();
                String id = parts[1].trim();
                String name = parts[2].trim();
                String email = parts[3].trim();
                String department = parts[4].trim();
                int score = Integer.parseInt(parts[5].trim());
                int wallet = Integer.parseInt(parts[6].trim());
                String status = parts[7].trim();

                if (type.equalsIgnoreCase("Premium")) {
                    users.add(new PremiumUser(id, name, email, department, score, wallet, status));
                } else {
                    users.add(new StandardUser(id, name, email, department, score, wallet, status));
                }
            }
        }
        return users;
    }

    public void saveUsers(String filePath, List<User> users) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("type,id,name,email,department,performanceScore,walletBalance,status");
            for (User user : users) {
                writer.println(user.toCsv());
            }
        }
    }
}
