package fr.univ_amu.m1info.board_game_library.view.graphics.javafx.app;

import fr.univ_amu.m1info.board_game_library.view.graphics.JavaFXBoardGameApplicationLauncher;
import fr.univ_amu.m1info.board_game_library.view.graphics.javafx.view.BoardGameConfigurator;
import fr.univ_amu.m1info.board_game_library.view.graphics.javafx.view.BoardGameControllableView;
import fr.univ_amu.m1info.board_game_library.view.graphics.javafx.view.JavaFXBoardGameViewBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXBoardGameApplication extends Application {


    @Override
    public void start(Stage stage) {
        var launcher = JavaFXBoardGameApplicationLauncher.getInstance();
        var configuration = launcher.getConfiguration();
        var controller = launcher.getController();
        final JavaFXBoardGameViewBuilder viewBuilder = new JavaFXBoardGameViewBuilder(stage);
        new BoardGameConfigurator().configure(viewBuilder, configuration);
        BoardGameControllableView view = viewBuilder.getView();
        view.setController(controller);
        controller.initializeViewOnStart(view);
        stage.show();
    }
}
