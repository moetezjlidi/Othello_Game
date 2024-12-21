package fr.univ_amu.m1info.board_game_library.model;

import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;

public class GameStateManager {
    private AbstractPlayer player1;
    private AbstractPlayer player2;
    private boolean player2Turn = false;
    private int turnCounter = 0;

    public GameStateManager(AbstractPlayer player1, AbstractPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }


    public AbstractPlayer getCurrentPlayer() {
        return player2Turn ? player2 : player1;
    }


    public AbstractPlayer getOpponentPlayer() {
        return player2Turn ? player1 : player2;
    }

    public void toggleTurn() {
        player2Turn = !player2Turn;
        turnCounter++;
    }

    public void previousTurn(){
        player2Turn = !player2Turn;
        turnCounter--;
    }


    public int getTurnCounter() {
        return turnCounter / 2;
    }

    public boolean isPlayer2Turn() {
        return player2Turn;
    }


    public void setPlayer2Turn(boolean player2Turn) {
        this.player2Turn = player2Turn;
    }
}
