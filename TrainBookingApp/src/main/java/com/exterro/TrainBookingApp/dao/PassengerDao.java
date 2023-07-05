package com.exterro.TrainBookingApp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.exterro.TrainBookingApp.entity.Passenger;

public class PassengerDao {

	SessionFactory sessionfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	public void addPassenger() {

		Session session = sessionfactory.openSession();

		session.beginTransaction();
		Passenger passenger = new Passenger("arun@gmail.com", "arun");
		session.save(passenger);

		session.getTransaction().commit();
		session.close();
			
	}

	public void getAllPassenger() {
		Session session = sessionfactory.openSession();

		session.beginTransaction();

		Criteria passengerCriteria = session.createCriteria(Passenger.class);
		
		List<Passenger> passengerList = passengerCriteria.list();

		for (Passenger pr : passengerList) {
			System.out.println("Passenger Id: " + pr.getPassengerId() + "Passenger Name: " + pr.getPassengerName()
					+ "Passenger Email: " + pr.getEmailId());
		}
		
		session.close();

	}
}
