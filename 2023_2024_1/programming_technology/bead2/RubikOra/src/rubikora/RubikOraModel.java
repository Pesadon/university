/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rubikora;

import java.util.Random;

/**
 *
 * @author Perczel-Szabó Dániel
 */
public class RubikOraModel {
    private int[][] board;
    private int steps;

    public RubikOraModel(int size) {
        board = new int[size][size];
        steps = 0;
        initializeBoard();
    }

    public void initializeBoard() {
        steps=0;
        Random random = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = random.nextInt(12) + 1;
                //board[i][j] = 12;
            }
        }
    }

    public int getClockValue(int row, int col) {
        return board[row][col];
    }

    public void updateClock(int row, int col) {
        board[row][col] = (board[row][col] % 12) + 1;
        board[row][col+1] = (board[row][col+1] % 12) + 1;
        board[row+1][col] = (board[row+1][col] % 12) + 1;
        board[row+1][col+1] = (board[row+1][col+1] % 12) + 1;
        steps++;
    }

    public boolean isGameWon() {
        for (int[] row : board) {
            for (int value : row) {
                if (value != 12) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public int getSteps() {
        return steps;
    }
}

