package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Board;
import view.BattleshipView;
import controller.BattleshipController;

public class BattleShipControllerTest {
    @Test
    public void testStartGame() {
        Board board = new Board();
        BattleshipView view = new BattleshipView();
        BattleshipController controller = new BattleshipController(board, view);
        controller.startGame();
    }
}
