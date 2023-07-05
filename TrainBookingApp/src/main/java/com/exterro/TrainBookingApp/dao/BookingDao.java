package com.exterro.TrainBookingApp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exterro.TrainBookingApp.entity.Booking;
import com.exterro.TrainBookingApp.entity.Passenger;
import com.exterro.TrainBookingApp.entity.Train;

public class BookingDao {
	private static final Logger logger = LoggerFactory.getLogger(BookingDao.class);

	// private static variable
	private static BookingDao instance;
	private final SessionFactory sessionFactory= new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();


	// private constructor
	private BookingDao() {
	}

	// method which returns that instance
	public static synchronized BookingDao getInstance() {
		if (instance == null) {
			instance = new BookingDao();
		}
		return instance;
	}

	public void bookTicket(final int passengerId, final int trainId, final int noOfTickets)
	        throws InterruptedException {
	    Thread bookThread = new Thread(() -> {
	        try (Session session = sessionFactory.openSession()) {
	            session.beginTransaction();

	            Passenger passenger = session.get(Passenger.class, passengerId);
	            Train train = session.get(Train.class, trainId);

	            if (passenger != null && train != null) {
	                logger.info("Passenger And Train Available");

	                if (train.getNoOfTicketsAvailable() > noOfTickets) {
	                    Booking booking = new Booking();
	                    booking.setPassenger(passenger);
	                    booking.setTrain(train);
	                    booking.setNoOfTickets(noOfTickets);

	                    session.save(booking);

	                    int noOfTicketsAvailable = train.getNoOfTicketsAvailable() - noOfTickets;
	                    train.setNoOfTicketsAvailable(noOfTicketsAvailable);
	                    int noOfTicketsBooked = train.getNoOfTicketsBooked() + noOfTickets;
	                    train.setNoOfTicketsBooked(noOfTicketsBooked);
	                    session.update(train);

	                    session.getTransaction().commit();
	                    System.out.println("Booking created successfully.");
	                    logger.info("Booking created successfully.");
	                } else {
	                    System.out.println("Availability of Tickets is Less!!!");
	                    logger.info("Availability of Tickets is Less!!!");
	                }
	            } else {
	                logger.info("Passenger or Train not found. Unable to create booking.");
	                System.out.println("Passenger or Train not found. Unable to create booking.");
	            }
	        } catch (Exception e) {
	            logger.info("Exception Occurs: ".concat(e.getMessage()));
	        }
	    });
	    bookThread.start();
	    bookThread.join();
	}


	public void cancelTicket(final int bookingId) {
		Thread cancelThread = new Thread(()-> {
			
				try (Session session = sessionFactory.openSession()) {
					session.beginTransaction();
					
					Booking booking = session.get(Booking.class, bookingId);
					
					Train train=booking.getTrain();
					Passenger passenger=booking.getPassenger();
					if(passenger.equals(booking.getPassenger())) {
					session.delete(booking);
					
					booking.getBookingId();
					int noOfTicketsAvailable=train.getNoOfTicketsAvailable()+booking.getNoOfTickets();
					int noOfTicketsBooked=train.getNoOfTicketsBooked()-booking.getNoOfTickets();
					train.setNoOfTicketsAvailable(noOfTicketsAvailable);
					train.setNoOfTicketsBooked(noOfTicketsBooked);
					session.update(train);
					session.getTransaction().commit();
					logger.error("Booking Cancelled!!!");
					System.out.println("Booking Canceled!!!");
					}
					else {
						System.out.println("Enter Your Correct Booking Id!!!");
					}
					
				} catch (Exception e) {
					logger.info("Exception Occurred: ".concat(e.getMessage()));
				}
			
		});
		cancelThread.start();
	}

	public void getAllBookings() {
		Thread getAllThread = new Thread(() -> {
			
				try (Session session = sessionFactory.openSession()) {
					session.beginTransaction();

					@SuppressWarnings("deprecation")
					Criteria bookingCriteria = session.createCriteria(Booking.class);
					@SuppressWarnings("unchecked")
					List<Booking> bookingList = bookingCriteria.list();

					for (Booking bg : bookingList) {
						System.out.printf(
								"Booking Id: " + bg.getBookingId() + ", Train Id: " + bg.getTrain().getTrainId()
										+ ", Passenger Id: " + bg.getPassenger().getPassengerId() + "No Of Tickets: ",
								bg.getNoOfTickets());
						}

					session.getTransaction().commit();
				} catch (Exception e) {
					logger.info("Exception Occurred" + e.getMessage());
				}
			
		});
		getAllThread.start();
	}
}
