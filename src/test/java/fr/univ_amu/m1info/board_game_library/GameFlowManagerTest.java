package fr.univ_amu.m1info.board_game_library;

import fr.univ_amu.m1info.board_game_library.model.BoardManager;
import fr.univ_amu.m1info.board_game_library.model.Cell;


import fr.univ_amu.m1info.board_game_library.model.GameFlowManager;
import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;
import fr.univ_amu.m1info.board_game_library.model.mode.GameModeStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameFlowManagerTest {

    private GameFlowManager gameFlowManager;

    @Mock
    private BoardManager mockBoardManager;

    @Mock
    private GameModeStrategy mockGameModeStrategy;

    @Mock
    private AbstractPlayer mockPlayer1;

    @Mock
    private AbstractPlayer mockPlayer2;

    @BeforeEach
    void setUp() {
        // Initialisation des mocks avec Mockito
        MockitoAnnotations.openMocks(this);

        // Instanciation de GameFlowManager avec le mock de BoardManager
        gameFlowManager = new GameFlowManager(mockBoardManager);
    }

    @Test
    void testInitializeGameConfiguration() {
        // Appel de la méthode à tester
        gameFlowManager.initializeGameConfiguration(mockPlayer1, mockPlayer2, mockGameModeStrategy);

        // Vérifications
        verify(mockBoardManager).initializeBoard();
        verify(mockBoardManager).showAvailableMoves(mockPlayer1, mockPlayer2);
    }

    @Test
    void testStartGame() {
        // Définir le comportement attendu
        Cell[][] mockCells = new Cell[8][8];
        when(mockBoardManager.getCellStates()).thenReturn(mockCells);

        // Appel de la méthode à tester
        Cell[][] result = gameFlowManager.startGame(mockPlayer1, mockPlayer2, mockGameModeStrategy);

        // Vérifications
        verify(mockBoardManager).initializeBoard();
        verify(mockBoardManager).showAvailableMoves(mockPlayer1, mockPlayer2);
        assertSame(mockCells, result); // Vérifie que les cellules retournées sont correctes
    }

    @Test
    void testEndGame() {
        // Tester avec `isGameOver` true
        when(mockBoardManager.isGameOver()).thenReturn(true);
        assertTrue(gameFlowManager.endGame());

        // Tester avec `skipturn == 2`
        when(mockBoardManager.isGameOver()).thenReturn(false);
        gameFlowManager = new GameFlowManager(mockBoardManager) {
            @Override
            public boolean endGame() {
                skipturn = 2;
                return super.endGame();
            }
        };
        assertTrue(gameFlowManager.endGame());

        // Tester avec `isGameOver` false et `skipturn != 2`
        when(mockBoardManager.isGameOver()).thenReturn(false);
        gameFlowManager = new GameFlowManager(mockBoardManager);
        assertFalse(gameFlowManager.endGame());
    }

    @Test
    void testPlay() {
        // Configurer la stratégie et les joueurs avant de jouer
        gameFlowManager.initializeGameConfiguration(mockPlayer1, mockPlayer2, mockGameModeStrategy);

        // Appeler la méthode à tester
        gameFlowManager.play(3, 4);

        // Vérifier que playTurn a été appelé sur gameModeStrategy avec les bons arguments
        verify(mockGameModeStrategy).playTurn(3, 4);
    }
}

