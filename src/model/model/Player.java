package model;

import java.util.List;

import controller.AIController;

public class Player {
    private String name;
    private Board board;
    private boolean isAI;
    private AIController aiController;

    public Player(String name, boolean isAI) {
        // Precondition: name should not be null or empty
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
        this.isAI = isAI;
        this.board = new Board();
        this.aiController = isAI ? new AIController() : null;

        // Postcondition: name, board, and isAI should be properly initialized
        assert this.name.equals(name) : "Name not properly initialized";
        assert this.board != null : "Board not properly initialized";
        assert this.isAI == isAI : "AI status not properly initialized";
        assert (isAI && this.aiController != null) || (!isAI && this.aiController == null) : "AIController not properly initialized";
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

    public AIController getAIController() {
        return aiController;
    }

    public int[] makeMove() {
        if (isAI) {
            // Precondition: aiController should not be null for AI player
            assert aiController != null : "AIController should not be null for AI player";
            int[] move = aiController.makeAIMove(board.getGrid());
            // Postcondition: move should be valid coordinates within board boundaries
            assert move.length == 2 : "Move should be an array of length 2";
            assert move[0] >= 0 && move[0] < 10 : "Move x coordinate should be within board boundaries";
            assert move[1] >= 0 && move[1] < 10 : "Move y coordinate should be within board boundaries";
            return move;
        } else {
            return new int[]{0, 0}; // Placeholder for human player moves
        }
    }

	public List<ShipPlacement> getShipPlacements() {
		// TODO Auto-generated method stub
		return null;
	}
}
