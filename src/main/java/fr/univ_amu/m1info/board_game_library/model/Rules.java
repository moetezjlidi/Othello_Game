package fr.univ_amu.m1info.board_game_library.model;

import fr.univ_amu.m1info.board_game_library.model.gamePlayer.AbstractPlayer;
import fr.univ_amu.m1info.board_game_library.view.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class Rules {
    public int BOARD_SIZE = 8;
    public static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public void checkAndFlipPieces(int row, int column, AbstractPlayer currentPlayer, AbstractPlayer opponentPlayer, Cell[][] cellStates) {
        for (int[] direction : DIRECTIONS) {
            List<int[]> opponentPositions = findOpponentPositions(row, column, direction, opponentPlayer, cellStates);
            if (shouldFlipPieces(opponentPositions, row, column, direction, currentPlayer, cellStates)) {
                flipPieces(opponentPositions, currentPlayer, cellStates);
            }
        }
    }

    private boolean shouldFlipPieces(List<int[]> opponentPositions, int row, int column, int[] direction, AbstractPlayer currentPlayer, Cell[][] cellStates) {
        if (opponentPositions.isEmpty()) {
            return false;
        }
        int dx = direction[0];
        int dy = direction[1];
        int x = row + dx * (opponentPositions.size() + 1);
        int y = column + dy * (opponentPositions.size() + 1);
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && cellStates[x][y].playerColor() == currentPlayer.getColor();
    }

    private void flipPieces(List<int[]> opponentPositions, AbstractPlayer currentPlayer, Cell[][] cellStates) {
        for (int[] position : opponentPositions) {
            int opponentRow = position[0];
            int opponentCol = position[1];
            cellStates[opponentRow][opponentCol].stack(new CellContent(currentPlayer.getShape(), currentPlayer.getColor(), Color.DARKGREEN));
        }
    }

    public boolean isMoveValid(int row, int column, AbstractPlayer currentPlayer, AbstractPlayer opponentPlayer, Cell[][] cellStates) {
        for (int[] direction : DIRECTIONS) {
            int dx = direction[0];
            int dy = direction[1];
            int x = row + dx;
            int y = column + dy;
            boolean hasOpponentBetween = false;

            while (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && cellStates[x][y].playerColor() == opponentPlayer.getColor()) {
                x += dx;
                y += dy;
                hasOpponentBetween = true;
            }
            if (hasOpponentBetween && x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && cellStates[x][y].playerColor() == currentPlayer.getColor()) {
                return true;
            }
        }

        return false;
    }

    public List<int[]> findOpponentPositions(int row, int column, int[] direction, AbstractPlayer opponentPlayer, Cell[][] cellStates) {
        List<int[]> opponentPositions = new ArrayList<>();
        int dx = direction[0];
        int dy = direction[1];
        int x = row + dx;
        int y = column + dy;

        while (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && cellStates[x][y].playerColor() == opponentPlayer.getColor()) {
            opponentPositions.add(new int[]{x, y});
            x += dx;
            y += dy;
        }
        return opponentPositions;
    }
}
