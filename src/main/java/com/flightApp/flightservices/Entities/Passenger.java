package com.flightApp.flightservices.Entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Passenger extends AbstractEntity{

	private String firstName;
	private String lastName;
	private String middleName;
	private String email;
	private String phone;
	
}
