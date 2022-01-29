package cinema;

public class Seat {
    int row;
    int column;

    public Seat() {}

    public Seat(int rowId, int columnId) {
        this.row = rowId;
        this.column = columnId;
    }


    public int getRow() {
        return row + 1;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column + 1;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
