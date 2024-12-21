package fr.univ_amu.m1info.board_game_library;
import fr.univ_amu.m1info.board_game_library.model.GameStateManager;
import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameStateManagerTest {

    private GameStateManager gameStateManager;

    @Mock
    private AbstractPlayer mockPlayer1;

    @Mock
    private AbstractPlayer mockPlayer2;

    @BeforeEach
    void setUp() {
        // Initialiser les mocks
        MockitoAnnotations.openMocks(this);

        // Créer une instance de GameStateManager avec les mocks
        gameStateManager = new GameStateManager(mockPlayer1, mockPlayer2);
    }

    @Test
    void testGetCurrentPlayer_Player1Turn() {
        // Par défaut, c'est le tour du joueur 1
        assertEquals(mockPlayer1, gameStateManager.getCurrentPlayer());
    }

    @Test
    void testGetCurrentPlayer_Player2Turn() {
        // Configurer pour que ce soit le tour du joueur 2
        gameStateManager.setPlayer2Turn(true);
        assertEquals(mockPlayer2, gameStateManager.getCurrentPlayer());
    }

    @Test
    void testGetOpponentPlayer_Player1Turn() {
        // Par défaut, c'est le tour du joueur 1
        assertEquals(mockPlayer2, gameStateManager.getOpponentPlayer());
    }

    @Test
    void testGetOpponentPlayer_Player2Turn() {
        // Configurer pour que ce soit le tour du joueur 2
        gameStateManager.setPlayer2Turn(true);
        assertEquals(mockPlayer1, gameStateManager.getOpponentPlayer());
    }

    @Test
    void testToggleTurn() {
        // Par défaut, c'est le tour du joueur 1
        assertFalse(gameStateManager.isPlayer2Turn());
        assertEquals(0, gameStateManager.getTurnCounter());

        // Basculer vers le joueur 2
        gameStateManager.toggleTurn();
        assertTrue(gameStateManager.isPlayer2Turn());
        assertEquals(0, gameStateManager.getTurnCounter()); // 1 / 2 = 0 (division entière)

        // Basculer de nouveau vers le joueur 1
        gameStateManager.toggleTurn();
        assertFalse(gameStateManager.isPlayer2Turn());
        assertEquals(1, gameStateManager.getTurnCounter());
    }

    @Test
    void testIsPlayer2Turn() {
        // Par défaut, c'est le tour du joueur 1
        assertFalse(gameStateManager.isPlayer2Turn());

        // Configurer pour que ce soit le tour du joueur 2
        gameStateManager.setPlayer2Turn(true);
        assertTrue(gameStateManager.isPlayer2Turn());
    }

    @Test
    void testSetPlayer2Turn() {
        // Configurer pour que ce soit le tour du joueur 2
        gameStateManager.setPlayer2Turn(true);
        assertTrue(gameStateManager.isPlayer2Turn());

        // Configurer pour revenir au joueur 1
        gameStateManager.setPlayer2Turn(false);
        assertFalse(gameStateManager.isPlayer2Turn());
    }

    @Test
    void testGetTurnCounter() {
        // Par défaut, le compteur est à 0
        assertEquals(0, gameStateManager.getTurnCounter());

        // Simuler quelques tours
        gameStateManager.toggleTurn(); // Tour 1
        gameStateManager.toggleTurn(); // Tour 2
        gameStateManager.toggleTurn(); // Tour 3
        assertEquals(1, gameStateManager.getTurnCounter()); // 3 / 2 = 1 (division entière)
    }
}

