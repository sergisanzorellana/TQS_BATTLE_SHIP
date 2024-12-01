package test;

import static org.junit.jupiter.api.Assertions.*;

import model.Player;
import controller.BattleshipController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.BattleshipView;

public class BattleShipControllerTest {

    @Test
    void testBattleshipController() {
        Player player = Mockito.mock(Player.class);
        Player ai = Mockito.mock(Player.class);
        BattleshipView view = Mockito.mock(BattleshipView.class);

        BattleshipController controller = new BattleshipController(player, ai, view);

        assertNotNull(controller, "Controller should be properly initialized");
    }

    @Test
    void testStartGame() {
        Player player = Mockito.mock(Player.class);
        Player ai = Mockito.mock(Player.class);
        BattleshipView view = Mockito.mock(BattleshipView.class);

        Mockito.when(player.getBoard()).thenReturn(Mockito.mock(model.Board.class));
        Mockito.when(ai.getBoard()).thenReturn(Mockito.mock(model.Board.class));

        BattleshipController controller = new BattleshipController(player, ai, view);

        // Mocking interactions
        Mockito.doNothing().when(view).displayWelcomeMessage();
        Mockito.doNothing().when(view).displayGameOver();
        Mockito.doNothing().when(view).displayPublicBoard(Mockito.any());
        Mockito.doNothing().when(view).displayBoard(Mockito.any());
        Mockito.doNothing().when(view).displayShotResult(Mockito.anyBoolean());
        Mockito.when(view.getMove()).thenReturn(new int[]{0, 0});

        Mockito.when(player.getBoard().allShipsSunk()).thenReturn(false, false, true);
        Mockito.when(ai.getBoard().allShipsSunk()).thenReturn(false, true);

        controller.startGame();

        // Verify interactions
        Mockito.verify(view).displayWelcomeMessage();
        Mockito.verify(view).displayGameOver();
    }
}
