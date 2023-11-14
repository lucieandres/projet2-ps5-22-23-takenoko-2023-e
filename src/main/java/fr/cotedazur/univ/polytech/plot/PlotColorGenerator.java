package fr.cotedazur.univ.polytech.plot;

import fr.cotedazur.univ.polytech.utilities.Color;

import java.util.*;

/**
 * Generate a list of plot color for the game
 */
public class PlotColorGenerator {

    /**
     * Generate a list of plot color for the game
     * @param NBPLOTPERTYPE number of plot per type
     * @return a list of plot color
     */
    public LinkedList<PlotColor> generatePlotList(int NBPLOTPERTYPE) {
        LinkedList<PlotColor> tmp = new LinkedList<PlotColor>();
        for (int i = 0; i < NBPLOTPERTYPE; i++) {
            tmp.add(new PlotColor(null, Color.PINK));
            tmp.add(new PlotColor(null, Color.GREEN));
            tmp.add(new PlotColor(null, Color.YELLOW));
        }
        Collections.shuffle(tmp, new Random());
        return tmp;
    }
}