package model;


import java.util.ArrayList;
import java.util.List;

public class MockPlayer {
    private final String name;
    private final boolean isAI;
    private final List<int[]> moves; // Predefined moves
    private int moveIndex; // Keeps track of the next move to make
    private Board board;

    public MockPlayer(String name, boolean isAI, List<int[]> moves) {
        this.name = name;
        this.isAI = isAI;
        this.moves = moves;
        this.moveIndex = 0;
        this.board = new Board();
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isAI() {
        return isAI;
    }

    public int[] makeMove() {
        if (moveIndex < moves.size()) {
            return moves.get(moveIndex++);
        } else {
            throw new IllegalStateException("No more moves available");
        }
    }
    
    public List<int[]> getMoves() {
    	return this.moves;
    }

    public List<ShipPlacement> getShipPlacements() {
        List<ShipPlacement> placements = new ArrayList<>();
        placements.add(new ShipPlacement(0, 0, 5, "Carrier", true));
        placements.add(new ShipPlacement(2, 2, 4, "Battleship", false));
        placements.add(new ShipPlacement(4, 4, 3, "Cruiser", true));
        placements.add(new ShipPlacement(6, 6, 3, "Submarine", false));
        placements.add(new ShipPlacement(8, 8, 2, "Destroyer", true));
        return placements;
    }
}
