package cinema;

public class Cinema {
    public int total_rows;
    public int total_columns;

    public Seat[] available_seats;


    public Cinema() {}

    public Cinema(int rows, int columns) {
        this.total_rows = rows;
        this.total_columns = columns;
        this.available_seats = new Seat[rows * columns];


        int counter = 0;
        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                available_seats[counter] = new Seat(i, j);
                counter++;
            }
        }
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

    public Seat[] getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(Seat[] available_seats) {
        this.available_seats = available_seats;
    }
}
