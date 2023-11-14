package fr.cotedazur.univ.polytech.board;

import fr.cotedazur.univ.polytech.plot.Etang;
import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;
import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

class BoardTest {
    /**
     * A Board is obligatorily ordinate /!\
     * A Row is obligatorily ordinate /!\
     */
    Board emptyBoard, board;

    Row rowm1, row0, row1;
    Plot plot, plotInMiddleRow, plotForNewStartedRow, plotForNewEndedRow;

    @BeforeEach
    void setUp() {

        ArrayList<Row> rows = new ArrayList<Row>();

        ArrayList<Plot> plots2 = new ArrayList<Plot>();
        plots2.add(new PlotColor(new Coordinate(-1, 1), Color.PINK));
        plots2.add(new PlotColor(new Coordinate(-1, -1), Color.PINK));
        plots2.add(new PlotColor(new Coordinate(-1, 0), Color.PINK));

        rowm1 = new Row(plots2);

        ArrayList<Plot> plots = new ArrayList<Plot>();
        plots.add(new PlotColor(new Coordinate(0, 2), Color.PINK));
        plots.add(new Plot(new Coordinate(0, 0)));
        plots.add(new PlotColor(new Coordinate(0, -1), Color.PINK));

        row0 = new Row(plots);

        ArrayList<Plot> plots3 = new ArrayList<Plot>();
        plots3.add(new PlotColor(new Coordinate(1, 0), Color.PINK));
        plots3.add(new PlotColor(new Coordinate(1, -1), Color.PINK));
        plots3.add(new PlotColor(new Coordinate(1, 2), Color.PINK));
        plots3.add(new PlotColor(new Coordinate(1, 1), Color.PINK));


        row1 = new Row(plots3);

        rows.add(rowm1);
        rows.add(row0);
        rows.add(row1);


        board = (new Board(rows));

        plot = new PlotColor(new Coordinate(-1, -2), Color.PINK);
        plotInMiddleRow = new PlotColor(new Coordinate(0, 1), Color.PINK);
        plotForNewEndedRow = new PlotColor(new Coordinate(2, 1), Color.PINK);
        plotForNewStartedRow = new PlotColor(new Coordinate(-2, 0), Color.PINK);

        emptyBoard = (new BoardGenerator(new Etang())).generateEmptyBoard();
    }

    @Test
    void testSimplePush() {
       board.push(plot);
        assertEquals(board.get(0).get(0), plot);
    }

    @Test
    void testPushInAMiddleRow() {
        board.push(plotInMiddleRow);
        assertEquals(board.get(1).get(2), plotInMiddleRow);
    }

    @Test
    void testPushInSmallerRowThanCurrently() {
        board.push(plotForNewStartedRow);
        assertEquals(board.get(0).get(0), plotForNewStartedRow);
    }

    @Test
    void testPushInBiggerRowThanCurrently() {
        board.push(plotForNewEndedRow);
        assertEquals(board.get(3).get(0), plotForNewEndedRow);
    }

    @Test
    void testIrrigableCoordinatesWithEmptySetUp() {
        assertEquals(6, emptyBoard.getIrrigableCoordinates().size());
        assertEquals(0, emptyBoard.getIrrigablePlotWDirections().size());
    }

    @Test
    void testIrrigableCoordinates() {
        assertEquals(6, emptyBoard.getIrrigableCoordinates().size());
        Coordinate cooTest = new Coordinate(0, 1);
        emptyBoard.push(new Plot(cooTest));
        assertEquals(1, emptyBoard.getIrrigablePlotWDirections().size());
        assertEquals(2, emptyBoard.getIrrigablePlotWDirections().get(cooTest).size());
        Coordinate cooTest2 = new Coordinate(-1, 1);
        emptyBoard.push(new Plot(cooTest2));
        assertEquals(2, emptyBoard.getIrrigablePlotWDirections().size());
        assertEquals(2, emptyBoard.getIrrigablePlotWDirections().get(cooTest2).size());
    }

    @Test
    void testAddRow() {
        ArrayList<Row> rows = new ArrayList<Row>();

        ArrayList<Plot> plots2 = new ArrayList<Plot>();
        plots2.add(new PlotColor(new Coordinate(-1, 1), Color.PINK));
        plots2.add(new PlotColor(new Coordinate(-1, -1), Color.PINK));
        plots2.add(new PlotColor(new Coordinate(-1, 0), Color.PINK));

        rowm1 = new Row(plots2);

        ArrayList<Plot> plots = new ArrayList<Plot>();
        plots.add(new PlotColor(new Coordinate(0, 2), Color.PINK));
        plots.add(new Plot(new Coordinate(0, 0)));
        plots.add(new PlotColor(new Coordinate(0, -1), Color.PINK));

        row0 = new Row(plots);

        ArrayList<Plot> plots3 = new ArrayList<Plot>();
        plots3.add(new PlotColor(new Coordinate(1, 0), Color.PINK));
        plots3.add(new PlotColor(new Coordinate(1, -1), Color.PINK));
        plots3.add(new PlotColor(new Coordinate(1, 2), Color.PINK));
        plots3.add(new PlotColor(new Coordinate(1, 1), Color.PINK));


        row1 = new Row(plots3);

        rows.add(rowm1);
        rows.add(row0);
        rows.add(row1);


        board = (new Board(rows));

        Plot plot = new PlotColor(new Coordinate(-1, 1), Color.PINK);
        board.addRow(plot, 0);
    	assertEquals(board.get(0).get(0), plot);
    }

    @Test
    void testRemoveAddIrrigeableCoordinates(){
        board = (new BoardGenerator(new Etang())).generateFixedWithIrrigable();
        assertEquals(6, board.getIrrigableCoordinates().size());
        board.removeIrrigableCoordinates(new IrrigableCoordinates(new Coordinate(0, 1), Direction.MX, new Coordinate(1, 1)));
    }

}
