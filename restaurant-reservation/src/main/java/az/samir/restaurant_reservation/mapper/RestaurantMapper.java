package az.samir.restaurant_reservation.mapper;



import az.samir.restaurant_reservation.dto.RestaurantRequest;
import az.samir.restaurant_reservation.dto.RestaurantResponse;
import az.samir.restaurant_reservation.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public Restaurant toEntity(RestaurantRequest req) {
        return Restaurant.builder()
                .name(req.getName())
                .address(req.getAddress())
                .openTime(req.getOpenTime())
                .closeTime(req.getCloseTime())
                .build();
    }

    public RestaurantResponse toResponse(Restaurant restaurant) {
        RestaurantResponse resp = new RestaurantResponse();
        resp.setId(restaurant.getId());
        resp.setName(restaurant.getName());
        resp.setAddress(restaurant.getAddress());
        resp.setOpenTime(restaurant.getOpenTime());
        resp.setCloseTime(restaurant.getCloseTime());
        return resp;
    }
}
