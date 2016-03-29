package com.example.valentinsauer.connectfour;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by valentin.sauer on 18.03.2016.
 */
public class Game extends AppCompatActivity {

    public static final int RED = 1;
    public static final int YELLOW = -1;
    public static final int EMPTY = 0;

    public static final int COLS = 7;
    public static final int ROWS = 6;

    private Cell[][] gameState = new Cell[COLS][ROWS];
    private int gameCounter = 0;


    public Game() {
        for( int i = 0; i < gameState.length; i++){
            for (int ii = 0; ii < gameState[i].length; ii++){
                gameState[i][ii] = new Cell(ii, i);
            }
        }
    }

    public int getCurrentPlayer() {
        return this.gameCounter % 2 == 0 ? RED : YELLOW;
    }

    public Cell move(int column) {
        for (int i = 0; i < gameState[column].length; i++) {
            if (gameState[column][i].isEmpty()) {
                gameState[column][i].setStatus(getCurrentPlayer());
                gameCounter++;
                return gameState[column][i];
            }
        }
        return new Cell(-1, -1);
    }

    public Cell computerMove() {
        while (true) {
            int randomColumn = (int) (Math.random() * gameState.length);
            Cell cell = this.move(randomColumn);
            if (cell.isValid()) {
                return cell;
            }
        }
    }

    /**
     * checks the cells surrounding the currently placed one for lines of four
     * @param cell
     * @return
     */
    public int checkFinish(Cell cell) {

        int diagUp = 1;
        int diagDown = 1;
        int horizontal = 1;
        int vertical = 1;

        if (gameCounter == 42){
            return 42;
        }

        for (int i = 1; i <= 3; i++) {

            diagUp += checkCell(cell, i, i);
            horizontal += checkCell(cell, 0, i);
            diagDown += checkCell(cell, -i, i);
            vertical += checkCell(cell, -i, 0);
            diagUp += checkCell(cell, -i, -i);
            horizontal += checkCell(cell, 0, -i);
            diagDown += checkCell(cell, i, -i);

            if (diagUp == 4 || diagDown == 4 || horizontal == 4 || vertical == 4) {
                return getCurrentPlayer();
            }
        }
        return 0;
    }

    /**
     * Checks if a cell has the same status (playercolor) as the current one
     * @param cell
     * @param row
     * @param col
     * @return
     */
    private int checkCell(Cell cell, int row, int col) {
        row = cell.getRow() + row;
        col = cell.getCol() + col;
        if (col < 0 || col > Game.COLS - 1 || row < 0 || row > Game.ROWS - 1) {
            return 0;
        } else {
            if (!gameState[col][row].isEmpty() && cell.getStatus() == gameState[col][row].getStatus()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
