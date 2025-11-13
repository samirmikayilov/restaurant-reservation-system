package az.samir.restaurant_reservation.controller;
import az.samir.restaurant_reservation.dto.RestaurantTableRequest;
import az.samir.restaurant_reservation.dto.RestaurantTableResponse;
import az.samir.restaurant_reservation.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/tables")
@RequiredArgsConstructor
public class RestaurantTableController {

    private final RestaurantTableService tableService;

    @PostMapping
    public ResponseEntity<RestaurantTableResponse> addTable(
            @PathVariable Long restaurantId,
            @RequestBody RestaurantTableRequest request) {
        return ResponseEntity.ok(tableService.addTable(restaurantId, request));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantTableResponse>> getTables(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(tableService.getByRestaurant(restaurantId));
    }
}
