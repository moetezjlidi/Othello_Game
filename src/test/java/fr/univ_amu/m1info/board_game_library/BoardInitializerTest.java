package fr.univ_amu.m1info.board_game_library;

import fr.univ_amu.m1info.board_game_library.model.BoardInitializer;
import fr.univ_amu.m1info.board_game_library.model.Cell;
import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;
import fr.univ_amu.m1info.board_game_library.model.gamePlayer.Human;
import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardInitializerTest {

    private BoardInitializer boardInitializer;
    private AbstractPlayer player1;
    private AbstractPlayer player2;

    @BeforeEach
    void setUp() {
        // Initialize players
        player1 = new Human(Shape.CIRCLE, Color.BLACK, "Player1");
        player2 = new Human(Shape.CIRCLE, Color.WHITE, "Player2");

        // Initialize BoardInitializer with players
        boardInitializer = new BoardInitializer(player1, player2);
    }

    @Test
    void testInitializeCells() {

        boardInitializer.initializeCells();
        Cell[][] cellStates = boardInitializer.getCellStates();

        for (int row = 0; row < BoardInitializer.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardInitializer.BOARD_SIZE; column++) {
                assertNotNull(cellStates[row][column], "Cell should not be null");
                assertEquals(Shape.STAR, cellStates[row][column].playerShape(), "Cell shape should be STAR");
                assertEquals(Color.DARKGREEN, cellStates[row][column].baseColor(), "Cell base color should be DARKGREEN");
            }
        }
    }

    @Test
    void testInitializeStartingPositions() {
        boardInitializer.initializeBoard();

        Cell[][] cellStates = boardInitializer.getCellStates();

        assertEquals(player2.getShape(), cellStates[3][3].playerShape(), "Top-left starting cell shape should match Player2");
        assertEquals(player2.getColor(), cellStates[3][3].playerColor(), "Top-left starting cell color should match Player2");

        assertEquals(player1.getShape(), cellStates[3][4].playerShape(), "Top-right starting cell shape should match Player1");
        assertEquals(player1.getColor(), cellStates[3][4].playerColor(), "Top-right starting cell color should match Player1");

        assertEquals(player1.getShape(), cellStates[4][3].playerShape(), "Bottom-left starting cell shape should match Player1");
        assertEquals(player1.getColor(), cellStates[4][3].playerColor(), "Bottom-left starting cell color should match Player1");

        assertEquals(player2.getShape(), cellStates[4][4].playerShape(), "Bottom-right starting cell shape should match Player2");
        assertEquals(player2.getColor(), cellStates[4][4].playerColor(), "Bottom-right starting cell color should match Player2");
    }
}
