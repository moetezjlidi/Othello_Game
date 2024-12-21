package fr.univ_amu.m1info.board_game_library.controller;

import fr.univ_amu.m1info.board_game_library.model.*;

import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;
import fr.univ_amu.m1info.board_game_library.model.gamePlayer.Human;
import fr.univ_amu.m1info.board_game_library.model.gamePlayer.IA;
import fr.univ_amu.m1info.board_game_library.model.mode.GameModeStrategy;
import fr.univ_amu.m1info.board_game_library.model.mode.PVP;
import fr.univ_amu.m1info.board_game_library.model.mode.PvAI;
import fr.univ_amu.m1info.board_game_library.view.graphics.*;
import fr.univ_amu.m1info.board_game_library.view.graphics.Color;
import fr.univ_amu.m1info.board_game_library.view.graphics.Shape;

import javax.swing.*;
import java.awt.*;

public class OthelloController implements BoardGameController {

    private static final int BOARD_SIZE = 8;

    private BoardGameView view;
    private BoardManager boardManager;
    private GameManager gameManager;
    private boolean isGameModeSelected = false; // nv ligne

    @Override
    public void initializeViewOnStart(BoardGameView view) {
        this.view = view;
        view.updateLabeledElement("AI-mode", "AI");
        view.updateLabeledElement("Multi-mode", "Multi-player");
        view.updateLabeledElement("BlackTurnLabel", "Black's TURN");
        view.updateLabeledElement("WhiteTurnLabel", "");
        view.updateLabeledElement("TurnCounterLabel", "Turn: 0");
        //view.updateLabeledElement("GameRulesButton", "Règles du jeu");


    }

    private void initializeGame(GameModeStrategy gameModeStrategy, AbstractPlayer player1, AbstractPlayer player2) {

        GameFlowManager gameFlowManager = new GameFlowManager(boardManager);
        this.gameManager = new GameManager(gameFlowManager,gameModeStrategy.getGameStateManager());
        Cell[][] board = gameManager.startGame(player1, player2, gameModeStrategy);
        updateViewWithTheBoard(board);
        updateScoreLabels();
    }
    private void updateScoreLabels() {
        int[] scores = calculateScores();
        view.updateLabeledElement("BlackScoreLabel", "B :" + scores[1]);
        view.updateLabeledElement("WhiteScoreLabel", "W : " + scores[0]);
    }

    private int[] calculateScores() {
        int blackScore = 0, whiteScore = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (boardManager.getCell(row, column).playerColor() == Color.BLACK) {
                    blackScore++;
                } else if (boardManager.getCell(row, column).playerColor() == Color.WHITE) {
                    whiteScore++;
                }
            }
        }
        return new int[]{whiteScore, blackScore}; // index 0 for white, 1 for black
    }


    public void updateViewWithTheBoard(Cell[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (!board[row][column].isEmpty()) {
                    view.addShapeAtCell(row, column, board[row][column].playerShape(), board[row][column].playerColor());
                }
                view.setCellColor(row, column, board[row][column].baseColor());
            }
        }
    }


    public void boardActionOnClick(int row, int column) {
        boardManager.resetAvailableMovesHighlight();
        gameManager.play(row, column);
        updateViewWithTheBoard(boardManager.getCellStates());
        updateTurnLabel();
        if (gameManager.endGame()) {
            handleGameOver();
        }
        updateScoreLabels();
    }

    private void handleGameOver() {
        int[] scores = calculateScores();
        int whiteScore = scores[0];
        int blackScore = scores[1];

        String resultMessage;
        if (blackScore > whiteScore) {
            resultMessage = "player2(black) wins with " + blackScore + " points!";
        } else if (whiteScore > blackScore) {
            resultMessage = "player1(white) wins with " + whiteScore + " points!";
        } else {
            resultMessage = "It's a tie!";
        }

        view.displayMessage(resultMessage);
    }


    private void updateTurnLabel() {
        boolean isPlayer2Turn = gameManager.isPlayer2Turn();


        if (isPlayer2Turn) {
            view.updateLabeledElement("WhiteTurnLabel", "W TURN");
            view.updateLabeledElement("BlackTurnLabel", "");

        } else {
            view.updateLabeledElement("BlackTurnLabel", "B TURN");
            view.updateLabeledElement("WhiteTurnLabel", "");
        }
        view.updateLabeledElement("TurnCounterLabel", "Turn: " + gameManager.getTurnCounter());
    }

    @Override
    public void buttonActionOnClick(String buttonId) {
        switch (buttonId) {
            case "ClearCells" -> {
                clearBoard(view);
                changeCellColors(view, Color.GREEN, Color.GREEN);
                initializeViewOnStart(view);
                gameManager.setPlayer2Turn(false);
                isGameModeSelected = false;
                view.updateLabeledElement("Player2TurnLabel", "Player2's TURN");
                view.updateLabeledElement("Player1TurnLabel", "");
            }
//            case "ChangeColors" ->{
//                if(isGameModeSelected ) {
//                    changeCellColors(view, Color.BLUE, Color.DARKBLUE);
//                }
//            }
            case "GameRulesButton" -> showRules();
            case "AI-mode" -> {
                if (!isGameModeSelected) {  // Vérifie si le mode de jeu n'a pas encore été choisi
                    isGameModeSelected = true;
                    AbstractPlayer human = new Human(Shape.CIRCLE, Color.BLACK, "Human");
                    AbstractPlayer ai = new IA(Shape.CIRCLE, Color.WHITE, "AI");
                    BoardManager boardManager = new BoardManager(new Rules(), human, ai);
                    GameStateManager gameStateManager = new GameStateManager(human, ai);
                    this.boardManager = boardManager;
                    initializeGame(new PvAI(boardManager, gameStateManager), human, ai);
                    view.updateLabeledElement("AI-mode", "AI mode activated");
                }
            }
            case "Multi-mode" -> {
                if (!isGameModeSelected) {  // Vérifie si le mode de jeu n'a pas encore été choisi
                    isGameModeSelected = true;
                    AbstractPlayer player1 = new Human(Shape.CIRCLE, Color.BLACK, "player1");
                    AbstractPlayer player2 = new Human(Shape.CIRCLE, Color.WHITE, "player2");
                    BoardManager boardManager = new BoardManager(new Rules(), player1, player2);
                    GameStateManager gameStateManager = new GameStateManager(player1, player2);
                    this.boardManager = boardManager;
                    initializeGame(new PVP(boardManager, gameStateManager), player1, player2);
                    view.updateLabeledElement("Multi-mode", "Multi-player mode activated");
                }
            }
            case "UndoMove"->{
            //TODO
            }

            default -> throw new IllegalStateException("Unexpected event, button id: " + buttonId);
        }
    }

    private static void changeCellColors(BoardGameView view, Color oddColor, Color evenColor) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                boolean isEven = (row + column) % 2 == 0;
                Color colorSquare = isEven ? evenColor : oddColor;
                view.setCellColor(row, column, colorSquare);
            }
        }
    }

    private static void clearBoard(BoardGameView view) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                view.removeShapesAtCell(row, column);
                view.setCellColor(row, column, Color.WHITE);
            }
        }
    }

    // Méthode pour afficher les règles du jeu
    private void showRules() {
        String[][] rules = {
                {"1", "Le jeu commence avec 4 pions au centre du plateau."},
                {"2", "Les joueurs jouent alternativement en plaçant un pion de leur couleur."},
                {"3", "Un coup est valide si au moins un pion adverse est retourné."},
                {"4", "Un joueur doit passer son tour s'il ne peut pas jouer."},
                {"5", "La partie se termine lorsqu'aucun joueur ne peut jouer."},
                {"6", "Le joueur avec le plus de pions gagne."}
        };
        String[] columns = {"Étape", "Description"};

        displayTableRules("Règles du jeu Othello", rules, columns);
    }

    private void displayTableRules(String title, String[][] data, String[] colonnes) {

        JFrame dialog = new JFrame(title);
        dialog.setSize(500, 200);
        dialog.setLayout(new BorderLayout());

        JTable table = new JTable(data, colonnes) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        JScrollPane scrollPane = new JScrollPane(table);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(450);
        JButton fermerButton = new JButton("Fermer");
        fermerButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(fermerButton);

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }

}
