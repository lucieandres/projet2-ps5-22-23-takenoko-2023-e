package fr.cotedazur.univ.polytech.plot;

import fr.cotedazur.univ.polytech.board.Board;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;
import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinates;

import java.util.*;

/**
 * Used for all methods which usable by multiple class
 */
public class PlotUtilitiesTools {

    /**
     * Create new coordinate from older and a combo distance + direction
     *
     * @param coordinate based
     * @param distance
     * @param direction
     * @return the new coordinate
     */
    public Coordinate newCoordinateWithADirection(Coordinate coordinate, int distance, Direction direction) {
        Coordinate tmp = coordinate.clone();
        switch (direction) {
            case X:
                tmp.addY(distance);
                break;
            case MX:
                tmp.addY(-distance);
                break;
            case Y:
                tmp.addX(distance);
                break;
            case MY:
                tmp.addX(-distance);
                break;
            case Z:
                tmp.addX(-distance);
                tmp.addY(distance);
                break;
            case MZ:
                tmp.addX(distance);
                tmp.addY(-distance);
                break;
            default:
                break;
        }
        return tmp;
    }

    /**
     * Used to find a plot by coordinate
     *
     * @param coordinate target
     * @param board
     * @return the plot in optional status
     */
    public Optional<Plot> findPlot(Coordinate coordinate, Board board) {
        int row = -1;
        int index = -1;
        for(int i = 0; i < board.size(); i++) {
            if(board.get(i).getRowPosition() == coordinate.getX()){
                row = i;
                break;
            }
        }
        if(row == -1) return Optional.empty();
        for(int i = 0; i< board.get(row).size(); i++) {
            if(board.get(row).get(i).getCoordinate().getY() == coordinate.getY()) {
                index = i;
                break;
            }
        }
        if(index == -1) return Optional.empty();
        return Optional.of(board.get(row).get(index));
    }

    /**
     * Calculate the max distance from a coordinate, to a direction
     *
     * @param coordinate
     * @param direction
     * @param board
     * @return number of plot in the direction, from the coordinate
     */
    public int calcDistanceMax(Coordinate coordinate, Direction direction, Board board) {
        int distance = 0;
        while(findPlot(newCoordinateWithADirection(coordinate, distance+1, direction), board).isPresent()) {
            distance++;
        }
        return distance;
    }

    /**
     * Get a list of plot in a direction, from a coordinate
     * @param coordinate
     * @param direction
     * @param board
     * @return a list of plot
     */
    public List<Plot> getListPlotInDirection(Coordinate coordinate, Direction direction, Board board) {
        List<Plot> plots = new ArrayList<>();
        int distance = 0;
        Optional<Plot> optplot = findPlot(newCoordinateWithADirection(coordinate, distance, direction), board);
        while(optplot.isPresent()) {
            plots.add(optplot.get());
            distance++;
            optplot = findPlot(newCoordinateWithADirection(coordinate, distance, direction), board);
        }
        return plots;
    }

    /**
     * Set a zone of PlotColor, in the same color as target
     *
     * @param target  is the plot targeted
     * @param board
     * @return a set of PlotColor, who's in the same color as the target (target is in the set)
     */
    public HashSet<PlotColor> coloredZone(Plot target, Board board) {
        HashSet<PlotColor> plotsAdj = new HashSet<PlotColor>();
        if(target instanceof PlotColor targetColor){
            plotsAdj.add(targetColor);
            findAdjoiningColor(targetColor, plotsAdj, board);
        }
        return plotsAdj;
    }

    /**
     * Recurrence. To get all plots in the same color beside the target
     *
     * @param target   is the plot targeted
     * @param plotsAdj is the set of plotsAdj
     * @param board
     */
    public void findAdjoiningColor(PlotColor target, HashSet<PlotColor> plotsAdj, Board board) {
        List<Direction> directions = Direction.allDirection();
        for (Direction d: directions) {
            Optional<Plot> tmp = findPlot(newCoordinateWithADirection(target.getCoordinate(), 1, d), board);
            if(tmp.isPresent()) {
                Plot plot = tmp.get();
                if(plot instanceof PlotColor plotColor) {
                    if(!plotsAdj.contains(plotColor)) {
                        if(plotColor.isIrrigated() && plotColor.getColor() == target.getColor()) {
                            plotsAdj.add(plotColor);
                            findAdjoiningColor(plotColor, plotsAdj, board);
                        }
                    }
                }
            }
        }
    }

    /** Mainly used when an irrigation is posed, it used to give the irrigable sides enable by the use of the irrigableCoordinates
     *
     * @param irrigableCoordinates is the position irrigated
     * @param board
     * @return a set of irrigableCoordinates
     */
    public Set<IrrigableCoordinates> findNewIrrigable(IrrigableCoordinates irrigableCoordinates, Board board) {
        Set<IrrigableCoordinates> setIrrig = new HashSet<IrrigableCoordinates>();
        Map<Coordinate, Direction> coosDir = irrigableCoordinates.getCoordinatesWithDirectionIrrigable();
        for(Coordinate coo : coosDir.keySet()) {

            Direction direction = coosDir.get(coo);

            List<Direction> dirs = new ArrayList<>();
            dirs.add(direction.nextDirection());
            dirs.add(direction.previousDirection());

            for(Direction d : dirs) {
                Optional<Plot> plot = findPlot(coo, board);

                Coordinate cooNext = newCoordinateWithADirection(coo, 1, d);
                Optional<Plot> plotAdj = findPlot(cooNext, board);

                if(plot.isPresent() && plot.get().getIrrigation().contains(d)) continue;
                if(plotAdj.isPresent() && plotAdj.get().getIrrigation().contains(d.oppositeDirection())) continue;
                setIrrig.add(new IrrigableCoordinates(coo, d, newCoordinateWithADirection(coo, 1, d)));
            }
        }
        return setIrrig;
    }

    /** When a new plot is added on the board, we need to check if adjoining plots are irrigated
     *
     * @param coordinate of the plot
     * @param board
     * @return the list of side irrigated
     */
    public Set<Direction> findAdjoiningIrrigated(Coordinate coordinate, Board board) {
        Set<Direction> res = new HashSet<>();
        for(Direction d : Direction.values()) {
            Optional<Plot> optPlot = findPlot(newCoordinateWithADirection(coordinate, 1, d), board);
            if(optPlot.isEmpty()) continue;
            if(optPlot.get().getIrrigation().contains(d.oppositeDirection())) res.add(d);
        }
        return res;
    }

    /**
     * Find all plots that are not irrigated of the board
     * @param board
     * @return a list of PlotColor
     */
    public List<PlotColor> findNoIrrigatePlot(Board board) {
        List<PlotColor> plots = new ArrayList<>();
        for (Plot plot : board.allPlots()) {
            if(!(plot instanceof PlotColor))continue;
            if(!plot.isIrrigated()) plots.add((PlotColor) plot);
        }
        return plots;
    }

    /**
     * Find neighbour plots of a coordinate on the board
     * @param coordinate
     * @param board
     * @return a list of neighbour plots
     */
    public List<Plot> findNeighbourPlot(Coordinate coordinate, Board board) {
        List<Plot> res = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            Optional<Plot> optPlot = findPlot(newCoordinateWithADirection(coordinate, 1, dir), board);
            if(optPlot.isEmpty()) continue;
            res.add(optPlot.get());
        }
        return res;
    }

    /**
     * Find all plots of a color on the board
     * @param color
     * @param board
     * @return a list of PlotColor
     */
    public List<PlotColor> findMatchesColorPlots(Color color, Board board) {
        List<PlotColor> res = new ArrayList<>();
        for(Plot plot : board.allPlots()) {
            if(plot.getClass() == PlotColor.class) {
                PlotColor pc = (PlotColor) plot;
                if(pc.getColor() == color) res.add(pc);
            }
        }
        return res;
    }

}
