package fr.cotedazur.univ.polytech.pawn;

import fr.cotedazur.univ.polytech.board.Board;
import fr.cotedazur.univ.polytech.object.*;
import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.object.ImprovementType;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.plot.PlotUtilitiesTools;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

import static fr.cotedazur.univ.polytech.startingpoint.Main.LOGGER;

/**
 * Gardener is a pawn that can plant bamboo on the board
 */
public class Gardener extends Pawn {

    /**
     * Constructor of the Gardener
     * @param plot the plot where the Gardener is
     */
    public Gardener(Plot plot) {
        super(plot);
    }

    /**
     * Action of the Gardener : plant bamboo on the board if the plot is irrigated and if there is less than 4 bamboo on the plot
     * (and double if there is a fertilizer)
     * @param tools the tools of the game
     * @param board the board of the game
     * @param mapBamboo the map of the bamboo
     */
    public void action(PlotUtilitiesTools tools, Board board, Map<Color, LinkedList<Bamboo>> mapBamboo) {
        HashSet<PlotColor> plots = new HashSet<>();
        plots = tools.coloredZone(plot, board);
        int nbBamboo = 0;
        for(PlotColor plotColor : plots) {
            if (plotColor instanceof PlotColor) {
                    if (plotColor.isIrrigated() && plotColor.getSizeBambooList() <= 4) {
                    plotColor.addBamboo(new Bamboo(plotColor.getColor()));
                    nbBamboo++;
                    if((plotColor.getImprovement().isPresent() && plotColor.getImprovement().get().getType() == ImprovementType.FERTILIZER) && plotColor.getSizeBambooList() <= 4) {
                        plotColor.addBamboo(new Bamboo(plotColor.getColor()));
                        nbBamboo++;
                    }

                }
            }
        }
        LOGGER.finer("Le jardinier a planté " + nbBamboo + " bambou(s)");
    }

    /**
     * toString method
     * @return the string of the Gardener
     */
    @Override
    public String toString() {
        return "Gardener > " + getCoordinate().toString();
    }

}
