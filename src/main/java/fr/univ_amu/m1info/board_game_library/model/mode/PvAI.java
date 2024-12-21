package fr.univ_amu.m1info.board_game_library.model.mode;

import fr.univ_amu.m1info.board_game_library.model.BoardManager;
import fr.univ_amu.m1info.board_game_library.model.GameStateManager;

public class PvAI extends GameModeStrategy {

    public PvAI(BoardManager boardManager, GameStateManager gameStateManager) {
        super(boardManager, gameStateManager);
    }

    private boolean AITurn = false;

    @Override
    public void playTurn(int row, int column) {

        if (!AITurn) playerPlay(row, column);
        if (AITurn) AIPlay();

        getBoardManager().resetAvailableMovesHighlight();
        getBoardManager().showAvailableMoves(getGameStateManager().getCurrentPlayer(), getGameStateManager().getOpponentPlayer());

        while (!this.getBoardManager().showAvailableMovesForPlayer()) {
            getGameStateManager().setPlayer2Turn(!getGameStateManager().isPlayer2Turn());
            setSkipturn(getSkipturn()+1);
            getBoardManager().showAvailableMoves(getGameStateManager().getCurrentPlayer(), getGameStateManager().getOpponentPlayer());
            if (getSkipturn() == 2) {
                System.out.println("no available moves");
                break;
            }
        }
    }

    private void playerPlay(int row, int column) {
        if (getBoardManager().placePawn(row, column, getGameStateManager().getCurrentPlayer(), getGameStateManager().getOpponentPlayer())) {
            getGameStateManager().toggleTurn();
            AITurn = true;
            setSkipturn(0);
        }

    }

    private void AIPlay() {
        getBoardManager().resetAvailableMovesHighlight();
        getBoardManager().showAvailableMoves(getGameStateManager().getCurrentPlayer(), getGameStateManager().getOpponentPlayer());
        System.out.println("AITurn");
        int[] randomMoves = getBoardManager().getAvailableMove();
        if (randomMoves[0] != -1) {
            System.out.println(randomMoves[0] + " " + randomMoves[1]
            );
            if (getBoardManager().placePawn(randomMoves[0], randomMoves[1],
                    getGameStateManager().getCurrentPlayer(), getGameStateManager().getOpponentPlayer())) {
                System.out.println("AI play");
                getGameStateManager().toggleTurn();
                AITurn = false;
                setSkipturn(0);
            }
        }
    }
}
