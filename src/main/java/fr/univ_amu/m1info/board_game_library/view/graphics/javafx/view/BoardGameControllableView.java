package fr.univ_amu.m1info.board_game_library.view.graphics.javafx.view;

import fr.univ_amu.m1info.board_game_library.view.graphics.BoardGameController;
import fr.univ_amu.m1info.board_game_library.view.graphics.BoardGameView;


public interface BoardGameControllableView extends BoardGameView {
    void setController(BoardGameController controller);
}
