package fr.cotedazur.univ.polytech.board;

import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RowTest {
    /**
     * A Row is obligatorily ordinate /!\
     */
    Row rowEmpty, row;
    Plot plot, plotSmaller, plotBigger;

    @BeforeEach
    public void setUp() {
        rowEmpty = new Row();

        ArrayList<Plot> plots = new ArrayList<Plot>();
        plots.add(new PlotColor(new Coordinate(0, 2), Color.PINK));
        plots.add(new PlotColor(new Coordinate(0, 0), Color.PINK));
        plots.add(new PlotColor(new Coordinate(0, -1), Color.PINK));
        plots.add(new PlotColor(new Coordinate(0, 4), Color.PINK));

        row = new Row(plots);


        plot = new PlotColor(new Coordinate(0, 1), Color.PINK);
        plotSmaller = new PlotColor(new Coordinate(0, -3), Color.PINK);
        plotBigger = new PlotColor(new Coordinate(0, 6), Color.PINK);
    }


    @Test
    void testPushAPlotInEmptyRow() {
        rowEmpty.push(plot);
        assertEquals(rowEmpty.get(0), plot);
    }

    @Test
    void testPushAPlotSmaller() {
        row.push(plotSmaller);
        assertEquals(row.get(0), plotSmaller);
    }

    @Test
    void testPushAPlotBigger() {
        row.push(plotBigger);
        assertEquals(row.get(row.size()-1), plotBigger);
    }

    @Test
    void testPushAPlotInRow() {
        row.push(plot);
        assertEquals(row.get(2), plot);
    }

    @Test
    void testFirstPostionInRow() {
        assertEquals(row.getFirstPlotPosition(), -1);
    }

    @Test
    void testLarstPostionInRow() {
        assertEquals(row.getLastPlotPosition(), 4);
    }

    @Test
    void testPostionRow() {
        assertEquals(row.getRowPosition(), 0);
    }
}
