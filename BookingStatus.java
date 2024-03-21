package com.raman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingStatus {

	Connection connection = new JDBC().establishConnection();
	
public void bookingStatusCancelled() {
    	
    	String iquery = "UPDATE TransactionalDetails SET BookingStatus = ? WHERE UniqueId = ?";
        
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(iquery);
			preparedStatement.setString(1, "Cancelled");
			preparedStatement.setString(2, UserControl.uniqueId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

public void bookingStatusSuccessful() {
	
	String iquery = "UPDATE TransactionalDetails SET BookingStatus = ? WHERE UniqueId = ?";
    
	try {
		PreparedStatement preparedStatement = connection.prepareStatement(iquery);
		preparedStatement.setString(1, "Successful");
		preparedStatement.setString(2, UserControl.uniqueId);
		preparedStatement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	
}
