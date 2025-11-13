package az.samir.restaurant_reservation.dto;
import lombok.Data;
@Data
public class RestaurantTableRequest {
    private Integer tableNumber;
    private Integer capacity;
}
