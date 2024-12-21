package fr.univ_amu.m1info.board_game_library;

import fr.univ_amu.m1info.board_game_library.controller.OthelloController;
import fr.univ_amu.m1info.board_game_library.view.graphics.*;
import fr.univ_amu.m1info.board_game_library.view.graphics.configuration.BoardGameConfiguration;
import fr.univ_amu.m1info.board_game_library.view.graphics.configuration.LabeledElementConfiguration;
import fr.univ_amu.m1info.board_game_library.view.graphics.configuration.LabeledElementKind;
import fr.univ_amu.m1info.board_game_library.view.graphics.configuration.BoardGameDimensions;

import java.util.ArrayList;
import java.util.List;

public class OthelloApplication extends OthelloController {
    public static void main(String[] args) {
        BoardGameConfiguration boardGameConfiguration = new BoardGameConfiguration("Othello",
                new BoardGameDimensions(8, 8),
                List.of(
                        new LabeledElementConfiguration("Black's TURN", "BlackTurnLabel", LabeledElementKind.TEXT),
                        new LabeledElementConfiguration("White's TURN", "WhiteTurnLabel", LabeledElementKind.TEXT),
                        new LabeledElementConfiguration("Nouvelle partie", "ClearCells", LabeledElementKind.BUTTON),
                        new LabeledElementConfiguration("Turn: 0", "TurnCounterLabel", LabeledElementKind.TEXT),
                        new LabeledElementConfiguration("Black's score: 0", "BlackScoreLabel", LabeledElementKind.TEXT), // New score label for Black
                        new LabeledElementConfiguration("White's score: 0", "WhiteScoreLabel", LabeledElementKind.TEXT),// New score label for White
                        new LabeledElementConfiguration("Règles du jeu", "GameRulesButton", LabeledElementKind.BUTTON),
                        new LabeledElementConfiguration("AI", "AI-mode", LabeledElementKind.BUTTON),
                        new LabeledElementConfiguration("Multi-player", "Multi-mode", LabeledElementKind.BUTTON),
                        new LabeledElementConfiguration("Undo", "UndoMove", LabeledElementKind.BUTTON)

                ));
        BoardGameController controller = new OthelloController();
        BoardGameApplicationLauncher launcher = JavaFXBoardGameApplicationLauncher.getInstance();
        launcher.launchApplication(boardGameConfiguration, controller);
    }
}

