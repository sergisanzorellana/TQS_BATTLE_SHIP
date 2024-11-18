package controller;

import model.Board;
import view.BattleshipView;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        BattleshipView view = new BattleshipView();
        BattleshipController controller = new BattleshipController(board, view);
        controller.startGame();
    }
}
