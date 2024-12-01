package controller;

import java.util.Random;

public class AIController {

    private Random rand;

    public AIController() {
        this.rand = new Random();
    }

    public int[] makeAIMove(char[][] grid) {
        int x, y;
        do {
            x = rand.nextInt(10);
            y = rand.nextInt(10);
        } while (grid[x][y] == 'X' || grid[x][y] == 'O');
        return new int[] { x, y };
    }
}
