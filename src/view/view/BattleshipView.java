package view;

import model.Board;
import model.ShipPlacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattleshipView {
    private Scanner scanner;

    public BattleshipView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to Battleship!");
    }

    // Display the user's board with ships, hits, and misses
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

    // Display the opponent's board with ships hidden and showing hits/misses
    public void displayPublicBoard(Board board) {
        char[][] grid = board.getGrid();
        System.out.println("  0 1 2 3 4 5 6 7 8 9 ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < grid[i].length; j++) {
                // Only show hits and misses, hide ships
                if (grid[i][j] == 'S') {
                    System.out.print("~ "); // Display water for ships
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void displayShotResult(boolean hit) {
        if (hit) {
            System.out.println("Hit!");
        } else {
            System.out.println("Miss!");
        }
    }

    public int[] getMove() {
        while (true) {
            System.out.println("Enter coordinates for your shot (column and row): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if (x >= 0 && x < 10 && y >= 0 && y < 10) {
                return new int[]{y, x};
            } else {
                System.out.println("Invalid coordinates. Please enter values between 0 and 9.");
            }
        }
    }

    public void displayGameOver() {
        System.out.println("Game Over!");
    }

    // Get the user's ship placements
    public List<ShipPlacement> getUserShipPlacements() {
        List<ShipPlacement> placements = new ArrayList<>();
        // Predefined ship sizes and names
        String[] shipNames = {"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
        int[] shipSizes = {5, 4, 3, 3, 2};

        for (int i = 0; i < shipNames.length; i++) {
            System.out.println("Place your " + shipNames[i] + " (Size: " + shipSizes[i] + ")");
            System.out.print("Enter the starting x-coordinate (0-9): ");
            int x = scanner.nextInt();

            System.out.print("Enter the starting y-coordinate (0-9): ");
            int y = scanner.nextInt();

            System.out.print("Enter the orientation (h for horizontal, v for vertical): ");
            char orientation = scanner.next().charAt(0);

            boolean isHorizontal = (orientation == 'h');
            
            // Create the ShipPlacement object and add it to the list
            placements.add(new ShipPlacement(x, y, shipSizes[i], shipNames[i], isHorizontal));
        }
        return placements;
    }
}
