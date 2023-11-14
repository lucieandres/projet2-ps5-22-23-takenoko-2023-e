package fr.cotedazur.univ.polytech.plot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlotColorGeneratorTest {

    @Test
    public void testPlotColorGenerator() {
        PlotColorGenerator plotColorGenerator = new PlotColorGenerator();
        assertEquals(9, plotColorGenerator.generatePlotList(3).size());
    }
}
