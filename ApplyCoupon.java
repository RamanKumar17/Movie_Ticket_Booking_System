package com.raman;

import java.sql.*;
import java.util.Scanner;

public class ApplyCoupon {
	
     Scanner scanner = new Scanner(System.in);
     JDBC jdbc=new JDBC();
 	 Connection connection=jdbc.establishConnection();
 	 public int discountedPrice;
    
 	public void applyCoupon() {
 		
        // Assume booking details are retrieved from the database
        // Show total amount to user
        System.out.println("Total Amount: Rs. " + new UserControl().totalPrice);

        // Prompt user to proceed for payment
        String proceedChoice = null;
        boolean isValidProceedChoice = false;
        
        while (!isValidProceedChoice) {
            System.out.println("Do you want to proceed for payment? (yes/no)");
            proceedChoice = scanner.nextLine().toLowerCase();

            if (proceedChoice.equals("yes") || proceedChoice.equals("no")) {
                isValidProceedChoice = true;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        if (proceedChoice.equals("yes")) {
            // Ask user if they have a coupon code
            String couponChoice = null;
            boolean isValidCouponChoice = false;
            while (!isValidCouponChoice) {
                System.out.println("Do you have a coupon code? (yes/no)");
                couponChoice = scanner.nextLine().toLowerCase();

                if (couponChoice.equals("yes") || couponChoice.equals("no")) {
                    isValidCouponChoice = true;
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }
            }

            if (couponChoice.equals("yes")) {
                // Ask user to input coupon code
                System.out.println("Enter your coupon code:");
                String couponCode = scanner.nextLine();

                // Validate coupon code and apply discount if valid
                int discount = validateCouponCode(couponCode);
                if (discount > 0) {
                    // Apply discount and display updated total amount
                    discountedPrice = new UserControl().totalPrice - discount;
                    System.out.println("Congratulations! You've got a discount of Rs. " + discount);
                    System.out.println("Amount after discount: Rs. " + discountedPrice);
                    // Proceed with payment
                    // Payment logic goes here
                    
                    new UserControl().finalPrice = discountedPrice;
                    
                    String iquery = "UPDATE TransactionalDetails SET Price_after_coupon = ? WHERE UniqueId = ?";
                   
					try {
						PreparedStatement preparedStatement = connection.prepareStatement(iquery);
						preparedStatement.setInt(1, discountedPrice);
						new UserControl();
						preparedStatement.setString(2, UserControl.uniqueId);
						preparedStatement.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                } else {
                    System.out.println("Invalid coupon code. Proceeding without discount.");
                    // Proceed with payment
                    new MakePayment().selectPaymentMethod();
                    
                }
            } else {
                // Proceed with payment without using a coupon code
                // Payment logic goes here
            }
        } else {
            System.out.println("Payment cancelled.");
        }
    }
    public int validateCouponCode(String couponCode) {
        int discount = 0;
      
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection (assuming already established)

            // Prepare SQL statement to fetch discount for the given coupon code
        	
            String sql = "SELECT FlatDiscount FROM CouponOffer WHERE CouponCode = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, couponCode);

            // Execute query
            rs = stmt.executeQuery();

            // Check if coupon code exists and get discount value
            if (rs.next()) {
                discount = rs.getInt("FlatDiscount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        /*
        finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        */
        return discount;
    }
}

