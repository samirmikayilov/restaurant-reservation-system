package az.samir.restaurant_reservation.dto;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationRequest {

    private Long userId;
    private Long tableId;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private Integer peopleCount;
}
