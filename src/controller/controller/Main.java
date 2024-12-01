package controller;

import model.Player;
import view.BattleshipView;

public class Main {
    public static void main(String[] args) {
        BattleshipView view = new BattleshipView();
        Player player = new Player("player 1", false);
        Player ai = new Player("ai", true);
        BattleshipController controller = new BattleshipController(player, ai, view);
        controller.startGame();
    }
}
