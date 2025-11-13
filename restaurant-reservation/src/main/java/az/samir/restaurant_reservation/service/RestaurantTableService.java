package az.samir.restaurant_reservation.service;
import az.samir.restaurant_reservation.dto.RestaurantTableRequest;
import az.samir.restaurant_reservation.dto.RestaurantTableResponse;
import az.samir.restaurant_reservation.entity.Restaurant;
import az.samir.restaurant_reservation.entity.RestaurantTable;
import az.samir.restaurant_reservation.mapper.RestaurantTableMapper;
import az.samir.restaurant_reservation.repository.RestaurantRepository;
import az.samir.restaurant_reservation.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RestaurantTableService {

    private final RestaurantTableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantTableMapper mapper;

    public RestaurantTableResponse addTable(Long restaurantId, RestaurantTableRequest request) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        RestaurantTable table = mapper.toEntity(request, restaurant);
        tableRepository.save(table);

        return mapper.toResponse(table);
    }

    public List<RestaurantTableResponse> getByRestaurant(Long restaurantId) {
        return tableRepository.findAll().stream()
                .filter(t -> t.getRestaurant().getId().equals(restaurantId))
                .map(mapper::toResponse)
                .toList();
    }
}
