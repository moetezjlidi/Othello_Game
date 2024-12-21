package fr.univ_amu.m1info.board_game_library.model.gamePlayer;

import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;

public class IA extends AbstractPlayer {

   public IA(Shape shape, Color color, String name){
       super(shape, color, name);
   }

   IA(Shape shape, Color color){
       super(shape, color, "IA");
   }
}
