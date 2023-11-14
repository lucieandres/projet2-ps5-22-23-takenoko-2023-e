package fr.cotedazur.univ.polytech.board;

import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.startingpoint.Referee;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static fr.cotedazur.univ.polytech.startingpoint.Referee.TOOLS;

/**
 * This class is used to find all the possible place where a plot can be placed
 * It's a HashSet of Coordinate
 */
public class PoseablePlacePlot extends HashSet<Coordinate> {

    /**
     * Constructor of PoseablePlacePlot
     * @param plot to find the poseable place
     * @param referee to get usable methode
     */
    public void findNewPoseablePlace(Plot plot, Referee referee) {
        Coordinate cooPlot = plot.getCoordinate();
        Board board = referee.getBoard();

        for(Direction dir : Direction.allDirection()) {
            Optional<Plot> optPlot = TOOLS.findPlot(TOOLS.newCoordinateWithADirection(cooPlot, 1, dir), board);
            if(optPlot.isPresent()) {
                Coordinate cooAdj = optPlot.get().getCoordinate();
                List<Coordinate> listCooAdj = generateCoordinateFromAdjCoordinate(cooPlot, cooAdj, dir, referee);
                for (Coordinate tmpcoo : listCooAdj) {
                    if(TOOLS.findPlot(tmpcoo, board).isEmpty()) {
                        add(tmpcoo);
                    }
                }
            }
        }
    }

    /** Generate a list of coordinate (2 Coordinates) where they were adjacent to cooPlot & cooNeighbor
     *
     * @param cooPlot Coordinate of the plot
     * @param cooNeighbor Coordinate of the Neighbor
     * @param direction of the movement between the plot & the neighbor
     * @param referee to get usable methode
     * @return a list of 2 coordinate
     */
    public List<Coordinate> generateCoordinateFromAdjCoordinate(Coordinate cooPlot, Coordinate cooNeighbor, Direction direction, Referee referee) {
        List<Coordinate> listCooAdj = new ArrayList<>();

        switch (direction) {
            case MY, MZ :
                listCooAdj.add(TOOLS.newCoordinateWithADirection(cooPlot, 1, Direction.MX));
                listCooAdj.add(TOOLS.newCoordinateWithADirection(cooNeighbor, 1, Direction.X));
                break;
            case Y, Z :
                listCooAdj.add(TOOLS.newCoordinateWithADirection(cooPlot, 1, Direction.X));
                listCooAdj.add(TOOLS.newCoordinateWithADirection(cooNeighbor, 1, Direction.MX));
                break;
            case MX :
                listCooAdj.add(TOOLS.newCoordinateWithADirection(cooPlot, 1, Direction.MY));
                listCooAdj.add(TOOLS.newCoordinateWithADirection(cooNeighbor, 1, Direction.Y));
                break;
            case X :
                listCooAdj.add(TOOLS.newCoordinateWithADirection(cooPlot, 1, Direction.Y));
                listCooAdj.add(TOOLS.newCoordinateWithADirection(cooNeighbor, 1, Direction.MY));
                break;
        }
        return listCooAdj;
    }

    /**
     * The toString methode
     * @return the string of the PoseablePlacePlot
     */
    @Override
    public String toString() {
        String res = "Poseable = [ ";
        for(Coordinate coo : this) {
            res += coo.toString() + "| ";
        }
        return res + " ]";
    }
}
