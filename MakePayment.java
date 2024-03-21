package com.raman;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import javax.mail.MessagingException;

public class MakePayment {
	
    static Scanner scanner = new Scanner(System.in);
    Countdown count = new Countdown();
//    ReduceSeats reduceSeats = new ReduceSeats();
    UserControl userControl = new UserControl();
    Connection connection = new JDBC().establishConnection();
    
    public static String modeOfPayment = null;
    
//    int seats = userControl.numberOfSeats;
//    int id = userControl.theaterId;
    
    
    public void selectPaymentMethod(){
    	
        // Prompt user to proceed for payment
    	
    	BookingStatus bookingStatus = new BookingStatus();
        System.out.println("Do you want to proceed for payment? (yes/no)");
        String proceedChoice = scanner.nextLine();
        
        if (proceedChoice.equalsIgnoreCase("yes")) {
            // Present payment options
            System.out.println("Choose payment method: ");
            System.out.println("1. Cash");
            System.out.println("2. Online");
            
            
            int paymentMethod = scanner.nextInt();
            
            switch (paymentMethod) {
                case 1:
                	modeOfPayment="Cash";
                    new MakePayment().payByCash();
                    break;
                case 2:
                	modeOfPayment="Online";
                	new MakePayment().payOnline();
                    break;
                default:
                    System.out.println("Invalid payment method selected.\n");
//                    System.out.println("Closing the task and everything...");
                    System.out.println("Thanks For using our Service !!");
                    bookingStatus.bookingStatusCancelled();
                    System.exit(0);
                    
            }
        } else {
            System.out.println("Payment cancelled.\n");
//            System.out.println("Closing the task and everything...");
            System.out.println("Thanks For using our Service !!");
            bookingStatus.bookingStatusCancelled();
            System.exit(0);
            
        }
    }


    public void payByCash() {
        System.out.println("Please Collect your ticket: ");
        
        addModeOfPayment(modeOfPayment);
        new BookingStatus().bookingStatusSuccessful();
        System.out.println("Thanks for being patient. Your ticket has been sent to your provided E-mail Id.");
        new PDFgeneration();
		PDFgeneration.generatePdf();
        
        try {
			new SendMail().draftAndSendEmail();
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.exit(0);
    }

    public void payOnline() {
    	
        System.out.println("Please select your online payment method: ");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. Net Banking");
        
        int option = scanner.nextInt();
        
        switch (option) {
        case 1:
        	modeOfPayment="Credit Card";
            payByCreditCard();
            break;
        case 2:
        	modeOfPayment="Debit Card";
            payByDebitCard();
            break;
        case 3:
        	modeOfPayment="Net Banking";
            payByNetBanking();
            break;
        default:
            System.out.println("Invalid online payment method selected.\n");
//            System.out.println("Closing the task and everything...");
            System.out.println("Thanks For using our Service !!");
            new BookingStatus().bookingStatusCancelled();
            System.exit(0);
            
    }
    }
    
    public void payByCreditCard() {
        // Logic for credit card payment
    	System.out.println("Enter credit card number...");
        long cardNumber = scanner.nextLong();
        addModeOfPayment(modeOfPayment);
        new BookingStatus().bookingStatusSuccessful();
        System.out.println("Payment successful!!");
        
        new PDFgeneration();
		PDFgeneration.generatePdf();
        
        try {
			new SendMail().draftAndSendEmail();
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.exit(0);
    }

    public void payByDebitCard() {
    	
        // Logic for debit card payment
        System.out.println("Enter debit card number...");
        long cardNumber = scanner.nextLong();
        System.out.println("Enter CVV number");
        int cvvNumber = scanner.nextInt();
        System.out.println("Enter expiry details(MM/YY)");
        scanner.nextLine();
        String expiryDetails = scanner.nextLine();
        
        addModeOfPayment(modeOfPayment);
        new BookingStatus().bookingStatusSuccessful();
        System.out.println("Payment successful !!");
        
        new PDFgeneration();
		PDFgeneration.generatePdf();
        
        try {
			new SendMail().draftAndSendEmail();
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.exit(0);
    }

    public void payByNetBanking() {
        
    	// Logic for net banking payment
        System.out.println("Enter net banking number");
        long netBankingNumber = scanner.nextLong();
        
        addModeOfPayment(modeOfPayment);
        new BookingStatus().bookingStatusSuccessful();
        System.out.println("Payment successful !!");
        
        new PDFgeneration();
		PDFgeneration.generatePdf();
        
        try {
			new SendMail().draftAndSendEmail();
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.exit(0);
        
    }
    
    public void addModeOfPayment(String modeOfPayment) {
    	
    	// Assuming you have a table named TransactionalDetails with a column named ModeOfPayment
        String updateQuery = "UPDATE TransactionalDetails SET ModeOfPayment = ? WHERE UniqueId = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, modeOfPayment);
            preparedStatement.setString(2, UserControl.uniqueId); // Assuming UserControl.uniqueId contains the unique identifier for the transaction
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    }
}