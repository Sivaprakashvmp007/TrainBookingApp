package com.exterro.TrainBookingApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Passenger {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int passengerId;
	private String passengerName;
	private String emailId;
	
	
	public Passenger() {
		super();
	}

	public Passenger(String passengerName, String emailId) {
		super();
		this.passengerName = passengerName;
		this.emailId = emailId;

	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", passengerName=" + passengerName + ", emailId=" + emailId
				+  "]";
	}
	
}
