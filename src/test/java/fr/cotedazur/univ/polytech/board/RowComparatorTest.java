package fr.cotedazur.univ.polytech.board;

import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.plot.PlotColorGenerator;
import fr.cotedazur.univ.polytech.startingpoint.Referee;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RowComparatorTest {

    @Test
    public void testCompare() {
        ArrayList<Plot> list1 = new ArrayList<>();
        list1.add(new PlotColor(new Coordinate(0, 0), Color.PINK));
        list1.add(new PlotColor(new Coordinate(0, 1), Color.PINK));
        list1.add(new PlotColor(new Coordinate(0, 2), Color.PINK));
        Row row1 = new Row(list1);

        ArrayList<Plot> list2 = new ArrayList<>();
        list2.add(new PlotColor(new Coordinate(3, 4), Color.PINK));
        list2.add(new PlotColor(new Coordinate(3, 5), Color.PINK));
        list2.add(new PlotColor(new Coordinate(3, 6), Color.PINK));
        Row row2 = new Row(list2);

        RowComparator rowComparator = new RowComparator();
        assertEquals(-3, rowComparator.compare(row1, row2));
    }
}
