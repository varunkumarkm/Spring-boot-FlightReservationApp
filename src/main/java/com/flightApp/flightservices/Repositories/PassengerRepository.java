package com.flightApp.flightservices.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flightApp.flightservices.Entities.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}
