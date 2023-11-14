package fr.cotedazur.univ.polytech.plot;

import fr.cotedazur.univ.polytech.utilities.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlotComparatorTest {

    @Test
    public void testCompare() {
        PlotComparator plotComparator = new PlotComparator();
        Plot plot1 = new Plot(new Coordinate(1, 1));
        Plot plot2 = new Plot(new Coordinate(1, 1));
        assertEquals(0, plotComparator.compare(plot1, plot2));
        plot2 = new Plot(new Coordinate(1, 2));
        assertEquals(-1, plotComparator.compare(plot1, plot2));
        plot2 = new Plot(new Coordinate(1, 0));
        assertEquals(1, plotComparator.compare(plot1, plot2));
        plot2 = new Plot(new Coordinate(2, 1));
        assertEquals(-1, plotComparator.compare(plot1, plot2));
        plot2 = new Plot(new Coordinate(0, 1));
        assertEquals(1, plotComparator.compare(plot1, plot2));
    }

}
