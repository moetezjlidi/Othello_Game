package fr.univ_amu.m1info.board_game_library.model.mode;

import fr.univ_amu.m1info.board_game_library.model.BoardManager;
import fr.univ_amu.m1info.board_game_library.model.GameStateManager;

public class PVP extends GameModeStrategy{


    public PVP(BoardManager boardManager, GameStateManager gameStateManager) {
        super(boardManager, gameStateManager);
    }

    @Override
    public void playTurn(int row, int column) {
        if(getBoardManager().placePawn(row,column, getGameStateManager().getCurrentPlayer(), getGameStateManager().getOpponentPlayer())){
            getGameStateManager().toggleTurn();
            setSkipturn(0);
        }
        getBoardManager().showAvailableMoves(getGameStateManager().getCurrentPlayer(), getGameStateManager().getOpponentPlayer());

        while (!this.getBoardManager().showAvailableMovesForPlayer()){
            getGameStateManager().setPlayer2Turn(!getGameStateManager().isPlayer2Turn());
            setSkipturn(getSkipturn()+1);
            getBoardManager().showAvailableMoves(getGameStateManager().getCurrentPlayer(), getGameStateManager().getOpponentPlayer());
            if(getSkipturn() == 2){
                System.out.println("no available moves");
                break;
            }

        }
    }
}
