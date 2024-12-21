package fr.univ_amu.m1info.board_game_library.model;

import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;
import fr.univ_amu.m1info.board_game_library.model.mode.GameModeStrategy;

public class GameFlowManager {
    private BoardManager boardManager;
    private GameModeStrategy gameModeStrategy;
    public int skipturn = 0;

    public GameFlowManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }


    public void initializeGameConfiguration(AbstractPlayer player1, AbstractPlayer player2, GameModeStrategy gameModeStrategy) {
        boardManager.initializeBoard();
        boardManager.showAvailableMoves(player1, player2);
        this.gameModeStrategy = gameModeStrategy;
    }


    public Cell[][] startGame(AbstractPlayer player1, AbstractPlayer player2, GameModeStrategy gameModeStrategy) {
        initializeGameConfiguration(player1, player2, gameModeStrategy);
        return boardManager.getCellStates();
    }


    public boolean endGame() {
        return (boardManager.isGameOver() || skipturn == 2);
    }

    public Cell [][] rollback(){
        boardManager.undoMove();
        return boardManager.getCellStates();
    }


    public void play(int row, int column) {
        if (gameModeStrategy == null) {
            throw new IllegalStateException("GameModeStrategy is not initialized. Call initializeGameConfiguration first.");
        }
        gameModeStrategy.playTurn(row, column);
    }
}
