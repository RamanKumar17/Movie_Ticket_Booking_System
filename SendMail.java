package com.raman;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

    public static String mailId = new UserControl().emailId;
    Session newSession = null;
    MimeMessage mimeMessage = null;

    public void draftAndSendEmail() throws MessagingException, IOException {
        if (mailId == null) {
            System.out.println("No email id provided.");
            return;
        }

        // Setup server properties
        setupServerProperties();

        // Draft the email
        draftEmail();

        // Send the email
        sendEmail();
    }

    public void sendEmail() throws MessagingException {
        String fromUser = "kashyapraman1968@gmail.com";
        String fromUserPassword = "clprzpiklkrjxwht";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        
        //generating pdf here
        
        System.out.println("Booking Ticket has been sent to your E-mail !!!");
    }

    public MimeMessage draftEmail() throws AddressException, MessagingException, IOException {
        String emailSubject = "Test Mail";
        String emailBody = "Test Body of my email";
        mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailId));
        mimeMessage.setSubject(emailSubject);

        // CREATE MIMEMESSAGE 
        // CREATE MESSAGE BODY PARTS 
        // CREATE MESSAGE MULTIPART 
        // ADD MESSAGE BODY PARTS ----> MULTIPART 
        // FINALLY ADD MULTIPART TO MESSAGECONTENT i.e. mimeMessage object 

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/html");

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
//        ("/Users/Desktop/CodeTechGyan.png")
        attachmentBodyPart.attachFile(new File("D:\\Invoice_PDF\\ticket.pdf"));

        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        multiPart.addBodyPart(attachmentBodyPart);
        mimeMessage.setContent(multiPart);
        return mimeMessage;
    }

    public void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587"); // or "465" for SSL
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties, null);
    }

}


//the following code can be used to fetch all the mail id from the db and will send to everyone


//package com.mailSetup;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//public class Mail {
//
//	public static String mailId = null;
//    Session newSession = null;
//    MimeMessage mimeMessage = null;
//    
//    public void draftAndSendEmail() throws MessagingException, IOException {
//        // Setup server properties
//        setupServerProperties();
//
//        // Retrieve email recipients from the database
//        String[] emailRecipients = getEmailRecipientsFromDB();
//
//        // If no recipients found, return
//        if (emailRecipients == null || emailRecipients.length == 0) {
//            System.out.println("No email recipients found in the database.");
//            return;
//        }
//
//        // Draft the email
//        draftEmail(emailRecipients);
//
//        // Send the email
//        sendEmail();
//    }
//
//    
//    public String[] getEmailRecipientsFromDB() {
//        // Establish database connection
//        Connection connection = new Jdbc().establishConnection();
//
//        try {
//            if (connection != null) {
//                // Creating statement with a scrollable result set
//                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//
//                // Query to retrieve email recipients
//                String query = "SELECT Mail FROM MailData";
//
//                // Executing query
//                ResultSet resultSet = statement.executeQuery(query);
//
//                // Counting the number of rows
//                resultSet.last(); // Move to the last row to get the count
//                int rowCount = resultSet.getRow();
//                resultSet.beforeFirst(); // Move back to before the first row for iteration
//
//                // Extracting email recipients
//                String[] emailRecipients = new String[rowCount];
//                int i = 0;
//                while (resultSet.next()) {
//                    emailRecipients[i++] = resultSet.getString("Mail");
//                }
//                return emailRecipients;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    public void sendEmail() throws MessagingException {
//        String fromUser = "kashyapraman1968@gmail.com";
//        String fromUserPassword = "clprzpiklkrjxwht";
//        String emailHost = "smtp.gmail.com";
//        Transport transport = newSession.getTransport("smtp");
//        transport.connect(emailHost, fromUser, fromUserPassword);
//        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
//        transport.close();
//        System.out.println("Email successfully sent!!!");
//    }
//
//    public MimeMessage draftEmail(String[] emailRecipients) throws AddressException, MessagingException, IOException {
//        String emailSubject = "Test Mail";
//        String emailBody = "Test Body of my email";
//        mimeMessage = new MimeMessage(newSession);
//
//        for (String recipient : emailRecipients) {
//            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//        }
//        mimeMessage.setSubject(emailSubject);
//
//        // CREATE MIMEMESSAGE 
//        // CREATE MESSAGE BODY PARTS 
//        // CREATE MESSAGE MULTIPART 
//        // ADD MESSAGE BODY PARTS ----> MULTIPART 
//        // FINALLY ADD MULTIPART TO MESSAGECONTENT i.e. mimeMessage object 
//
//        MimeBodyPart bodyPart = new MimeBodyPart();
//        bodyPart.setContent(emailBody, "html/text");
//
//        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
////        ("/Users/Desktop/CodeTechGyan.png")
//        attachmentBodyPart.attachFile(new File("C:\\Users\\Raman Kumar\\Desktop\\Java Programs\\Arraylist.java"));
//
//        MimeMultipart multiPart = new MimeMultipart();
//        multiPart.addBodyPart(bodyPart);
//        multiPart.addBodyPart(attachmentBodyPart);
//        mimeMessage.setContent(multiPart);
//        return mimeMessage;
//    }
//
//    public void setupServerProperties() {
//        Properties properties = System.getProperties();
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587"); // or "465" for SSL
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        newSession = Session.getDefaultInstance(properties, null);
//    }
//
//}

