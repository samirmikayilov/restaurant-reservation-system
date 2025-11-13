package az.samir.restaurant_reservation.mapper;


import az.samir.restaurant_reservation.dto.ReservationRequest;
import az.samir.restaurant_reservation.dto.ReservationResponse;
import az.samir.restaurant_reservation.entity.AppUser;
import az.samir.restaurant_reservation.entity.Reservation;
import az.samir.restaurant_reservation.entity.ReservationStatus;
import az.samir.restaurant_reservation.entity.RestaurantTable;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation toEntity(ReservationRequest req, AppUser user, RestaurantTable table) {
        return Reservation.builder()
                .reservationDate(req.getReservationDate())
                .reservationTime(req.getReservationTime())
                .peopleCount(req.getPeopleCount())
                .user(user)
                .table(table)
                .status(ReservationStatus.PENDING)
                .build();
    }

    public ReservationResponse toResponse(Reservation reservation) {
        ReservationResponse resp = new ReservationResponse();
        resp.setId(reservation.getId());
        resp.setReservationDate(reservation.getReservationDate());
        resp.setReservationTime(reservation.getReservationTime());
        resp.setPeopleCount(reservation.getPeopleCount());
        resp.setStatus(reservation.getStatus().name());
        resp.setUserId(reservation.getUser().getId());
        resp.setTableId(reservation.getTable().getId());
        return resp;
    }
}
