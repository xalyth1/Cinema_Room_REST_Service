package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@RestController
public class Contoller {

    private final int CINEMA_ROWS = 9;
    private final int CINEMA_COLUMNS = 9;

    Cinema cinema = new Cinema(CINEMA_ROWS, CINEMA_COLUMNS);

    /**
     * Get AVAILABLE seats
     *
     * @return list of available seats
     */
    @GetMapping("/seats")
    public Cinema getSeats() {
        cinema.updateAvailableSeats();
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Seat> purchase(@RequestBody SeatInfo seatInfo) {
        int row = seatInfo.getRow();
        int column = seatInfo.getColumn();
        Optional<Seat> optional = cinema.getSeat(row, column);

        if (optional.isPresent()) {
            Seat seat = optional.get();
            boolean isAvailable = cinema.seats.get(seat).booleanValue();
            if (isAvailable) {
                cinema.seats.put(seat, false);
                return new ResponseEntity<>(seat, HttpStatus.OK);
            } else {
                return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }

    }
}
