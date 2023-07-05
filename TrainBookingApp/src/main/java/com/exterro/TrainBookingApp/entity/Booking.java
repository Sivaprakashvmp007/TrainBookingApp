package com.exterro.TrainBookingApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;

	@ManyToOne
	@JoinColumn(name="trainId")
	private Train train;

	@ManyToOne
	@JoinColumn(name="passengerId")
	private Passenger passenger;

	
	private int noOfTickets;

	public Booking() {
		super();
	}

	public Booking(int bookingId, Train train, Passenger passenger, int noOfTickets) {
		super();
		this.bookingId = bookingId;
		this.train = train;
		this.passenger = passenger;
		this.noOfTickets = noOfTickets;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", train=" + train + ", passenger=" + passenger + ", noOfTickets="
				+ noOfTickets + "]";
	}

}
