package com.raman;

import java.sql.*;
import java.util.Scanner;

public class AdminControl {
	
	JDBC jdbc = new JDBC();
	Connection connection=jdbc.establishConnection();
	
    // Method to add a new theater
    public  void addTheater() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter theater name:");
            String theaterName = scanner.nextLine();
            System.out.println("Enter theater location:");
            String location = scanner.nextLine();

            // Prepare SQL statement
            String sql = "INSERT INTO TheaterDetails (TheaterName, Location) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, theaterName);
            statement.setString(2, location);

            // Execute SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Theater added successfully.");
            } else {
                System.out.println("Failed to add theater.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to edit theater details
    public  void editTheater() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter theater ID to edit:");
            int theaterId = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            System.out.println("Enter new theater name:");
            String theaterName = scanner.nextLine();
            System.out.println("Enter new theater location:");
            String theaterLocation = scanner.nextLine();

            // Prepare SQL statement
            String sql = "UPDATE TheaterDetails SET TheaterName = ?, Location = ? WHERE TheaterId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, theaterName);
            statement.setString(2, theaterLocation);
            statement.setInt(3, theaterId);

            // Execute SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Theater updated successfully.");
            } else {
                System.out.println("Failed to update theater.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to remove a theater
    public  void removeTheater() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter theater ID to remove:");
            int theaterId = scanner.nextInt();

            // Prepare SQL statement
            String sql = "DELETE FROM TheaterDetails WHERE TheaterId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, theaterId);

            // Execute SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Theater removed successfully.");
            } else {
                System.out.println("Failed to remove theater.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new movie
    public void addMovie( ) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter movie name:");
            String movieName = scanner.nextLine();
            System.out.println("Enter movie duration:");
            int duration = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            System.out.println("Enter TheaterId:");
            int TheaterId = scanner.nextInt();
            scanner.nextLine();

            // Prepare SQL statement
            String sql = "INSERT INTO MovieDetails (MovieName, Duration_in_min, TheaterId) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, movieName);
            statement.setInt(2, duration);
            statement.setInt(3, TheaterId);

            // Execute SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Movie added successfully.");
            } else {
                System.out.println("Failed to add movie.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to edit movie details
    public void editMovie() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter movie ID to edit:");
            int movieId = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            System.out.println("Enter new movie name:");
            String movieName = scanner.nextLine();
            System.out.println("Enter new movie duration:");
            int duration = scanner.nextInt();

            // Prepare SQL statement
            String sql = "UPDATE MovieDetails SET MovieName = ?, Duration_in_min = ? WHERE MovieId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, movieName);
            statement.setInt(2, duration);
            statement.setInt(3, movieId);

            // Execute SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Movie updated successfully.");
            } else {
                System.out.println("Failed to update movie.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to remove a movie
    public  void removeMovie() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter movie ID to remove:");
            int movieId = scanner.nextInt();

            // Prepare SQL statement
            String sql = "DELETE FROM MovieDetails WHERE MovieId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, movieId);

            // Execute SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Movie removed successfully.");
            } else {
                System.out.println("Failed to remove movie.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void viewTransactionalDetails() {
    	
    }
}
