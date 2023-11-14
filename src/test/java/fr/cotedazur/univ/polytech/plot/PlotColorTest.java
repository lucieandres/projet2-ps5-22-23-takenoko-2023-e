package fr.cotedazur.univ.polytech.plot;

import fr.cotedazur.univ.polytech.object.Bamboo;
import fr.cotedazur.univ.polytech.object.Improvement;
import fr.cotedazur.univ.polytech.object.ImprovementType;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlotColorTest {

    @Test
    public void testPlotColor() {
        PlotColor plotColor;
        do {
            plotColor = new PlotColor(new Coordinate(1, 1), Color.PINK);
        } while (plotColor.getImprovement().isEmpty()
                || (plotColor.getImprovement().isPresent() && plotColor.getImprovement().get().getType() != ImprovementType.ENCLOSURE));
        assertEquals(Color.PINK, plotColor.getColor());
        assertEquals(new Coordinate(1, 1), plotColor.getCoordinate());
        plotColor.setCoordinate(new Coordinate(0, 1));
        assertEquals(new Coordinate(0, 1), plotColor.getCoordinate());
        assertEquals(0, plotColor.getSizeBambooList());
        assertEquals(0, plotColor.getBambooList().size());
        assertEquals(plotColor.getImprovement().get().getType(), ImprovementType.ENCLOSURE);
//        assertEquals("Plot with color pink and Amenagement enclos and bamboo list : Bamboo pink  at x: 0, y: 1", plotColor.toString());

        plotColor.addBamboo(new Bamboo(Color.PINK));
        assertEquals(1, plotColor.getSizeBambooList());
        plotColor.removeBamboo(plotColor.getBambooList().get(0));
        assertEquals(0, plotColor.getSizeBambooList());
    }

    @Test
    public void testPlotColorWithBamboo() {
        PlotColor plotColor;
        do {
            plotColor = new PlotColor(new Coordinate(1, 1), Color.PINK, 3);
        } while (plotColor.getImprovement().isEmpty()
                || (plotColor.getImprovement().isPresent() && plotColor.getImprovement().get().getType() != ImprovementType.ENCLOSURE));
        assertEquals(Color.PINK, plotColor.getColor());
        assertEquals(new Coordinate(1, 1), plotColor.getCoordinate());
        assertEquals(3, plotColor.getSizeBambooList());
        assertEquals(3, plotColor.getBambooList().size());
        assertEquals(plotColor.getImprovement().get().getType(), ImprovementType.ENCLOSURE);
//        assertEquals("Plot with color pink and Amenagement enclos and bamboo list : Bamboo pink Bamboo pink Bamboo pink  at x: 1, y: 1", plotColor.toString());

        plotColor.addBamboo(new Bamboo(Color.PINK));
        assertEquals(4, plotColor.getSizeBambooList());
        plotColor.removeBamboo(plotColor.getBambooList().get(0));
        assertEquals(3, plotColor.getSizeBambooList());
    }

    @Test
    public void testIsIrrigated() {
        PlotColor plotColor;
        do {
            plotColor = new PlotColor(new Coordinate(1, 1), Color.PINK);
        } while (plotColor.getImprovement().isEmpty()
                || (plotColor.getImprovement().isPresent() && plotColor.getImprovement().get().getType() == ImprovementType.WATERSHED));
        assertFalse(plotColor.isIrrigated());
        plotColor.setImprovement(new Improvement(ImprovementType.WATERSHED));
        assertTrue(plotColor.isIrrigated());

        PlotColor plotColor3;
        do {
            plotColor3 = new PlotColor(new Coordinate(-1, 1), Color.PINK);
        } while (plotColor3.getImprovement().isEmpty() || plotColor3.getImprovement().get().getType() != ImprovementType.WATERSHED);
        assertTrue(plotColor3.isIrrigated());
    }

    @Test
    public void testImprovementIsEnclosure() {
        PlotColor plotColor;
        do {
            plotColor = new PlotColor(new Coordinate(1, 1), Color.PINK);
        } while (plotColor.getImprovement().isPresent());
        assertFalse(plotColor.improvementIsEnclosure());
        plotColor.setImprovement(new Improvement(ImprovementType.ENCLOSURE));
        assertTrue(plotColor.improvementIsEnclosure());
    }

    @Test
    public void testToString() {
        PlotColor plotColor;
        do {
            plotColor = new PlotColor(new Coordinate(1, 1), Color.PINK);
        } while (plotColor.getImprovement().isEmpty()
                || (plotColor.getImprovement().isPresent() && plotColor.getImprovement().get().getType() != ImprovementType.FERTILIZER));

        assertEquals("Plot with color pink and Optional[Amenagement engrais] at x: 1, y: 1", plotColor.toString());
    }
}
