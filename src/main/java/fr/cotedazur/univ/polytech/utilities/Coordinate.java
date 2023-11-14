package fr.cotedazur.univ.polytech.utilities;

/**
 * Coordinate class is used to represent a coordinate in the game
 */
public class Coordinate {
    private int x,y;

    /**
     * Constructor of the Coordinate class
     * @param x
     * @param y
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Clone the coordinate
     * @return
     */
    public Coordinate clone() {
        return new Coordinate(this.x, this.y);
    }

    /**
     * Get the x coordinate
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y coordinate
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Add an int to the current coordinate
     * @param y the int to add to the y coordinate
     */
    public void addY(int y) {
        this.y += y;
    }

    /**
     * Add an int to the current coordinate
     * @param x the int to add to the x coordinate
     */
    public void addX(int x) {
        this.x += x;
    }

    /**
     * Set the coordinate
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setCoordonate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set the x coordinate
     * @param x the x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the y coordinate
     * @param y the y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Check if the Coordiniate is equal to the given coordinate
     * @param coordinate the coordinate to check
     * @return true if the object is equal to the coordinate, false otherwise
     */
    public boolean equals(Coordinate coordinate) {
        return x == coordinate.getX() && y == coordinate.getY();
    }

    /**
     * toString method
     * @return the string representation of the coordinate
     */
    public String toString() {
        return "x: " + x + ", y: " + y;
    }

    /**
     * Check if the object is equal to the coordinate
     * @param o
     * @return true if the object is equal to the coordinate, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Coordinate){
            Coordinate one = (Coordinate) o;
            if(one.getX()==this.getX() && one.getY()==this.getY()){
                return true;
            }
        }
        return false;
    }

    /**
     * Hashcode method
     * @return the hashcode of the coordinate
     */
    @Override
    public int hashCode() {
        return x*10000+y;
    }

    /**
     *
     * @param that is the coordinate you want to Reach
     * @return the direction to reach that
     */
    public Direction getDirectionToReachCoo(Coordinate that) {
        if(this == that) return null;
        if(this.x > that.getX()) {
            if(this.y >= that.getY()) return Direction.MY;
            else return Direction.Z;
        }
        if(this.x == that.getX()) {
            if(this.y >= that.getY()) return Direction.MX;
            else return Direction.X;
        }
        else {
            if(this.y >= that.getY()) return Direction.MZ;
            else return Direction.Y;
        }
    }
}
