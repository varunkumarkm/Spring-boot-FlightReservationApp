package com.flightApp.flightservices.Integration;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.flightApp.flightservices.Entities.Flight;
import com.flightApp.flightservices.Entities.Passenger;
import com.flightApp.flightservices.Entities.Reservation;
import com.flightApp.flightservices.Repositories.FlightRepository;
import com.flightApp.flightservices.Repositories.PassengerRepository;
import com.flightApp.flightservices.Repositories.ReservationRepository;
import com.flightApp.flightservices.dto.CreateReservationRequest;
import com.flightApp.flightservices.dto.UpdateReservationRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationRestController {

	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private PassengerRepository pessengerRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	//http://localhost:8080/flightservices/flights?from=AUS&to=NYC&departureDate=2023-10-20
	@RequestMapping(value = "/flights", method = RequestMethod.GET)
	public List<Flight> findFlights(@RequestParam("from") String from, @RequestParam("to")String to, 
			@RequestParam("departureDate") @DateTimeFormat(pattern="yyy-MM-dd") Date departureDate) {
		return flightRepo.findFlights(from, to, departureDate);
	}
	
	@RequestMapping("/flights/{id}")
	public Flight findFlight(@PathVariable("id") int id) {
		return flightRepo.findById(id).get();
		
	}
	
	@RequestMapping(value = "/reservations", method = RequestMethod.POST)
	@Transactional
	public Reservation saveReservation(@RequestBody CreateReservationRequest request) {
		Flight flight = flightRepo.findById(request.getFlightId()).get();
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setMiddleName(request.getPassengerMiddleName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		
		Passenger saveedPassenger = pessengerRepo.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(saveedPassenger);
		reservation.setCheckedIn(false);
		
		return	reservationRepo.save(reservation);	
	}
	
	//localhost:8080/flightservices/reservations/id
	@RequestMapping(value = "/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") int id) {
		return reservationRepo.findById(id).get();
	}
	
	@RequestMapping(value = "/reservations", method = RequestMethod.PUT)
	public Reservation updateReservation(@RequestBody UpdateReservationRequest request) {
		Reservation reservation = reservationRepo.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.isCheckIn());	    
		return reservationRepo.save(reservation);
	}
 }











