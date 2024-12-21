package fr.univ_amu.m1info.board_game_library.model;

import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;

public class BoardManager{

    private final BoardInitializer boardInitializer;
    private final BoardMoveManager moveManager;
    private final BoardStateChecker gameStateChecker;

    public BoardManager(Rules rules, AbstractPlayer player1, AbstractPlayer player2) {
        this.boardInitializer = new BoardInitializer(player1, player2);
        this.moveManager = new BoardMoveManager(boardInitializer.getCellStates(), rules);
        this.gameStateChecker = new BoardStateChecker(boardInitializer.getCellStates());
    }


    public void initializeBoard() {
        boardInitializer.initializeBoard();
    }


    public boolean placePawn(int row, int column, AbstractPlayer currentPlayer, AbstractPlayer opponentPlayer) {
        return moveManager.placePawn(row, column, currentPlayer, opponentPlayer);
    }


    public void showAvailableMoves(AbstractPlayer currentPlayer, AbstractPlayer opponentPlayer) {
        moveManager.showAvailableMoves(currentPlayer, opponentPlayer);
    }


    public boolean showAvailableMovesForPlayer() {
        return moveManager.showAvailableMovesForPlayer();
    }

    public int[] getAvailableMove() {
       return moveManager.getAvailableMove();
    }


    public void resetAvailableMovesHighlight() {
        moveManager.resetAvailableMovesHighlight();
    }


    public boolean isGameOver() {
        return gameStateChecker.isGameOver();
    }

    public void undoMove(){
        moveManager.undoMove();
    }


    public Cell[][] getCellStates() {
        return boardInitializer.getCellStates();
    }

    public Cell getCell(int row, int column) {
        return boardInitializer.getCellStates()[row][column];
    }
}
