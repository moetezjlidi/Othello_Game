package fr.univ_amu.m1info.board_game_library;

import fr.univ_amu.m1info.board_game_library.model.BoardManager;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardManagerTest {
    /*
    @Test
    void testInitializeCells() {
        BoardManager boardManager = new BoardManager();
        boardManager.initializeCells();

        // Ensure board is empty except for the starting positions
        assertEquals(CellState.WHITE, boardManager.getCellState(3, 3));
        assertEquals(CellState.BLACK, boardManager.getCellState(3, 4));
        assertEquals(CellState.BLACK, boardManager.getCellState(4, 3));
        assertEquals(CellState.WHITE, boardManager.getCellState(4, 4));
        assertEquals(CellState.EMPTY, boardManager.getCellState(0, 0));
    }

    @Test
    void testPlacePawn() {
        BoardManager boardManager = new BoardManager();
        boardManager.initializeCells();

        // Place a black pawn and verify flip
        boardManager.placePawn(2, 3);
        assertEquals(CellState.BLACK, boardManager.getCellState(2, 3));
        assertEquals(CellState.BLACK, boardManager.getCellState(3, 3)); // White piece flipped
    }

    @Test
    void testShowAvailableMoves() {
        BoardManager boardManager = new BoardManager();
        boardManager.initializeCells();

        List<int[]> moves = boardManager.showAvailableMoves();

        // Utility method for checking if a move exists in the list
        assertTrue(containsMove(moves, new int[]{2, 3}));
        assertTrue(containsMove(moves, new int[]{3, 2}));
        assertTrue(containsMove(moves, new int[]{4, 5}));
        assertTrue(containsMove(moves, new int[]{5, 4}));
    }

    // Helper method for comparing array contents
    private boolean containsMove(List<int[]> moves, int[] target) {
        for (int[] move : moves) {
            if (Arrays.equals(move, target)) {
                return true;
            }
        }
        return false;
    }
    */

}
