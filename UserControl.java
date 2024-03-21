package com.raman;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserControl 
{
	
	JDBC jdbc = new JDBC();
	Connection connection=jdbc.establishConnection();
	
	public static int movieId=0;
	public static  int theaterId= 0;
	public static int numberOfSeats =0;
	public static int totalPrice = 0;
	public static String uniqueId=null;
	public static String showDate=null;
	public static String showTime=null;
	public static String emailId=null;
	public static String movieName=null;
	public static String genre=null;
	public static String theaterName=null;
	public static int finalPrice = 0;
	public static String showTiming=null;
	
	public void displayMovieChart() {
		
		try {
			UserControl ua = new UserControl();
			Scanner sc = new Scanner(System.in);
			
            // Execute SQL query to retrieve data from MovieDetails and TheaterDetails tables
            Statement statement = connection.createStatement();
            String query = "SELECT MovieDetails.MovieId, MovieName, Genre, Duration_in_min, TicketPrice, MovieDetails.TheaterId, TheaterName, Location, ShowDate1,ShowDate2,ShowDate3 FROM MovieDetails INNER JOIN TheaterDetails ON MovieDetails.TheaterId = TheaterDetails.TheaterId INNER JOIN ShowDetails ON MovieDetails.MovieId = ShowDetails.MovieId;";
            ResultSet resultSet = statement.executeQuery(query);

            // Display combined table
            
            System.out.println("+-----------+--------------------------------+----------------------+---------------+----------+--------------+--------------------------------+--------------------+--------------+------------------+------------------+");
            System.out.println("| Movie ID  |           Movie Name           |        Genre         |   Duration    |  Price   | Theater ID   |        Theater Name            |    Location        | Show Date 1  |   Show Date 2    |    Show Date 3   |");
            System.out.println("+-----------+--------------------------------+----------------------+---------------+----------+--------------+--------------------------------+--------------------+--------------+------------------+------------------+");
            
            while (resultSet.next()) {
                int movieId = resultSet.getInt("MovieId");
                String movieName = resultSet.getString("MovieName");
                String genre = resultSet.getString("Genre");
                int duration = resultSet.getInt("Duration_in_min");
                int ticketPrice = resultSet.getInt("TicketPrice");
                int theaterId = resultSet.getInt("TheaterId");
                String theaterName = resultSet.getString("TheaterName");
                String location = resultSet.getString("Location");
                String showDate1 = resultSet.getString("ShowDate1");
                String showDate2 = resultSet.getString("ShowDate2");
                String showDate3 = resultSet.getString("ShowDate3");
                
                System.out.printf("| %-9s | %-30s | %-20s | %-13s | %-8s | %-12s | %-30s | %-18s | %-12s | %-16s |", 
                    movieId, movieName, genre, duration, ticketPrice, theaterId, theaterName, location, showDate1, showDate2);
                System.out.println("   "+showDate3);
            }
            
            System.out.println();
            System.out.println("PLease select the following available actions.");
            boolean isValidInput = false;
            
            while (!isValidInput) {
                // Display menu options
                System.out.println("1. Book A ticket");
                System.out.println("2. Exit");
                System.out.println();
                System.out.print("Enter your choice: ");
                
                // Read user input
                int choice = sc.nextInt();
                sc.nextLine();
                
                // Process user input
                switch (choice) {
                    case 1:
                        // Perform action for option 1
                        isValidInput = true;
                        ua.selectionProcess();
                        break;
                    case 2:
                        // Perform action for option 2
                        isValidInput = true;
                        System.out.println("Please visit us again.");
                        break;
                    default:
                        // Invalid input, prompt user to enter again
                        System.out.println("Invalid input. Please enter a valid option.");
                        break;
                }
            }
            
            sc.close();
            
            
            // Close connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	//method to book the ticket
	public void selectionProcess() throws SQLException{
		
		Scanner sc = new Scanner(System.in);
		Countdown cd = new Countdown();
		UserControl userControl = new UserControl();
		
		System.out.println("Please Be patient");
		
        int userId = -1; // Default value if user is not found
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT UserId FROM UserCredentials WHERE Username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, new UserLogin().username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("UserId");
            }
            }catch(SQLException e) {
            	e.printStackTrace();
            }
        
        System.out.println("User Id is: "+ userId);
        System.out.println("Press any button to proceed");
        sc.nextLine();
        System.out.println("Enter the name for billing purpose: ");
        String username = sc.nextLine();
        
        System.out.println("Enter movieId: ");
        movieId = sc.nextInt();
        
        System.out.println("Enter theaterId: ");
        theaterId = sc.nextInt();
        
     // Fetch movie details based on MovieId
        String movieDetailsQuery = "SELECT MovieName, Duration_in_min, Genre, TicketPrice FROM MovieDetails WHERE MovieId = ?";
        PreparedStatement movieDetailsStatement = connection.prepareStatement(movieDetailsQuery);
        movieDetailsStatement.setInt(1, movieId);
        ResultSet movieDetailsResult = movieDetailsStatement.executeQuery();

        movieName = null;
        int duration = 0;
        genre = null;
        int ticketPrice = 0;

        if (movieDetailsResult.next()) {
            movieName = movieDetailsResult.getString("MovieName");
            duration = movieDetailsResult.getInt("Duration_in_min");
            genre = movieDetailsResult.getString("Genre");
            ticketPrice = movieDetailsResult.getInt("TicketPrice");
        } else {
            System.out.println("Movie with ID " + movieId + " not found.");
            return;
        }
        
     // Fetch theater details based on TheaterId
        String theaterDetailsQuery = "SELECT TheaterName, Location FROM TheaterDetails WHERE TheaterId = ?";
        PreparedStatement theaterDetailsStatement = connection.prepareStatement(theaterDetailsQuery);
        theaterDetailsStatement.setInt(1, theaterId);
        ResultSet theaterDetailsResult = theaterDetailsStatement.executeQuery();

        theaterName = null;
        String theaterLocation = null;

        if (theaterDetailsResult.next()) {
            theaterName = theaterDetailsResult.getString("TheaterName");
            theaterLocation = theaterDetailsResult.getString("Location");
        } else {
            System.out.println("Theater with ID " + theaterId + " not found.");
            return;
        }
        
        userControl.validateDate();
        
        System.out.println("Select the Show Id as per the corresponding Timing\n\n1. Morning(09:00 AM)\n2. Afternoon(01:00 PM\n3. Evening(05:00 PM\n4. Night(09:00 PM");
        
        while (true) {
            showTime = sc.nextLine();
            
            // Check if the input is valid (1, 2, 3, or 4)
            if (showTime.matches("[1-4]")) {
                // Valid input
                break;
            } else {
                System.out.println("Please enter 1, 2, 3, or 4.");
            }
        }
        
        showTiming = "";
        
        int showTimeValue = Integer.parseInt(showTime);
        
        switch(showTimeValue) {
        case 1: showTiming="09:00 AM";
        	break;
        case 2: showTiming="01:00 PM";
    		break;
        case 3: showTiming="05:00 PM";
    		break;
        case 4: showTiming="09:00 PM";
    		break;
    	
        }
        
        System.out.println("Enter numberOfSeats: ");
        numberOfSeats = sc.nextInt();
        
        sc.nextLine();
        System.out.println("Enter Your E-mail Id: ");
        emailId = sc.nextLine();
        
        System.out.println("");
        new UserControl().validateAndStoreUniqueId();
        
        totalPrice=calculateTotalPrice(movieId, theaterId, numberOfSeats);
        finalPrice=totalPrice;
        
        if(totalPrice==0) {
        	System.exit(showTimeValue);
        }
        

        // Insert booking details into the database
        String insertQuery = "INSERT INTO TransactionalDetails (UserId, Username, MovieId, MovieName, TheaterId, TheaterName, Location, NumberOfSeats, Price_before_coupon, Email_id, ShowDate, ShowTime, UniqueId) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setLong(1, userId);
        preparedStatement.setString(2, username);
        preparedStatement.setInt(3, movieId);
        preparedStatement.setString(4, movieName);
        preparedStatement.setInt(5, theaterId);
        preparedStatement.setString(6, theaterName);
        preparedStatement.setString(7, theaterLocation);
        preparedStatement.setInt(8, numberOfSeats);
        preparedStatement.setInt(9, totalPrice);
        preparedStatement.setString(10, emailId);
        preparedStatement.setString(11, showDate);
        preparedStatement.setString(12, showTiming);
        preparedStatement.setString(13, uniqueId);
        preparedStatement.executeUpdate();

        cd.timeCheck();
        
        // Close resources
        preparedStatement.close();
        connection.close();
    } 
    
    public void validateDate() {
    	
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Please Enter the date(YY-MM-DD) on which you would like to watch the movie : \n\nNote* Kindly refer the above table to see the movie availibility");
        showDate = scanner.nextLine();
    	boolean isValidDate = false;
    	
    	while (!isValidDate) {
    		
    		// Check if showDate is empty
            if (showDate.isEmpty()) {
                System.out.println("Date cannot be empty. Please enter again:");
                showDate = scanner.nextLine(); // Read the next line again
                continue; // Skip further processing in this iteration
            }
    		
            // SQL query to check if the provided date exists for the current movie and theater
            String query = "SELECT COUNT(*) AS CountExists " +
                           "FROM ShowDetails sd " +
                           "JOIN MovieDetails md ON sd.MovieId = md.MovieId " +
                           "WHERE (md.MovieId = ?) " +
                           "AND (sd.ShowDate1 = ? OR sd.ShowDate2 = ? OR sd.ShowDate3 = ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, movieId);
                preparedStatement.setString(2, showDate);
                preparedStatement.setString(3, showDate);
                preparedStatement.setString(4, showDate);

                ResultSet resultSet = preparedStatement.executeQuery();

                // If the count is greater than 0, the date is valid
                if (resultSet.next() && resultSet.getInt("CountExists") > 0) {
                    System.out.println("Date is valid.");
                    isValidDate = true;
                } else {
                    System.out.println("Date is not valid. Please enter again:");
                    
					showDate = scanner.nextLine();
                }
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
		
	public  int calculateTotalPrice(int movieId, int theaterId, int numberOfSeats) throws SQLException {
        int ticketPrice = 0;
        
        // Fetch ticket price based on MovieId and TheaterId
        String ticketPriceQuery = "SELECT TicketPrice FROM MovieDetails WHERE MovieId = ? AND TheaterId = ?";
        PreparedStatement ticketPriceStatement = connection.prepareStatement(ticketPriceQuery);
        ticketPriceStatement.setInt(1, movieId);
        ticketPriceStatement.setInt(2, theaterId);
        ResultSet ticketPriceResult = ticketPriceStatement.executeQuery();
        if (ticketPriceResult.next()) {
            ticketPrice = ticketPriceResult.getInt("TicketPrice");
        } else {
            System.out.println("Ticket price not found for the given movie and theater combination.");
            return 0;
        }
        return numberOfSeats * ticketPrice;
    }
    
    //method for the functions related to unique id
    
 // Method to validate and store unique ID
    public void validateAndStoreUniqueId() {
        Scanner scanner = new Scanner(System.in);

        boolean isValidId = false;

        while (!isValidId) {
            System.out.println("Please enter a uniqueId of your choice: ");
            uniqueId = scanner.nextLine();

            // Check if the uniqueId is already present in the database
            if (isUniqueIdExists(uniqueId)) {
                System.out.println("This ID is not unique. Please try another one.");
            } else {
                isValidId = true;
            }
        }
    }
    
 // Method to check if the uniqueId already exists in the database
    private boolean isUniqueIdExists(String uniqueId) {
        boolean exists = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM TransactionalDetails WHERE UniqueId = ?");
            preparedStatement.setString(1, uniqueId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                exists = count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
    
}
