module fr.univ_amu.m1info.board_game_library {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports fr.univ_amu.m1info.board_game_library.view.graphics;
    exports fr.univ_amu.m1info.board_game_library;
    exports fr.univ_amu.m1info.board_game_library.view.graphics.javafx.app;
    exports fr.univ_amu.m1info.board_game_library.view.graphics.configuration;
    exports fr.univ_amu.m1info.board_game_library.view.graphics.javafx.view;
    exports fr.univ_amu.m1info.board_game_library.view.graphics.javafx.bar;
    exports fr.univ_amu.m1info.board_game_library.view.graphics.javafx.board;
    exports fr.univ_amu.m1info.board_game_library.model;
}