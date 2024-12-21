package fr.univ_amu.m1info.board_game_library;

import fr.univ_amu.m1info.board_game_library.model.BoardInitializer;
import fr.univ_amu.m1info.board_game_library.model.BoardMoveManager;
import fr.univ_amu.m1info.board_game_library.model.Cell;
import fr.univ_amu.m1info.board_game_library.model.Rules;
import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;
import fr.univ_amu.m1info.board_game_library.model.gamePlayer.Human;
import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardMoveManagerTest {

    private BoardMoveManager boardMoveManager;
    private Rules rules;
    private Cell[][] cellStates;
    private AbstractPlayer player1;
    private AbstractPlayer player2;

    @BeforeEach
    void setUp() {
        // Create mock rules
        rules = mock(Rules.class);
        cellStates = new Cell[BoardInitializer.BOARD_SIZE][BoardInitializer.BOARD_SIZE];
        for (int row = 0; row < BoardInitializer.BOARD_SIZE; row++) {
            for (int col = 0; col < BoardInitializer.BOARD_SIZE; col++) {
                cellStates[row][col] = new Cell(Shape.STAR, Color.DARKGREEN, Color.DARKGREEN);
            }
        }
        player1 = new Human(Shape.CIRCLE, Color.BLACK, "Player1");
        player2 = new Human(Shape.CIRCLE, Color.WHITE, "Player2");
        boardMoveManager = new BoardMoveManager(cellStates, rules);
    }

    @Test
    void testPlacePawnValidMove() {
        when(rules.isMoveValid(3, 3, player1, player2, cellStates)).thenReturn(true);
        boolean moveResult = boardMoveManager.placePawn(3, 3, player1, player2);
        assertTrue(moveResult);
        assertEquals(player1.getShape(), cellStates[3][3].playerShape());
        assertEquals(player1.getColor(), cellStates[3][3].playerColor());
        verify(rules).checkAndFlipPieces(3, 3, player1, player2, cellStates);
    }

    @Test
    void testPlacePawnInvalidMove() {
        when(rules.isMoveValid(3, 3, player1, player2, cellStates)).thenReturn(false);
        boolean moveResult = boardMoveManager.placePawn(3, 3, player1, player2);
        assertFalse(moveResult);
        assertEquals(Shape.STAR, cellStates[3][3].playerShape());
        assertEquals(Color.DARKGREEN, cellStates[3][3].playerColor());
        verify(rules, never()).checkAndFlipPieces(anyInt(), anyInt(), any(), any(), any());
    }

    @Test
    void testShowAvailableMoves() {
        when(rules.isMoveValid(3, 2, player1, player2, cellStates)).thenReturn(true);
        when(rules.isMoveValid(2, 3, player1, player2, cellStates)).thenReturn(true);
        boardMoveManager.showAvailableMoves(player1, player2);
        assertEquals(Color.BLUE, cellStates[3][2].baseColor());
        assertEquals(Color.BLUE, cellStates[2][3].baseColor());
        assertEquals(Color.DARKGREEN, cellStates[0][0].baseColor());
    }

    @Test
    void testShowAvailableMovesForPlayer() {
        cellStates[3][2] = new Cell(Shape.STAR, Color.BLUE, Color.BLUE);
        boolean result = boardMoveManager.showAvailableMovesForPlayer();
        assertTrue(result);
    }

    @Test
    void testResetAvailableMovesHighlight() {
        cellStates[3][2] = new Cell(Shape.STAR, Color.BLUE, Color.BLUE);
        boardMoveManager.resetAvailableMovesHighlight();
        assertEquals(Color.DARKGREEN, cellStates[3][2].baseColor());
    }
}
