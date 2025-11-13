package az.samir.restaurant_reservation.mapper;

import az.samir.restaurant_reservation.dto.RestaurantTableRequest;
import az.samir.restaurant_reservation.dto.RestaurantTableResponse;
import az.samir.restaurant_reservation.entity.Restaurant;
import az.samir.restaurant_reservation.entity.RestaurantTable;
import org.springframework.stereotype.Component;

@Component
public class RestaurantTableMapper {

    public RestaurantTable toEntity(RestaurantTableRequest req, Restaurant restaurant) {
        return RestaurantTable.builder()
                .tableNumber(req.getTableNumber())
                .capacity(req.getCapacity())
                .restaurant(restaurant)
                .build();
    }

    public RestaurantTableResponse toResponse(RestaurantTable table) {
        RestaurantTableResponse resp = new RestaurantTableResponse();
        resp.setId(table.getId());
        resp.setTableNumber(table.getTableNumber());
        resp.setCapacity(table.getCapacity());
        resp.setRestaurantId(table.getRestaurant().getId());
        return resp;
    }
}
