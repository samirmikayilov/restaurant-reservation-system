package az.samir.restaurant_reservation.dto;
import lombok.Data;
import java.time.LocalTime;

@Data
public class RestaurantResponse {

    private Long id;
    private String name;
    private String address;
    private LocalTime openTime;
    private LocalTime closeTime;
}
