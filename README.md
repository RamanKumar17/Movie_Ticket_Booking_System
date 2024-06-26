
# Movie Booking System

## Overview

The Movie Booking System is a console-based application developed in Core Java with JDBC for MySQL database connectivity. It allows users to book movie tickets, make payments, and receive ticket receipts via email. The system also provides admin functionalities such as managing movies, theaters, and viewing transaction details.
## Backend

## Key Features

### For Admin:

- **Manage Movies**: Add, edit, or delete movie details.
- **Manage Theaters**: Add, edit, or delete theater details.
- **View Transaction Details**: View transaction history and details.

### For User:

- **Select Movie**: Browse available movies and select desired ones.
- **Book Ticket**: Reserve seats for selected movies and make payments.
- **Apply Coupon Code**: Apply coupon codes for discounts on ticket bookings.
- **Generate Ticket Receipt**: Receive a PDF ticket receipt via email after successful booking.

## Installation

1. Clone the repository:
   ```
   bash
   git clone https://github.com/RamanKumar17/Movie_Ticket_Booking_System.git
   ```
   ## Installation
   
1. **Import the project into your Java IDE (Eclipse, IntelliJ, etc.).**
2. **Set up MySQL database:**
   - Create a database named `MovieTicketBooking`.
   - Import the `Movie_ticket_Booking_DB.sql` file provided in the repository to create tables and insert initial data.
3. **Configure MySQL JDBC connection:**
   - Update the JDBC URL, username, and password in the `JDBC.java` file located in the root directory.
4. **Build and run the application from your IDE.**

## Usage

1. **Launch the application.**
2. **Choose whether to login as an admin or user.**
   - If logging in as admin:
     - Perform admin operations such as managing movies, theaters, or viewing transaction details.
   - If logging in as a user:
     - Browse available movies, select desired ones, and book tickets.
     - Apply coupon codes if available.
     - Complete the payment process.
     - Receive a PDF ticket receipt via email after successful booking.

## Dependencies

- Java Development Kit (JDK)
- MySQL Server
- MySQL Connector/J (JDBC driver)

## ScreenShots 

- LoginPage
  
![Login Screen Dempo](Screenshots/loginPage.png)

- DisplayDetails
  
![Display Details Demo](Screenshots/DisplayDetails.png)

- BookingProcess
  
![Booking Process Demo](Screenshots/BookingProcess.png)

- SelectedDetails
  
![Selected Details Dempo](Screenshots/SelectedDetails.png)

- PaymentProcessing
  
![Payment Processing Dempo](Screenshots/PaymentProcessing.png)

- CouponCode
  
![Coupon Code Dempo](Screenshots/CouponCode.png)

- ModeOfPayment

  #Frontend

  # Project Frontend

Welcome to Movie_Ticket_Booking_System Frontend!

## Overview

This frontend repository serves as the user interface component of Movie_Ticket_Booking_System. It's built with HTML, CSS & JavaScript providing a seamless and intuitive experience for users interacting with our application.

## Features

- Login Page (Admin/User)
- Admin's Control PAge
- Movie Selection Page
- Payment Page

## ScreenShots

- LoginPage
  
![LoginPage](Screenshots/inndex.png)

- Admin Control Page
  
![AdminControl](Screenshots/AdminControl.png)

- SelectMovies Page
  
![SelectMovies](Screenshots/SelectMovies.png)

- Payment Page
  
![Paymentt](Screenshots/Paymentt.png)
