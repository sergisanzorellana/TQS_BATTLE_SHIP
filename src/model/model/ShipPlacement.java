package model;

public class ShipPlacement {
    private int x;
    private int y;
    private int size;
    private String name;
    private boolean horizontal;

    public ShipPlacement(int x, int y, int size, String name, boolean horizontal) {
        assert x >= 0 && x < 10 : "x-coordinate must be between 0 and 9"; // Precondition: x must be valid
        assert y >= 0 && y < 10 : "y-coordinate must be between 0 and 9"; // Precondition: y must be valid
        assert size > 0 : "Ship size must be greater than 0"; // Precondition: size must be positive
        assert name != null && !name.isEmpty() : "Ship name cannot be null or empty"; // Precondition: name must be valid
        assert (horizontal || !horizontal) : "Orientation must be either horizontal or vertical"; // Precondition: valid orientation
        
        this.x = x;
        this.y = y;
        this.size = size;
        this.name = name;
        this.horizontal = horizontal;

        // Postcondition: all properties should be initialized
        assert this.x == x : "x-coordinate not properly initialized";
        assert this.y == y : "y-coordinate not properly initialized";
        assert this.size == size : "Ship size not properly initialized";
        assert this.name.equals(name) : "Ship name not properly initialized";
        assert this.horizontal == horizontal : "Orientation not properly initialized";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public boolean isHorizontal() {
        return horizontal;
    }
}
