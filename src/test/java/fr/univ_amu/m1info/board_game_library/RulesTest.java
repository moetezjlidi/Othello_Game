package fr.univ_amu.m1info.board_game_library;

import fr.univ_amu.m1info.board_game_library.model.Cell;
import fr.univ_amu.m1info.board_game_library.model.Rules;
import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;
import fr.univ_amu.m1info.board_game_library.model.gamePlayer.Human;
import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RulesTest {

    private Rules rules;
    private Cell[][] cellStates;
    private AbstractPlayer player1;
    private AbstractPlayer player2;

    @BeforeEach
    void setUp() {
        rules = new Rules();
        cellStates = new Cell[rules.BOARD_SIZE][rules.BOARD_SIZE];
        for (int row = 0; row < rules.BOARD_SIZE; row++) {
            for (int col = 0; col < rules.BOARD_SIZE; col++) {
                cellStates[row][col] = new Cell(Shape.STAR, Color.DARKGREEN, Color.DARKGREEN);
            }
        }
        player1 = new Human(Shape.CIRCLE, Color.BLACK, "Player1");
        player2 = new Human(Shape.CIRCLE, Color.WHITE, "Player2");
    }

    @Test
    void testIsMoveValid() {
        cellStates[3][3] = new Cell(player2.getShape(), player2.getColor(), Color.DARKGREEN);
        cellStates[3][4] = new Cell(player1.getShape(), player1.getColor(), Color.DARKGREEN);
        boolean result = rules.isMoveValid(3, 2, player1, player2, cellStates);
        assertTrue(result);
    }

    @Test
    void testIsMoveInvalid() {
        cellStates[3][3] = new Cell(player2.getShape(), player2.getColor(), Color.DARKGREEN);
        boolean result = rules.isMoveValid(3, 2, player1, player2, cellStates);
        assertFalse(result);
    }

    @Test
    void testCheckAndFlipPieces() {
        cellStates[3][3] = new Cell(player2.getShape(), player2.getColor(), Color.DARKGREEN);
        cellStates[3][4] = new Cell(player2.getShape(), player2.getColor(), Color.DARKGREEN);
        cellStates[3][5] = new Cell(player1.getShape(), player1.getColor(), Color.DARKGREEN);
        rules.checkAndFlipPieces(3, 2, player1, player2, cellStates);
        assertEquals(player1.getColor(), cellStates[3][3].playerColor());
        assertEquals(player1.getColor(), cellStates[3][4].playerColor());
    }

    @Test
    void testFindOpponentPositions() {
        cellStates[3][3] = new Cell(player2.getShape(), player2.getColor(), Color.DARKGREEN);
        cellStates[3][4] = new Cell(player2.getShape(), player2.getColor(), Color.DARKGREEN);
        cellStates[3][5] = new Cell(player1.getShape(), player1.getColor(), Color.DARKGREEN);
        var opponentPositions = rules.findOpponentPositions(3, 2, new int[]{0, 1}, player2, cellStates);
        assertEquals(2, opponentPositions.size());
        assertArrayEquals(new int[]{3, 3}, opponentPositions.get(0));
        assertArrayEquals(new int[]{3, 4}, opponentPositions.get(1));
    }
}
