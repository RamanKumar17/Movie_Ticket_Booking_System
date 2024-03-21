package com.raman;

import java.sql.*;
import java.util.Scanner;

public class UserLogin  {
	
	JDBC jdbc = new JDBC();
	Connection connection = jdbc.establishConnection();
	public String role="";
	public static String username;
	
	Scanner sc = new Scanner(System.in);

	public void validatingCredentials() {
		System.out.println("Enter Username: ");
		username = sc.nextLine();
		
		System.out.println("Enter password: ");
		String password = sc.nextLine();
		
		// Query to retrieve user credentials
        String query = "SELECT Role FROM UserCredentials WHERE Username = ? AND Password = ?";
        try {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            // User exists, save the role
            role = resultSet.getString("Role");
            System.out.println("User authenticated successfully. Role: " + role);
            
        } else {
            // User doesn't exist
            System.out.println("Warning.");
        }

        // Close resources
        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        System.err.println("Error validating user credentials: " + e.getMessage());
    }
		
	}
}
