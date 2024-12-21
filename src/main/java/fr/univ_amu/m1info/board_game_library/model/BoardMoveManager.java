package fr.univ_amu.m1info.board_game_library.model;

import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;
import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;
import fr.univ_amu.m1info.board_game_library.controller.OthelloController;


import java.util.Stack;

public class BoardMoveManager {

    private Cell[][] cellStates;
    private Rules rules;


    public BoardMoveManager(Cell[][] cellStates, Rules rules) {
        this.cellStates = cellStates;
        this.rules = rules;
    }
    public boolean placePawn(int row, int column, AbstractPlayer currentPlayer, AbstractPlayer opponentPlayer) {
        if (cellStates[row][column].isEmpty() && rules.isMoveValid(row, column, currentPlayer, opponentPlayer, cellStates)) {
            setCellStates(row, column, currentPlayer.getShape(), currentPlayer.getColor());
            rules.checkAndFlipPieces(row, column, currentPlayer, opponentPlayer, cellStates);
            return true;
        } else {
            System.out.println("Invalid move at (" + row + ", " + column + "). Choose another cell.");
            return false;
        }
    }

    public void showAvailableMoves(AbstractPlayer currentPlayer, AbstractPlayer opponentPlayer) {
        for (int row = 0; row < BoardInitializer.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardInitializer.BOARD_SIZE; column++) {
                if (cellStates[row][column].isEmpty() && rules.isMoveValid(row, column, currentPlayer, opponentPlayer, cellStates)) {
                    this.cellStates[row][column].stack(new CellContent(Shape.STAR, Color.BLUE, Color.BLUE));
                }
            }
        }
    }


    public boolean showAvailableMovesForPlayer() {
        for (int row = 0; row < BoardInitializer.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardInitializer.BOARD_SIZE; column++) {
                if (cellStates[row][column].baseColor() == Color.BLUE) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[] getAvailableMove() {
        for (int row = 0; row < BoardInitializer.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardInitializer.BOARD_SIZE; column++) {
                if (cellStates[row][column].baseColor() == Color.BLUE) {
                    return new int[]{row, column};
                }
            }
        }
        return new int[]{-1,-1};
    }


    public void resetAvailableMovesHighlight() {
        for (int row = 0; row < BoardInitializer.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardInitializer.BOARD_SIZE; column++) {
                if (cellStates[row][column].baseColor() == Color.BLUE) {
                    cellStates[row][column].stack(new CellContent(Shape.STAR, Color.DARKGREEN, Color.DARKGREEN));
                }
            }
        }
    }
    public void undoMove(){
        for (int row = 0; row < BoardInitializer.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardInitializer.BOARD_SIZE; column++) {
                cellStates[row][column].unstack();
            }
        }
    }

    private void setCellStates(int row, int col, Shape shape, Color color) {
        this.cellStates[row][col].stack(new CellContent(shape, color, Color.DARKGREEN));
    }

}

