package com.flightApp.flightservices.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reservation extends AbstractEntity{

	private boolean checkedIn;
	private int numberOfBags;
	
	@OneToOne
	private Flight flight;
	@OneToOne
	private Passenger passenger;
}
