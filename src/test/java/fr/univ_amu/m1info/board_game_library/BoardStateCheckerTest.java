package fr.univ_amu.m1info.board_game_library;

import fr.univ_amu.m1info.board_game_library.model.BoardInitializer;
import fr.univ_amu.m1info.board_game_library.model.BoardStateChecker;
import fr.univ_amu.m1info.board_game_library.model.Cell;
import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardStateCheckerTest {

    private BoardStateChecker boardStateChecker;
    private Cell[][] cellStates;

    @BeforeEach
    void setUp() {
        cellStates = new Cell[BoardInitializer.BOARD_SIZE][BoardInitializer.BOARD_SIZE];
        for (int row = 0; row < BoardInitializer.BOARD_SIZE; row++) {
            for (int col = 0; col < BoardInitializer.BOARD_SIZE; col++) {
                cellStates[row][col] = new Cell(Shape.STAR, Color.DARKGREEN, Color.DARKGREEN);
            }
        }
        boardStateChecker = new BoardStateChecker(cellStates);
    }

    @Test
    void testIsGameOverWithEmptyCells() {
        cellStates[0][0] = new Cell(Shape.STAR, Color.DARKGREEN, Color.DARKGREEN);
        boolean result = boardStateChecker.isGameOver();
        assertFalse(result);
    }

    @Test
    void testIsGameOverWithFullBoard() {
        for (int row = 0; row < BoardInitializer.BOARD_SIZE; row++) {
            for (int col = 0; col < BoardInitializer.BOARD_SIZE; col++) {
                cellStates[row][col] = new Cell(Shape.CIRCLE, Color.BLACK, Color.DARKGREEN);
            }
        }
        boolean result = boardStateChecker.isGameOver();
        assertTrue(result);
    }
}
