package com.raman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Countdown {
	
    final long FIVE_MINUTES = 5 * 60 * 1000; // 5 minutes in milliseconds
    Scanner sc = new Scanner(System.in);
    Timer timer = new Timer();
    UserControl uc = new UserControl();
    
    Connection connection=new JDBC().establishConnection();
    
    public void timeCheck() {
    	
//    	Countdown countdown = new Countdown();
    	
    	BookingStatus bookingStatus = new BookingStatus();
        final int countdownDuration = 30; // seconds

        // Start the timer immediately
        timer.scheduleAtFixedRate(new TimerTask() {
            int remainingSeconds = countdownDuration;

            @Override
            public void run() {
                if (remainingSeconds > 1) {
                    if (remainingSeconds % 20 == 0)
                        System.out.print("\rTime remaining: " + remainingSeconds + " seconds");
                    remainingSeconds--;
                   
                } else {
                	timer.cancel();
                	System.out.println("\nTimes UP!!!!\nTransaction Cancelled");
                	bookingStatus.bookingStatusCancelled();
                }
            }
        }, 0, 1000); // Schedule the task to run every second (1000 milliseconds)

        // Wait for user input, or stop the timer if there's no input after a certain time
        System.out.println();
        System.out.println("Please Make the payment before " + countdownDuration + " seconds or else your transaction will be cancelled.\nNote* The time will be updated after every 20 seconds.");
        System.out.println();
        System.out.println("Enter 1 to proceed to make the payment or any other number to cancel the transaction");
        
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 1) {
                stopTimerAndCallPaymentMethod();
                
            } else {
                closeTaskAndEverything();
                bookingStatus.bookingStatusCancelled();
            }
        } else {
            closeTaskAndEverything();
            bookingStatus.bookingStatusCancelled();
        }
    }

    public void stopTimerAndCallPaymentMethod() {
    	
    	MakePayment makePayment = new MakePayment();
        timer.cancel();
        System.out.println();
        
        //Apply the coupon before making payment
        new ApplyCoupon().applyCoupon();
        
        //make payment after the coupon gets applied
        makePayment.selectPaymentMethod();
        System.out.println();
    }

    public void closeTaskAndEverything() {
    	System.out.println();
        System.out.println("Closing the task and everything...");
        timer.cancel();
        System.out.println();
        System.out.println("Thanks For using our Service !!");
    }

}
