package com.epam.mjc.io;

import java.io.File;


public class FileReader {

   public Profile getDataFromFile(File file) {
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }

        return parseProfile(fileContent.toString());
    }

    private Profile parseProfile(String data) {
        String[] lines = data.split("\n");
        String name = "", email = "", phone = "";
        int age = 0;

        for (String line : lines) {
            if (line.startsWith("Name: ")) {
                name = line.substring(6);
            } else if (line.startsWith("Age: ")) {
                age = Integer.parseInt(line.substring(5));
            } else if (line.startsWith("Email: ")) {
                email = line.substring(7);
            } else if (line.startsWith("Phone: ")) {
                phone = line.substring(7);
            }
        }

        return new Profile(name, age, email, phone);
    }
}
