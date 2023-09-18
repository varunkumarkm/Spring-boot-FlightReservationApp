package com.flightApp.flightservices.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateReservationRequest {

	private int Id;
	private boolean checkIn;
	private int numberOfBags;
	
}
