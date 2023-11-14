package fr.cotedazur.univ.polytech.plot;

import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A plot constitute a cell of the board game and is represented by a coordinate
 */
public class Plot {
    Coordinate coordinate;
    Set<Direction> irrigation;

    /**
     * Constructor of a plot
     * @param coordinate is the coordinate of the plot
     */
    public Plot(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.irrigation = new HashSet<>();
    }

    /**
     * Getter of the coordinate
     * @return the coordinate of the plot
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /***
     * compare the param plot to the current (used to place the param plot in a Row)
     *
     * @param plot is the plot compared to the current one
     * @return if currentPlot > param plot
     */
    public boolean isBiggerInRow(Plot plot) {
        return this.coordinate.getY()<plot.coordinate.getY();
    }

    /***
     * compare the param plot to the current (used to place the param plot in a Row)
     *
     * @param plot is the plot compared to the current one
     * @return if currentPlot > param plot
     */
    public boolean isSmallerInRow(Plot plot) {
        return this.coordinate.getY()>plot.coordinate.getY();
    }

    /**
     * toString method
     * @return the string representation of the plot
     */
    public String toString() {
        return "Plot at " + coordinate.toString();
    }

    /**
     * Check if the plot is irrigated
     * @return true if the plot is irrigated, false otherwise
     */
    public boolean isIrrigated() {
        return !irrigation.isEmpty();
    }

    /**
     * Getter of the list of irrigation
     * @return the list of irrigation
     */
    public List<Direction> getIrrigation() {
        return irrigation.stream().toList();
    }

    /**
     * Add an irrigation to the plot
     * @param direction is the direction of the irrigation
     */
    public void addIrrigation(Direction direction) {
        irrigation.add(direction);
    }

    /**
     * Add a set of irrigation to the plot
     * @param directions is the set of directions of the irrigation
     */
    public void addAllIrrigation(Set<Direction> directions) { irrigation.addAll(directions);}

    /**
     * Set the irrigation of the plot
     * @param directions is the set of directions of the irrigation
     */
    public void setAllIrrigation(Set<Direction> directions) { irrigation = directions;}

}