package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Cinema {
    public int total_rows;
    public int total_columns;

    /**
     * Seat - seat objext
     * Boolean - seat isAvailable
     */
    @JsonIgnore
    public ConcurrentMap<Seat, Boolean> seats;

    public List<Seat> available_seats;

    public Cinema() {
    }

    public Cinema(int rows, int columns) {
        this.total_rows = rows;
        this.total_columns = columns;
        this.seats = new ConcurrentHashMap<>();

        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                seats.put(new Seat(i, j), true);
            }
        }
    }

    public void updateAvailableSeats() {
        List<Seat> list = new ArrayList<>();
        for (Map.Entry<Seat, Boolean> entry : seats.entrySet()) {
            if (entry.getValue().booleanValue() == true) {
                list.add(entry.getKey());
            }
        }
        Collections.sort(list);
        available_seats = list;
    }

    public Optional<Seat> getSeat(int row, int col) {
        for (Seat s : seats.keySet()) {
            if (s.getRow() == row && s.getColumn() == col) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }
    
    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }

    public ConcurrentMap<Seat, Boolean> getSeats() {
        return seats;
    }

    public void setSeats(ConcurrentMap<Seat, Boolean> seats) {
        this.seats = seats;
    }
}
