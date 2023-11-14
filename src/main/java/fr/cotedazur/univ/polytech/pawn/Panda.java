package fr.cotedazur.univ.polytech.pawn;

import fr.cotedazur.univ.polytech.board.Board;
import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.object.ImprovementType;
import fr.cotedazur.univ.polytech.plot.PlotUtilitiesTools;
import fr.cotedazur.univ.polytech.startingpoint.Referee;

import static fr.cotedazur.univ.polytech.startingpoint.Main.LOGGER;

/**
 * Panda is a pawn that can eat bamboo on the board
 */
public class Panda extends Pawn {

    /**
     * Constructor of the panda
     * @param plot the plot where the panda is
     */
    public Panda(Plot plot) {
        super(plot);
    }

    /**
     * The panda eats bamboo on the plot where it is
     * (if there is no enclosure)
     * @param rf the referee
     * @param tools the tools to use
     * @param board the board
     */
    public void action(Referee rf, PlotUtilitiesTools tools, Board board) {
        if (plot instanceof PlotColor) {
            PlotColor plotColor = (PlotColor) plot;
            if((plotColor.getImprovement().isPresent() && plotColor.getImprovement().get().getType() == ImprovementType.ENCLOSURE)) {
                LOGGER.finer("Un enclos protège le bambou du panda");
            } else {
                if (plotColor.getSizeBambooList() > 0) {
                    LOGGER.finer("Le panda a mangé 1 bambou de couleur " + plotColor.getBambooList().get(0).getColor());
                    rf.getCurrentPlayer().addBamboo(plotColor.getBambooList().get(0));
                    plotColor.removeBamboo(plotColor.getBambooList().get(0));
                } else {
                    LOGGER.finer("Le panda n'a pas mangé de bambou");
                }
            }
        }


    }

    /**
     * toString method
     * @return the string
     */
    @Override
    public String toString() {
        return "Panda > " + getCoordinate().toString();
    }
}
