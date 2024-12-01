package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Player;

class PlayerTest {

    @Test
    void testPlayer() {
        // Test for a valid AI player
        Player aiPlayer = new Player("AIPlayer", true);
        assertNotNull(aiPlayer.getBoard(), "Board should be initialized");
        assertTrue(aiPlayer.isAI(), "Player should be AI");
        assertEquals("AIPlayer", aiPlayer.getName(), "Name should be AIPlayer");
        assertNotNull(aiPlayer.getAIController(), "AIController should be initialized for AI player");

        // Test for a valid human player
        Player humanPlayer = new Player("HumanPlayer", false);
        assertNotNull(humanPlayer.getBoard(), "Board should be initialized");
        assertFalse(humanPlayer.isAI(), "Player should not be AI");
        assertEquals("HumanPlayer", humanPlayer.getName(), "Name should be HumanPlayer");
        assertNull(humanPlayer.getAIController(), "AIController should be null for human player");

        // Test for invalid player names
        assertThrows(IllegalArgumentException.class, () -> new Player(null, true), "Constructor should throw IllegalArgumentException for null name");
        assertThrows(IllegalArgumentException.class, () -> new Player("", true), "Constructor should throw IllegalArgumentException for empty name");
    }

    @Test
    void testMakeMove() {
        // Test for AI player makeMove
        Player aiPlayer = new Player("AIPlayer", true);
        int[] aiMove = aiPlayer.makeMove();
        assertNotNull(aiMove, "AI move should not be null");
        assertEquals(2, aiMove.length, "AI move should be an array of length 2");
        assertTrue(aiMove[0] >= 0 && aiMove[0] < 10, "AI move x coordinate should be within board boundaries");
        assertTrue(aiMove[1] >= 0 && aiMove[1] < 10, "AI move y coordinate should be within board boundaries");

        // Test for human player makeMove
        Player humanPlayer = new Player("HumanPlayer", false);
        int[] humanMove = humanPlayer.makeMove();
        assertArrayEquals(new int[]{0, 0}, humanMove, "Human move should return placeholder (0,0)");
    }
}
