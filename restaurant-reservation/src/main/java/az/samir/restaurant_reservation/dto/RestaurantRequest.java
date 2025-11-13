package az.samir.restaurant_reservation.dto;
import lombok.Data;

import java.time.LocalTime;

@Data
public class RestaurantRequest {

    private String name;
    private String address;
    private LocalTime openTime;
    private LocalTime closeTime;
}
