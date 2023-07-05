package com.exterro.TrainBookingApp;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exterro.TrainBookingApp.dao.BookingDao;
import com.exterro.TrainBookingApp.dao.PassengerDao;
import com.exterro.TrainBookingApp.dao.TrainDao;
import com.exterro.TrainBookingApp.entity.Passenger;

/**
 * 
 *
 */
public class App

{
	// defining logger class
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws InterruptedException {

		logger.info("In Main Method");

		// creating scanner object
		Scanner scanner = new Scanner(System.in);
		BookingDao bookingDao = BookingDao.getInstance();
		TrainDao trainDao = new TrainDao();
		PassengerDao passengerDao=new PassengerDao();
		System.out.println("Enter 1 to Continue Or 2 to Exit");

		while (true) {
			int number = scanner.nextInt();
			if (number == 1) {
				System.out
						.println("Enter 'ADMIN' to Continue as Admin or 'USER' to Continue as User: or 'EXIT' to Exit");

				String input = scanner.next();

				if (input.equals("ADMIN")) {
					System.out.println("Welcome Admin!!!");

					while (true) {
						System.out.println("Press 1 for Train");
						System.out.println("Press 2 for Passenger");
						System.out.println("Press 3 for Booking");
						System.out.println("Press 4 to Exit");

						int number1 = scanner.nextInt();

						switch (number1) {
						case 1:
							
							System.out.println("Train CRUD");
							System.out.println("1. Add Train (ADD)");
							System.out.println("2. Get All Train (GETALL)");
							System.out.println("3. Get Train (GET)");
							System.out.println("4. Delete Train (DELETE)");
							String input1 = scanner.next();

							switch (input1) {
							case "ADD":
								trainDao.addTrain();
								logger.info("Train Added Successfully!!!");
								break;
							case "GETALL":
								trainDao.getAllTrain();
								break;
							case "GET":
								System.out.println("Enter the Train Id");
								int trainId = scanner.nextInt();
								trainDao.getTrain(trainId);
								break;
							case "DELETE":
								System.out.println("Enter the TrainId to delete: ");
								int trainId11 = scanner.nextInt();
								trainDao.deleteTrain(trainId11);
								break;
							}
							break;

						case 2:
							
							/*passengerDao.addPassenger();*/
							passengerDao.getAllPassenger();
							break;

						case 3:
							System.out.println("Enter Train Id: ");
							int trainId1 = scanner.nextInt();
							System.out.println("Enter Passenger Id:");
							int passengerId1 = scanner.nextInt();
							System.out.println("Enter the Number Of Tickets:");
							int numberOfTickets1 = scanner.nextInt();

							try {
								bookingDao.bookTicket(passengerId1, trainId1, numberOfTickets1);
							} catch (InterruptedException e) {
								logger.info("Unable To Book Train..");
							}

							System.out.println("Enter the Booking Id to Cancel Booking: ");
							int bookingId = scanner.nextInt();
							bookingDao.cancelTicket(bookingId);

							bookingDao.getAllBookings();
							break;

						case 4:
							// Exits the method
							System.out.println("You Transaction is Cancelled!!!");
							return;
						}
					}
				}

				while (true) {
					if (input.equals("USER")) {

						System.out.println("Welcome User..!!!\n\n");
						System.out.println("Press 1 to Continue Or 2 to Exit.");
						int num = scanner.nextInt();
						switch (num) {
						case 1:
							System.out.println("Trains Available...!!!\n\n");
							trainDao.getAllTrain();
							System.out.println("Enter Train Id: ");
							int trainId2 = scanner.nextInt();
							System.out.println("Enter Passenger Id:");
							int passengerId2 = scanner.nextInt();
							System.out.println("Enter the Number Of Tickets:");
							int numberOfTickets2 = scanner.nextInt();

							try {
								bookingDao.bookTicket(passengerId2, trainId2, numberOfTickets2);
							} catch (InterruptedException e) {

								logger.info("Unable To Book Train..");
							}
							System.out.println("Enter the Booking Id to Cancel Booking: ");
							int bookingId1 = scanner.nextInt();
						
							bookingDao.cancelTicket(bookingId1);

							bookingDao.getAllBookings();
							break;

						case 2:
							System.out.println("Your Transaction is Cancelled!!!");
							return;
						}
					}
					if (input.equals("EXIT"))

						System.out.println("Exit!!!");
					else

						System.out.println("Exit!!!");
					
				}
			}
		scanner.close();
		}
	}
}
