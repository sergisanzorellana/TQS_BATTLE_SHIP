package controller;

import model.Board;
import view.BattleshipView;

public class BattleshipController {
    private Board board;
    private BattleshipView view;

    public BattleshipController(Board board, BattleshipView view) {
        this.board = board;
        this.view = view;
    }

    public void startGame() {
        view.displayWelcomeMessage();
        view.displayBoard(board);
        while (!board.allShipsSunk()) {
            int[] coordinates = view.getMove();
            boolean hit = board.shoot(coordinates[0], coordinates[1]);
            view.displayShotResult(hit);
            view.displayBoard(board);
        }
        view.displayGameOver();
    }
}