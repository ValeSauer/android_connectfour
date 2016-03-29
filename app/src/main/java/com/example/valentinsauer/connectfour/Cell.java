package com.example.valentinsauer.connectfour;

/**
 * Created by valentin.sauer on 28.03.2016.
 */
public class Cell {

    private int row;
    private int col;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Cell(int row, int col) {
        this.setRow(row);
        this.setCol(col);
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isValid() {
        if (this.col == -1 || this.row == -1) {
            return false;
        }
        return true;
    }

    public boolean isEmpty() {
        if (this.status == Game.EMPTY) {
            return true;
        }
        return false;
    }

}
