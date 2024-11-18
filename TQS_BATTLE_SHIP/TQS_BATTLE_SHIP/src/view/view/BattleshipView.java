package view;

import model.Board;
import java.util.Scanner;

public class BattleshipView {
    private Scanner scanner;

    public BattleshipView() {
        scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println("¡Bienvenidos a Undir la flota!");
    }

    public void displayBoard(Board board) {
        char[][] grid = board.getGrid();
        System.out.println("  0 1 2 3 4 5 6 7 8 9 ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[] getMove() {
        System.out.println("Introduce las coordenadas para disparar (formato: x y): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new int[]{x, y};
    }

    public void displayShotResult(boolean hit) {
        if (hit) {
            System.out.println("¡Tocado!");
        } else {
            System.out.println("Agua...");
        }
    }

    public void displayGameOver() {
        System.out.println("¡Juego terminado! Todos los barcos han sido hundidos.");
    }
}
