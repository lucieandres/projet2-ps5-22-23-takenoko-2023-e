package fr.cotedazur.univ.polytech.plot;

import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PlotTest {

    @Test
    public void testPlot() {
        Plot plot = new Plot(new Coordinate(1, 1));
        assertEquals(1, plot.getCoordinate().getX());
        assertEquals(1, plot.getCoordinate().getY());
        assertEquals(0, plot.getIrrigation().size());
        plot.addIrrigation(Direction.X);
        assertEquals(1, plot.getIrrigation().size());
        plot.addAllIrrigation(Set.of(Direction.Y, Direction.Z));
        assertEquals(3, plot.getIrrigation().size());
        plot.setAllIrrigation(Set.of(Direction.MY, Direction.X));
        assertEquals(2, plot.getIrrigation().size());

        Plot plot2 = new Plot(new Coordinate(1, 0));
        assertFalse(plot.isBiggerInRow(plot2));
        assertTrue(plot.isSmallerInRow(plot2));

        assertTrue(plot.isIrrigated());
        assertEquals("Plot at x: 1, y: 1", plot.toString());
    }
}
