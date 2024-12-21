package fr.univ_amu.m1info.board_game_library.model.gamePlayer;

import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;

public class Human extends AbstractPlayer {

    public Human(Shape shape, Color color, String name) {
        super(shape, color, name);
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

}
