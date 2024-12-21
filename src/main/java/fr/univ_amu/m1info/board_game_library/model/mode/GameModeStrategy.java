package fr.univ_amu.m1info.board_game_library.model.mode;

import fr.univ_amu.m1info.board_game_library.model.BoardManager;
import fr.univ_amu.m1info.board_game_library.model.GameStateManager;

public abstract class GameModeStrategy {

    private BoardManager boardManager;
    private GameStateManager get;
    private int skipturn = 0;

    GameModeStrategy(BoardManager boardManager, GameStateManager gameStateManager) {
        this.boardManager = boardManager;
        this.get = gameStateManager;
    }
    public abstract void playTurn(int row, int column);


    public BoardManager getBoardManager() {
        return boardManager;
    }

    public GameStateManager getGameStateManager() {
        return get;
    }

    public int getSkipturn() {
        return skipturn;
    }

    public void setSkipturn(int skipturn) {
        this.skipturn = skipturn;
    }
}
