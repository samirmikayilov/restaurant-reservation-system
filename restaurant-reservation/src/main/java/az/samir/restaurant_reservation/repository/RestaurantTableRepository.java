package az.samir.restaurant_reservation.repository;
import az.samir.restaurant_reservation.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
}
