package fr.univ_amu.m1info.board_game_library.view.graphics.javafx.view;

import fr.univ_amu.m1info.board_game_library.view.graphics.BoardGameController;
import fr.univ_amu.m1info.board_game_library.view.graphics.javafx.bar.Bar;
import fr.univ_amu.m1info.board_game_library.view.graphics.javafx.board.BoardGridView;
import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.control.ButtonType;


public class JavaFXBoardGameView implements BoardGameControllableView {
    private final Stage stage;
    private BoardGridView boardGridView;
    private Bar bar;
    private BoardGameController controller;

    public void setController(BoardGameController controller) {
        this.controller = controller;
    }

    public JavaFXBoardGameView(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(_ -> Platform.exit());
        stage.setResizable(false);
        stage.sizeToScene();
    }

    public synchronized void reset() {
        VBox vBox = new VBox();
        bar = new Bar();
        boardGridView = new BoardGridView();
        vBox.getChildren().add(bar);
        vBox.getChildren().add(boardGridView);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
    }

    @Override
    public synchronized void updateLabeledElement(String id, String newText) {
        bar.updateLabel(id, newText);
    }

    @Override
    public synchronized void setCellColor(int row, int column, Color color) {
        boardGridView.setColorSquare(row, column, color);
    }

    @Override
    public synchronized void addShapeAtCell(int row, int column, Shape shape, Color color) {
        boardGridView.addShapeAtSquare(row, column, shape, color);
    }

    @Override
    public synchronized void removeShapesAtCell(int row, int column) {
        boardGridView.removeShapesAtSquare(row, column);
    }

    public BoardGridView getBoardGridView() {
        return boardGridView;
    }

    public Stage getStage() {
        return stage;
    }

    public Bar getBar() {
        return bar;
    }



    public void buttonActionOnclick(String id){
        controller.buttonActionOnClick(id);
    }

    public void boardActionOnclick(int row, int column){
        controller.boardActionOnClick(row, column);
    }

    public void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fin de la Partie");
        alert.setHeaderText("Résultat");
        alert.setContentText(message);

        // Ajouter des boutons personnalisés
        //ButtonType replayButton = new ButtonType("Rejouer");
        ButtonType quitButton = new ButtonType("Quitter");
        alert.getButtonTypes().setAll(quitButton);

        // Ajout d'une icône personnalisée
        // Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        // stage.getIcons().add(new Image("C:/Users/malak/Downloads/othello.jpg"));
        // Gérer les actions
        /*alert.showAndWait().ifPresent(response -> {
            if (response == replayButton) {
                System.out.println("Rejouer !");
            } else if (response == quitButton) {
                System.exit(0);
            }
        });*/

        // Afficher la fenêtre
        alert.showAndWait();
    }

}
