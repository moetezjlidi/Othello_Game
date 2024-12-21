package fr.univ_amu.m1info.board_game_library.model;

import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;
import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;

public class BoardInitializer{

    public static final int BOARD_SIZE = 8;
    private Cell[][] cellStates = new Cell[BOARD_SIZE][BOARD_SIZE];
    private AbstractPlayer player1;
    private AbstractPlayer player2;

    public BoardInitializer(AbstractPlayer player1, AbstractPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }


    public void initializeBoard() {
        initializeCells();
        initializeStartingPositions();
    }

    public void initializeCells() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                this.cellStates[row][column] = new Cell(new CellContent(Shape.STAR,null, Color.DARKGREEN));
            }
        }
    }

    public void initializeStartingPositions() {
        setCellStates(3, 3, player2.getShape(), player2.getColor());
        setCellStates(3, 4, player1.getShape(), player1.getColor());
        setCellStates(4, 3, player1.getShape(), player1.getColor());
        setCellStates(4, 4, player2.getShape(), player2.getColor());
    }

    public void setCellStates(int row, int col, Shape shape, Color color) {
        this.cellStates[row][col].stack(new CellContent(shape,color,Color.DARKGREEN));
    }

    public Cell[][] getCellStates() {
        return cellStates;
    }
}
