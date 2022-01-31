package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class Controller {

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
        synchronized (cinema) {
            cinema.updateAvailableSeats();
        }
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Purchase> purchase(@RequestBody SeatInfo seatInfo) {
        int row = seatInfo.getRow();
        int column = seatInfo.getColumn();
        Optional<Seat> optional = cinema.getSeat(row, column);

        if (optional.isPresent()) {
            Seat seat = optional.get();
            boolean isAvailable = cinema.seats.get(seat).booleanValue();
            if (isAvailable) {
                cinema.seats.put(seat, false);
                Purchase purchase = new Purchase(seat);
                cinema.addNewPurchase(purchase);
                return new ResponseEntity<>(purchase, HttpStatus.OK);
            } else {
                return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return")
    public ResponseEntity returnTicket(@RequestBody Token token) {
        UUID searchedUUID = token.getToken();

        Optional<Purchase> opt = cinema.findByToken(searchedUUID);
        if (opt.isPresent()) {
            Purchase purchase = opt.get();
            //remove purchase from purchase list
            cinema.getPurchases().remove(purchase);
            //make seat available in hash map, so new request of get /seats OR post /purchase could be properly serviced
            cinema.getSeats().put(purchase.getTicket(), true);

            return new ResponseEntity(Map.of("returned_ticket", purchase.ticket), HttpStatus.OK);
        } else {
            return new ResponseEntity(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/stats")
    public ResponseEntity statistics(@RequestParam Optional<String> password) {
        if (password.isEmpty() || !password.get().equals(cinema.password)) {
            return new ResponseEntity(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity(cinema.generateStatistics(), HttpStatus.OK);
        }
    }
}
