package fr.cotedazur.univ.polytech.pawn;

import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.utilities.Coordinate;

/**
 * Pawn is the abstract class of all the pawns in the game.
 */
public abstract class Pawn {
    Plot plot;

    /**
     * Constructor of the class Pawn.
     * @param plot the plot where the pawn is.
     */
    public Pawn(Plot plot) {
        this.plot = plot;
    }

    /**
     * Getter of the plot where the pawn is.
     * @return the plot where the pawn is.
     */
    public Plot getPlot() {
        return plot;
    }

    /**
     * Setter of the plot where the pawn is.
     * @param plot the plot where the pawn is.
     */
    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    /**
     * Getter of the coordinate of the plot where the pawn is.
     * @return the coordinate of the plot where the pawn is.
     */
    public Coordinate getCoordinate() {
        return plot.getCoordinate();
    }

    /**
     * toString method of the class Pawn.
     * @return the string representation of the pawn.
     */
    public abstract String toString();


}
