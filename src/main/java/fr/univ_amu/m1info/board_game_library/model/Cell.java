package fr.univ_amu.m1info.board_game_library.model;

import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;

import java.util.Stack;

public class Cell {
    private CellContent cellContent;
    private Stack<CellContent> cellStack = new Stack<>();

    public Cell(CellContent cellContent) {
        stack(cellContent);
    }

    public Cell(Shape shape, Color playerColor, Color baseColor) {
        this(new CellContent(shape, playerColor, baseColor));
    }


    public boolean isEmpty() {
        return cellContent.playerShape() == Shape.STAR;
    }


    public void stack(CellContent cellContent) {
        this.cellContent = cellContent;
        this.cellStack.push(cellContent);
        System.out.println("stack" + cellStack);
    }

    public void unstack() {
        if(!isEmpty()){
            this.cellContent = cellStack.pop();
        }
        System.out.println("unstack " + cellStack);
    }

    public Shape playerShape() {
        return cellContent.playerShape();
    }

    public Color playerColor() {
        return cellContent.Playercolor();
    }

    public Color baseColor() {
        return cellContent.baseColor();
    }
}
