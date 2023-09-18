package com.flightApp.flightservices.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flightApp.flightservices.Entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
