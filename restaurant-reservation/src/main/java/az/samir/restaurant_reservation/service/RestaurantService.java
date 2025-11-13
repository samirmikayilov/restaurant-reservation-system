package az.samir.restaurant_reservation.service;



import az.samir.restaurant_reservation.dto.RestaurantRequest;
import az.samir.restaurant_reservation.dto.RestaurantResponse;
import az.samir.restaurant_reservation.entity.Restaurant;
import az.samir.restaurant_reservation.mapper.RestaurantMapper;
import az.samir.restaurant_reservation.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantResponse create(RestaurantRequest request) {
        Restaurant restaurant = restaurantMapper.toEntity(request);
        restaurantRepository.save(restaurant);
        return restaurantMapper.toResponse(restaurant);
    }

    public List<RestaurantResponse> getAll() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurantMapper::toResponse)
                .toList();
    }
}
