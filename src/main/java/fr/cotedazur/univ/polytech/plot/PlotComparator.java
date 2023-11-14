package fr.cotedazur.univ.polytech.plot;

import fr.cotedazur.univ.polytech.plot.Plot;

import java.util.Comparator;

/**
 * Comparator for Plot
 */
public class PlotComparator implements Comparator<Plot> {

    /**
     * Compare two plots
     * @param o1 first plot
     * @param o2 second plot
     * @return 0 if the plots are equals, -1 if the first plot is before the second one, 1 if the first plot is after the second one
     */
    @Override
    public int compare(Plot o1, Plot o2) {
        int res = o1.getCoordinate().getX()-o2.getCoordinate().getX();
        return (res==0)? o1.getCoordinate().getY()-o2.getCoordinate().getY() : res;
    }
}
