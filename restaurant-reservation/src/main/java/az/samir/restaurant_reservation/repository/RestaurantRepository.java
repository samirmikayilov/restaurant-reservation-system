package az.samir.restaurant_reservation.repository;

import az.samir.restaurant_reservation.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}

