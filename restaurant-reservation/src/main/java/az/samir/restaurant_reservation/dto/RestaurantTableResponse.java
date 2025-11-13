package az.samir.restaurant_reservation.dto;
import lombok.Data;

@Data
public class RestaurantTableResponse {
    private Long id;
    private Integer tableNumber;
    private Integer capacity;
    private Long restaurantId;
}
