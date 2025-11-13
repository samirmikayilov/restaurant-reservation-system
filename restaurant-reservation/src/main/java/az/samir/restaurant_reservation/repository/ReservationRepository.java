package az.samir.restaurant_reservation.repository;


import az.samir.restaurant_reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByTableIdAndReservationDate(Long tableId, LocalDate date);
}
