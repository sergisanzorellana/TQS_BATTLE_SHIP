package view;

import model.Board;
import model.ShipPlacement;
import java.util.List;
import java.util.ArrayList;

public class MockBattleshipView extends BattleshipView {
    private List<int[]> moves;
    private StringBuilder log;

    public MockBattleshipView(List<int[]> moves) {
        this.moves = moves;
        this.log = new StringBuilder();
    }

    @Override
    public void displayWelcomeMessage() {
        log.append("Welcome to Battleship!\n");
    }

    @Override
    public void displayPublicBoard(Board board) {
        log.append("Displaying public board.\n");
    }

    @Override
    public void displayBoard(Board board) {
        log.append("Displaying board.\n");
    }

    @Override
    public void displayShotResult(boolean hit) {
        log.append(hit ? "Hit!\n" : "Miss!\n");
    }

    @Override
    public int[] getMove() {
        return moves.isEmpty() ? new int[] {0, 0} : moves.remove(0);
    }

    @Override
    public void displayGameOver() {
        log.append("Game Over!\n");
    }

    @Override
    public List<ShipPlacement> getUserShipPlacements() {
        // Mock ship placements
        List<ShipPlacement> placements = new ArrayList<>();
        placements.add(new ShipPlacement(0, 0, 5, "Carrier", true));
        return placements;
    }

    public String getLog() {
        return log.toString();
    }
}
