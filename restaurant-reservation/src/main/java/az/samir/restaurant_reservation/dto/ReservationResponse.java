package az.samir.restaurant_reservation.dto;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationResponse {

    private Long id;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private Integer peopleCount;
    private String status;
    private Long userId;
    private Long tableId;
}
