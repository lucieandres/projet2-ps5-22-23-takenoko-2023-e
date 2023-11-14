package fr.cotedazur.univ.polytech.irrigation;

import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;

import java.util.*;

/**
 * Coordinates irrigable by a canal
 */
public class IrrigableCoordinates {
    private Coordinate mainCoordinate;
    private Direction mainSide;
    private Coordinate secondCoordinate;

    /**
     * Constructor
     * @param coo1
     * @param dir
     * @param coo2
     */
    public IrrigableCoordinates(Coordinate coo1, Direction dir, Coordinate coo2) {
        if(coo1.hashCode()<coo2.hashCode()) {
            mainCoordinate = coo1;
            mainSide = dir;
            secondCoordinate = coo2;
        }
        else {
            mainCoordinate = coo2;
            mainSide = dir.oppositeDirection();
            secondCoordinate = coo1;
        }
    }

    /**
     * Get the main coordinate
     * @return main coordinate
     */
    public Coordinate getMainCoordinate() {
        return mainCoordinate;
    }

    /**
     * Get the second coordinate
     * @return second coordinate
     */
    public Coordinate getSecondCoordinate() {
        return secondCoordinate;
    }

    /**
     * Get the main side
     * @return main side
     */
    public Direction getMainSide() {
        return mainSide;
    }

    /**
     * Get the side of the coordinate
     * @param coordinate
     * @return side of the coordinate
     */
    public Direction getSideByCoordinate(Coordinate coordinate) {
        if(!contains(coordinate)) return null;
        if(mainCoordinate == coordinate) return mainSide;
        return mainSide.oppositeDirection();
    }

    /**
     * Get the coordinates
     * @return coordinates
     */
    public List<Coordinate> getCoordinates() {
        List<Coordinate> coos = new ArrayList<>();
        coos.add(mainCoordinate);
        coos.add(secondCoordinate);
        return coos;
    }

    /**
     * Check if the coordinate is in the irrigable coordinates
     * @param coordinate
     * @return true if the coordinate is in the irrigable coordinates
     */
    public boolean contains(Coordinate coordinate) {
        return (mainCoordinate.equals(coordinate))? true : secondCoordinate.equals(coordinate);
    }

    /**
     * Get the coordinates with the direction irrigable
     * @return coordinates with the direction irrigable
     */
    public Map<Coordinate, Direction> getCoordinatesWithDirectionIrrigable() {
        Map<Coordinate, Direction> coosDir = new HashMap<>();
        coosDir.put(mainCoordinate, mainSide);
        coosDir.put(secondCoordinate, mainSide.oppositeDirection());
        return coosDir;
    }

    /**
     * Check if the irrigable coordinates are equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(this instanceof IrrigableCoordinates)) return false;
        IrrigableCoordinates ic = (IrrigableCoordinates) o;
        if(this.mainSide != ic.mainSide) return false;
        if(!this.mainCoordinate.equals(ic.mainCoordinate) && !this.secondCoordinate.equals(ic.mainCoordinate)) return false;
        if(!this.mainCoordinate.equals(ic.secondCoordinate) && !this.secondCoordinate.equals(ic.secondCoordinate)) return false;
        return true;
    }

    /**
     * Get the string of the irrigable coordinates
     * @return string of the irrigable coordinates
     */
    @Override
    public String toString() {
        return "main coord. : " + mainCoordinate + " | " + mainSide + " | 2nd coord. : " + secondCoordinate+"\t";
    }

    /**
     * Get the hashcode of the irrigable coordinates
     * @return hashcode of the irrigable coordinates
     */
    @Override
    public int hashCode() {
        return mainCoordinate.hashCode()*1000 + secondCoordinate.hashCode();
    }
}
