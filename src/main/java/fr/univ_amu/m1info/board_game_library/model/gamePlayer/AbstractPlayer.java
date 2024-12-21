package fr.univ_amu.m1info.board_game_library.model.gamePlayer;

import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;

public abstract class AbstractPlayer {

    Shape shape;
    Color color;
    String name;

    AbstractPlayer(Shape shape, Color color, String name) {
        this.shape = shape;
        this.color = color;
        this.name = name;
    }
    // TODO : Transformer la classe Human en abstract player et utiliser human et ai pour différencier les players pour qu'on choisise un gameMode en fonction

    public Color getColor(){
        return color;
    };

    public Shape getShape(){
        return shape;
    };
}
