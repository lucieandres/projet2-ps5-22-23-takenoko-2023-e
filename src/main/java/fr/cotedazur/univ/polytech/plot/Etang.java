package fr.cotedazur.univ.polytech.plot;

import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;

import java.util.HashSet;

/**
 * Etang is a plot that can irrigated in all directions
 */
public class Etang extends Plot {

    /**
     * Constructor of Etang
     */
    public Etang() {
        super(new Coordinate(0, 0));
        irrigation = new HashSet<>(Direction.allDirection());
    }

    /**
     * toString method
     * @return a string
     */
    @Override
    public String toString() {
        return "- Etang -";
    }
}
