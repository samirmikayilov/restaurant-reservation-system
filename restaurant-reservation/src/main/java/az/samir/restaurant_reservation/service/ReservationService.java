package az.samir.restaurant_reservation.service;


import az.samir.restaurant_reservation.dto.ReservationRequest;
import az.samir.restaurant_reservation.dto.ReservationResponse;
import az.samir.restaurant_reservation.entity.AppUser;
import az.samir.restaurant_reservation.entity.Reservation;
import az.samir.restaurant_reservation.entity.RestaurantTable;
import az.samir.restaurant_reservation.entity.ReservationStatus;
import az.samir.restaurant_reservation.mapper.ReservationMapper;
import az.samir.restaurant_reservation.repository.ReservationRepository;
import az.samir.restaurant_reservation.repository.RestaurantTableRepository;
import az.samir.restaurant_reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RestaurantTableRepository tableRepository;
    private final ReservationMapper mapper;

    public ReservationResponse create(ReservationRequest request) {

        AppUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        RestaurantTable table = tableRepository.findById(request.getTableId())
                .orElseThrow(() -> new RuntimeException("Table not found"));

        List<Reservation> existingReservations =
                reservationRepository.findByTableIdAndReservationDate(
                        request.getTableId(),
                        request.getReservationDate()
                );

        LocalTime newStart = request.getReservationTime();
        LocalTime newEnd = newStart.plusHours(2);

        for (Reservation res : existingReservations) {
            LocalTime existStart = res.getReservationTime();
            LocalTime existEnd = existStart.plusHours(2);

            if (newStart.isBefore(existEnd) && newEnd.isAfter(existStart)) {
                throw new RuntimeException("Bu saat üçün masa doludur, başqa vaxt seçin!");
            }
        }

        Reservation reservation = mapper.toEntity(request, user, table);
        reservation.setStatus(ReservationStatus.PENDING);
        reservationRepository.save(reservation);

        return mapper.toResponse(reservation);
    }

    public List<ReservationResponse> getAll() {
        return reservationRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
