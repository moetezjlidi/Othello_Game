package fr.univ_amu.m1info.board_game_library.model;

public class BoardStateChecker {

    private Cell[][] cellStates;

    public BoardStateChecker(Cell[][] cellStates) {
        this.cellStates = cellStates;
    }


    public boolean isGameOver() {
        boolean isBoardFull = true;
        for (int row = 0; row < BoardInitializer.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardInitializer.BOARD_SIZE; column++) {
                if (cellStates[row][column].isEmpty()) {
                    isBoardFull = false;
                    break;
                }
            }
            if (!isBoardFull) break;
        }
        return isBoardFull;
    }
}
