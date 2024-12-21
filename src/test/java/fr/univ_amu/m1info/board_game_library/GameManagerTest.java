package fr.univ_amu.m1info.board_game_library;

import fr.univ_amu.m1info.board_game_library.model.*;
import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;
import fr.univ_amu.m1info.board_game_library.model.mode.GameModeStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameManagerTest {

    private GameManager gameManager;

    @Mock
    private GameFlowManager mockGameFlowManager;

    @Mock
    private GameStateManager mockGameStateManager;

    @Mock
    private AbstractPlayer mockPlayer1;

    @Mock
    private AbstractPlayer mockPlayer2;

    @Mock
    private GameModeStrategy mockGameModeStrategy;

    @BeforeEach
    void setUp() {
        // Initialiser les mocks
        MockitoAnnotations.openMocks(this);

        // Créer une instance de GameManager avec les mocks
        gameManager = new GameManager(mockGameFlowManager, mockGameStateManager);
    }

    @Test
    void testStartGame() {
        // Simuler le comportement de startGame dans GameFlowManager
        Cell[][] mockCells = new Cell[8][8];
        when(mockGameFlowManager.startGame(mockPlayer1, mockPlayer2, mockGameModeStrategy)).thenReturn(mockCells);

        // Appeler la méthode à tester
        Cell[][] result = gameManager.startGame(mockPlayer1, mockPlayer2, mockGameModeStrategy);

        // Vérifier que startGame est appelé correctement
        verify(mockGameFlowManager).startGame(mockPlayer1, mockPlayer2, mockGameModeStrategy);
        assertSame(mockCells, result); // Vérifier que le tableau retourné est correct
    }

    @Test
    void testPlay() {
        // Appeler la méthode à tester
        gameManager.play(3, 4);

        // Vérifier que play est appelé dans GameFlowManager avec les bons arguments
        verify(mockGameFlowManager).play(3, 4);
    }

    @Test
    void testEndGame() {
        // Simuler le comportement de endGame dans GameFlowManager
        when(mockGameFlowManager.endGame()).thenReturn(true);

        // Appeler la méthode à tester
        boolean result = gameManager.endGame();

        // Vérifier que endGame est appelé et retourne le bon résultat
        verify(mockGameFlowManager).endGame();
        assertTrue(result);
    }

    @Test
    void testIsPlayer2Turn() {
        // Simuler le comportement de isPlayer2Turn dans GameStateManager
        when(mockGameStateManager.isPlayer2Turn()).thenReturn(true);

        // Appeler la méthode à tester
        boolean result = gameManager.isPlayer2Turn();

        // Vérifier que isPlayer2Turn est appelé et retourne le bon résultat
        verify(mockGameStateManager).isPlayer2Turn();
        assertTrue(result);
    }

    @Test
    void testSetPlayer2Turn() {
        // Appeler la méthode à tester
        gameManager.setPlayer2Turn(true);

        // Vérifier que setPlayer2Turn est appelé avec le bon argument
        verify(mockGameStateManager).setPlayer2Turn(true);
    }

    @Test
    void testGetTurnCounter() {
        // Simuler le comportement de getTurnCounter dans GameStateManager
        when(mockGameStateManager.getTurnCounter()).thenReturn(5);

        // Appeler la méthode à tester
        int result = gameManager.getTurnCounter();

        // Vérifier que getTurnCounter est appelé et retourne le bon résultat
        verify(mockGameStateManager).getTurnCounter();
        assertEquals(5, result);
    }
}
