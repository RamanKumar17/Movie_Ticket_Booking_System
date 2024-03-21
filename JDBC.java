package com.raman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
   
        Connection establishConnection() {
        String url = "jdbc:mysql://localhost:3306/MovieTicketBooking";
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
//            if (connection != null)
//                System.out.println(".");
            return connection;
            
               
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
		
    }
        
        
}
