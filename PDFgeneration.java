package com.raman;

import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
 
public class PDFgeneration {
    private static final String pdfDirectory = "D:/Invoice_PDF/";
    private static final String pdfName = "ticket.pdf";
 
    static void generatePdf() {
        Document document = new Document();
        UserControl userControl = new UserControl();
        try {
            // Ensure that the directory exists
            File directory = new File(pdfDirectory);
            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it doesn't exist
            }
 
            // Create the PDF file
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(new File(pdfDirectory + pdfName)));
            document.open();
 
            document.add(new Paragraph("-----------------------------------------------------------------------------------------"));
 
            Font bigBoldFont = FontFactory.getFont(FontFactory.defaultEncoding, 24);
            Paragraph movieTitle = new Paragraph(userControl.movieName, bigBoldFont);
            movieTitle.setAlignment(Element.ALIGN_LEFT);
            document.add(movieTitle);
 
            document.add(new Paragraph("\n"));
 
            Font smallFont = FontFactory.getFont(FontFactory.defaultEncoding, 14);
            Paragraph genre = new Paragraph(userControl.genre, smallFont);
            genre.setAlignment(Element.ALIGN_LEFT);
            document.add(genre);
 
            document.add(new Paragraph("-----------------------------------------------------------------------------------------"));
            document.add(new Paragraph("\n"));
 
            Paragraph timing = new Paragraph(userControl.showTiming, bigBoldFont);
            timing.setAlignment(Element.ALIGN_LEFT);
            document.add(timing);
 
            document.add(new Paragraph("\n"));
 
            Paragraph theatre = new Paragraph(userControl.theaterName, bigBoldFont);
            theatre.setAlignment(Element.ALIGN_LEFT);
            document.add(theatre);
 
            Paragraph date = new Paragraph(userControl.showDate, bigBoldFont);
            date.setAlignment(Element.ALIGN_LEFT);
            document.add(date);
 
            Paragraph numberOfTickets = new Paragraph("Seats Booked: "+userControl.numberOfSeats, bigBoldFont);
            numberOfTickets.setAlignment(Element.ALIGN_LEFT);
            document.add(numberOfTickets);
 
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
 
            Paragraph bookingId = new Paragraph("Booking ID : WGR"+userControl.uniqueId, bigBoldFont);
            bookingId.setAlignment(Element.ALIGN_CENTER);
            document.add(bookingId);
 
            document.add(new Paragraph("\n"));
 
            Paragraph ticketPrice = new Paragraph("Tickets: "+userControl.numberOfSeats+", Price: "+userControl.finalPrice, smallFont);
            document.add(ticketPrice);
 
            Paragraph cf = new Paragraph("Convenience Fees - 0.00", smallFont);
            document.add(cf);
 
            Paragraph ac = new Paragraph("Additional Charges - 00.00", smallFont);
            document.add(ac);
 
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}