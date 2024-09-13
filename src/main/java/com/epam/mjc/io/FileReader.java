package com.epam.mjc.io;

import java.io.File;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String name = null;
            Integer age = null;
            String email = null;
            Long phone = null;

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    name = line.substring(6).trim();
                } else if (line.startsWith("Age: ")) {
                    age = Integer.parseInt(line.substring(5).trim());
                } else if (line.startsWith("Email: ")) {
                    email = line.substring(7).trim();
                } else if (line.startsWith("Phone: ")) {
                    phone = Long.parseLong(line.substring(7).trim());
                }
            }

            if (name == null || age == null || email == null || phone == null) {
                throw new IOException("File is incomplete or formatted incorrectly");
            }

            return new Profile(name, age, email, phone);
        } catch (FileNotFoundException e) {
            System.err.println("The specified file does not exist.");
            return null;
        } catch (IOException e) {
            System.err.println("Error occurred while reading the file: " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.err.println("Error in data format: " + e.getMessage());
            return null;
        }
    }
}
