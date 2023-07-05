package com.exterro.TrainBookingApp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Train {
	@Id
	private int trainId;
	private String trainName;
	private String source;
	private String destination;
	private int noOfTicketsAvailable;
	private int noOfTicketsBooked;

	public Train() {
		super();
	}

	public Train(int trainId, String trainName, String source, String destination, int noOfTicketsAvailable,
			int noOfTicketsBooked) {
		super();
		this.trainId = trainId;
		this.trainName = trainName;
		this.source = source;
		this.destination = destination;
		this.noOfTicketsAvailable = noOfTicketsAvailable;
		this.noOfTicketsBooked = noOfTicketsBooked;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getNoOfTicketsAvailable() {
		return noOfTicketsAvailable;
	}

	public void setNoOfTicketsAvailable(int noOfTicketsAvailable) {
		this.noOfTicketsAvailable = noOfTicketsAvailable;
	}

	public int getNoOfTicketsBooked() {
		return noOfTicketsBooked;
	}

	public void setNoOfTicketsBooked(int noOfTicketsBooked) {
		this.noOfTicketsBooked = noOfTicketsBooked;
	}

	@Override
	public String toString() {
		return "Train [trainId=" + trainId + ", trainName=" + trainName + ", source=" + source + ", destination="
				+ destination + ", noOfTicketsAvailable=" + noOfTicketsAvailable + ", noOfTicketsBooked="
				+ noOfTicketsBooked + "]";
	}

}
