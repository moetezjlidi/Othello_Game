package fr.univ_amu.m1info.board_game_library.model;

import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;
import fr.univ_amu.m1info.board_game_library.model.mode.GameModeStrategy;

public class GameManager {

    private GameFlowManager gameFlowManager;
    private GameStateManager gameStateManager;
    private GameModeStrategy gameModeStrategy;

    public GameManager(GameFlowManager gameFlowManager, GameStateManager gameStateManager) {
        this.gameFlowManager = gameFlowManager;
        this.gameStateManager = gameStateManager;
    }

    public Cell[][] startGame(AbstractPlayer player1, AbstractPlayer player2, GameModeStrategy gameModeStrategy) {
        this.gameModeStrategy = gameModeStrategy;
        return gameFlowManager.startGame(player1, player2, gameModeStrategy);
    }

    public void play(int row, int column) {
        gameFlowManager.play(row, column);
    }

    public Cell[][] undo(){
        gameStateManager.previousTurn();
        return gameFlowManager.rollback();
    }

    public boolean endGame() {
        return gameFlowManager.endGame();
    }

    public boolean isPlayer2Turn() {
        return gameStateManager.isPlayer2Turn();
    }

    public void setPlayer2Turn(boolean player2Turn) {
        gameStateManager.setPlayer2Turn(player2Turn);
    }

    public int getTurnCounter() {
        return gameStateManager.getTurnCounter();
    }

    // Provide access to the game mode strategy
    public GameModeStrategy getGameModeStrategy() {
        return gameModeStrategy;
    }

    //--------------------
    public void setGameModeStrategy(GameModeStrategy gameModeStrategy) {
        this.gameModeStrategy = gameModeStrategy;
    }
}
