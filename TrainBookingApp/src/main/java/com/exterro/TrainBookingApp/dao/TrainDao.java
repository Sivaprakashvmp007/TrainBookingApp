package com.exterro.TrainBookingApp.dao;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.exterro.TrainBookingApp.entity.Train;

public class TrainDao {
	
	SessionFactory sessionfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Scanner scanner=new Scanner(System.in);
	public void addTrain() {

		Session session = sessionfactory.openSession();

		session.beginTransaction();
		
		Train train1 = new Train();
		
		System.out.println("Enter Train Id:");
		int trainId=scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter Train Name:");
		String trainName=scanner.next();
		scanner.nextLine();
		System.out.println("Enter Source:");
		String source=scanner.next();
		scanner.nextLine();
		System.out.println("Enter Destination:");
		String destination=scanner.next();
		scanner.nextLine();
		System.out.println("Enter noOfTicketsAvailable:");
		int noOfTicketsAvailable=scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter noOfTicketsBooked:");
		int noOfTicketsBooked=scanner.nextInt();
		scanner.nextLine();
		train1.setTrainId(trainId);
		train1.setTrainName(trainName);
		train1.setSource(source);
		train1.setDestination(destination);
		train1.setNoOfTicketsAvailable(noOfTicketsAvailable);
		train1.setNoOfTicketsBooked(noOfTicketsBooked);
		
		session.save(train1);
		System.out.println("Train Added Successfully!!!");
		session.getTransaction().commit();
		session.close();
	}

	public void getAllTrain() {
		Session session = sessionfactory.openSession();

		session.beginTransaction();

		Criteria trainCriteria = session.createCriteria(Train.class);
		List<Train> trainList = trainCriteria.list();

		for (Train tr : trainList) {
			System.out.println("Train Id:" + tr.getTrainId() + ",\nTrain Name:" + tr.getTrainName() + ",\nSource:"
					+ tr.getSource() + ",\nDestination:" + tr.getDestination() + ",\nNo of Available Tickets: "
					+ tr.getNoOfTicketsAvailable() + ",\nNo of Tickets Booked: " + tr.getNoOfTicketsBooked()+"\n\n");
		}

		session.close();

	}
	public void getTrain(int trainId) {
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		Train train = session.get(Train.class, trainId);
		System.out.println(train);
		session.close();
	}

	public void deleteTrain(int trainId) {
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		Train train = session.get(Train.class, trainId);
		if (train != null) {
			session.delete(train);
			System.out.println("Train Deleted successfully..");
		}
		else {
			System.out.println("Train Not Found..");
		}
		session.getTransaction().commit();
		session.close();

	}

}
