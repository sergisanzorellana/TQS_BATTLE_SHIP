package controller;

import model.Player;
import model.ShipPlacement;
import view.BattleshipView;

import java.util.List;
import java.util.Random;

public class BattleshipController {
    private Player player;
    private Player ai;
    private BattleshipView view;

    public BattleshipController(Player player, Player ai, BattleshipView view) {
        this.player = player;
        this.ai = ai;
        this.view = view;
    }

    public void startGame() {
        view.displayWelcomeMessage();

        // Let the user place their ships manually
        placePlayerShips(player);

        // Let the AI place its ships randomly
        placeAIShips(ai);

        // Now, start the game where players take turns
        while (!player.getBoard().allShipsSunk() && !ai.getBoard().allShipsSunk()) {
            playTurn(player, ai);
            if (!ai.getBoard().allShipsSunk()) {
                playTurn(ai, player);
            }
        }
        view.displayGameOver();
    }

    private void placePlayerShips(Player player) {
        // Place ships manually by user input
        List<ShipPlacement> playerPlacements = view.getUserShipPlacements();
        for (ShipPlacement placement : playerPlacements) {
            player.getBoard().placeShip(placement);
        }
    }

    private void placeAIShips(Player ai) {
        // AI places ships randomly
        Random rand = new Random();
        for (String shipName : new String[]{"Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"}) {
            boolean placed = false;
            while (!placed) {
                int x = rand.nextInt(10);
                int y = rand.nextInt(10);
                boolean horizontal = rand.nextBoolean();
                int size = getShipSize(shipName);
                ShipPlacement placement = new ShipPlacement(x, y, size, shipName, horizontal);
                try {
                    ai.getBoard().placeShip(placement);
                    placed = true;
                } catch (IllegalArgumentException e) {
                    // Ship placement failed, generate new coordinates and try again
                    System.out.println("Failed to place " + shipName + " at (" + x + ", " + y + ") horizontally: " + horizontal + ". Retrying...");
                }
            }
        }
    }

    private int getShipSize(String shipName) {
        switch (shipName) {
            case "Carrier": return 5;
            case "Battleship": return 4;
            case "Cruiser": return 3;
            case "Submarine": return 3;
            case "Destroyer": return 2;
            default: throw new IllegalArgumentException("Unknown ship type");
        }
    }

    private void playTurn(Player attacker, Player defender) {
        int[] coordinates;
        if (attacker.isAI()) {
            coordinates = attacker.makeMove();
        } else {
            view.displayPublicBoard(defender.getBoard());
            coordinates = view.getMove();
        }
        boolean hit = defender.getBoard().shoot(coordinates[0], coordinates[1]);
        if (attacker.isAI()) {
            view.displayBoard(defender.getBoard());
        }
        view.displayShotResult(hit);
    }
}
