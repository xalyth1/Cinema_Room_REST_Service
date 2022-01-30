package cinema;

public class Seat implements Comparable<Seat> {
    int row;
    int column;

    int price;

    public Seat() {
    }

    public Seat(int rowId, int columnId) {
        this.row = rowId;
        this.column = columnId;
        this.price = row <= 4 ? 10 : 8;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int compareTo(Seat s) {
        if (this.row == s.row)
            return this.column - s.column;
        else return this.row - s.row;
    }
}
