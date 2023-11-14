package fr.cotedazur.univ.polytech.board;

import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinates;
import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinatesSet;
import fr.cotedazur.univ.polytech.plot.Etang;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.plot.PlotUtilitiesTools;
import fr.cotedazur.univ.polytech.utilities.*;

import java.util.ArrayList;

/**
 * BoardGenerator is a class that generate a board with a given etang
 */
public class BoardGenerator {
    Etang etang;
    Board board;
    private static PlotUtilitiesTools TOOLS = new PlotUtilitiesTools();

    /**
     * Constructor of BoardGenerator
     * @param etang the etang of the board
     */
    public BoardGenerator(Etang etang) {
        this.etang = etang;

    }

    /**
     * Generate a board with a given etang and a given number of plots
     * @return the board generated
     */
    public Board generateFixed() {

        Row row1 = new Row();

        row1.add(new PlotColor(new Coordinate(1, -1), Color.PINK,1));
        row1.add(new PlotColor(new Coordinate(1, 0), Color.PINK,2));

        Row row0 = new Row();

        row0.add(new PlotColor(new Coordinate(0, -1), Color.GREEN,1));
        row0.add(etang);
        row0.add(new PlotColor(new Coordinate(0, 1), Color.GREEN,2));

        Row rowm1 = new Row();

        rowm1.add(new PlotColor(new Coordinate(-1, 1), Color.YELLOW,1));
        rowm1.add(new PlotColor(new Coordinate(-1, 0), Color.YELLOW,2));

        board = new Board();

        board.add(rowm1);
        board.add(row0);
        board.add(row1);

        return board;
    }

    /**
     * Generate a board with a given etang and a given number of plots and with irrigable coordinates
     * @return the board generated
     */
    public Board generateFixedWithIrrigable() {

        board = generateEmptyBoard();
        PlotColor pc1 = new PlotColor(new Coordinate(-1, 0), Color.PINK,0);
        pc1.addIrrigation(Direction.Y);
        board.push(pc1);

        pc1 = new PlotColor(new Coordinate(-1, 1), Color.PINK,0);
        pc1.addIrrigation(Direction.MZ);
        board.push(pc1);

        pc1 = new PlotColor(new Coordinate(-1, -1), Color.PINK,0);
        board.push(pc1);

        pc1 = new PlotColor(new Coordinate(0, -1), Color.GREEN,0);
        pc1.addIrrigation(Direction.X);
        board.push(pc1);

        pc1 = new PlotColor(new Coordinate(0, 1), Color.GREEN,0);
        pc1.addIrrigation(Direction.MX);
        board.push(pc1);

        pc1 = new PlotColor(new Coordinate(1, -1), Color.YELLOW,0);
        pc1.addIrrigation(Direction.Z);
        board.push(pc1);

        pc1 = new PlotColor(new Coordinate(1, 0), Color.YELLOW,0);
        pc1.addIrrigation(Direction.MY);
        board.push(pc1);

        pc1 = new PlotColor(new Coordinate(1, 1), Color.YELLOW,0);
        board.push(pc1);

        board.setIrrigableCoordinates(generateIrrigableCoordinatesForEmptyBoard());
        return board;
    }

    /**
     * Generate an empty board with a given etang
     * @return the board generated
     */
    public Board generateEmptyBoard() {
        Row row0 = new Row();
        row0.add(etang);

        ArrayList<Row> rows = new ArrayList<Row>();
        rows.add(row0);
        board = new Board(rows);
        board.setIrrigableCoordinates(generateIrrigableCoordinatesForEmptyBoard());

        return board;
    }

    /**
     * Generate the poseable place plot for an empty board
     * @return poseable place plot
     */
    public PoseablePlacePlot generatePoseablePlacePlotForEmptyBoard() {
        PoseablePlacePlot pos = new PoseablePlacePlot();

        pos.add(new Coordinate(0, -1));
        pos.add(new Coordinate(0, 1));
        pos.add(new Coordinate(-1, 0));
        pos.add(new Coordinate(1, 0));
        pos.add(new Coordinate(1, -1));
        pos.add(new Coordinate(-1, 1));

        return pos;

    }

    /**
     * Generate the irrigable coordinates for an empty board
     * @return irrigable coordinates
     */
    public IrrigableCoordinatesSet generateIrrigableCoordinatesForEmptyBoard() {
        IrrigableCoordinatesSet res = new IrrigableCoordinatesSet();
        for (Direction d : Direction.allDirection()) {
            res.addAll(TOOLS.findNewIrrigable(new IrrigableCoordinates(etang.getCoordinate(), d, TOOLS.newCoordinateWithADirection(etang.getCoordinate(), 1, d)), board));
        }
        return res;

    }
}
