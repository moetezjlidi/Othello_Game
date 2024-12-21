package fr.univ_amu.m1info.board_game_library.model;

import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;

public record CellContent(Shape playerShape, Color Playercolor, Color baseColor) {
}
