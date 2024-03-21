package com.raman;

import java.util.Scanner;

public class TicketBookingSystem {
	
	public static void LogIn() {
		
		Scanner scanner = new Scanner(System.in);
		UserLogin ul=new UserLogin(); //object of userlogin
		AdminControl ac = new AdminControl();	//object of admincontrol
		UserControl uc = new UserControl();	//object of usercontrol
		
		ul.validatingCredentials();
		String name = ul.username;
		
		if(ul.role.equalsIgnoreCase("admin")){
			
			System.out.println("What would you like to do Admin ?");
			System.out.println("1. Add Theatre\n2. Edit Theater\n3. Remove Theater\n4. Add Movie\n5. Edit Movie\n6. Remove Movie\n7. View Transactional Details ");
			int choice = scanner.nextInt();
	        scanner.nextLine(); // Consume newline character
	        
	        switch (choice) 
	        {
	            case 1:
	                ac.addTheater();
	                break;
	            case 2:
	                ac.editTheater();
	                break;
	            case 3:
	                ac.removeTheater();
	                break;
	            case 4:
	                ac.addMovie();
	                break;
	            case 5:
	                ac.editMovie();
	                break;
	            case 6:
	                ac.removeMovie();
	                break;
	            case 7:
	                ac.viewTransactionalDetails();
	                break;
	            default:
	                System.out.println("Invalid choice. Please try again.");}
			
		}else if (ul.role.equalsIgnoreCase("user")) {
			System.out.println("Welcome Cinephile!!!   " + name);
			uc.displayMovieChart();
		}else {
			System.out.println("Invalid Credentials!!! Try Again.....");
		}
	}

	public static void main(String[] args) {
		
		LogIn();
		
		}
	
}
