package fr.cotedazur.univ.polytech.board;

import fr.cotedazur.univ.polytech.plot.Etang;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.startingpoint.Referee;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;
import fr.cotedazur.univ.polytech.player.PlayerGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PoseablePlacePlotTest {
    PoseablePlacePlot poseablePlacePlot;
    Referee referee;
    Etang etang;

    @BeforeEach
    public void beforeTest() {
        etang = new Etang();
        BoardGenerator boardGenerator = new BoardGenerator(etang);
        Board board = boardGenerator.generateEmptyBoard();
        poseablePlacePlot = boardGenerator.generatePoseablePlacePlotForEmptyBoard();
        referee = new Referee(board, null, null, null, null, null, new PlayerGenerator().generateBot(1), poseablePlacePlot, null,null, null);

    }

    @Test
    public void verifyGenerationTest() {
        assertTrue(poseablePlacePlot.contains(new Coordinate(1, 0)));
        assertTrue(poseablePlacePlot.contains(new Coordinate(-1, 0)));
        assertTrue(poseablePlacePlot.contains(new Coordinate(0, 1)));
        assertTrue(poseablePlacePlot.contains(new Coordinate(0, -1)));
        assertTrue(poseablePlacePlot.contains(new Coordinate(-1, 1)));
        assertTrue(poseablePlacePlot.contains(new Coordinate(1, -1)));
    }

    @Test
    public void calculatePoseableCoordinate() {
        PlotColor plotColor = new PlotColor(new Coordinate(1, 0), Color.PINK);
        poseablePlacePlot.findNewPoseablePlace(plotColor, referee);
        assertEquals(6, poseablePlacePlot.size());
    }

    @Test
    public void verifyGenerationCoo() {
        /*
                   x  -10  x  -11  x              My   z
                     \   /   \   /                  \ /
                 0-1   x  0 0  x  0 1           Mx -- -- x
                     /   \   /   \                  / \
                   x  1-1  x  1 0  x              Mz   y
         */


        // L'etang se trouve dans la direction MY de notre nouveau plot
        Coordinate cooPos = new Coordinate(1, 0);
        List<Coordinate> cooList = poseablePlacePlot.generateCoordinateFromAdjCoordinate(cooPos, etang.getCoordinate(), Direction.MY, referee);
        assertEquals(2, cooList.size());
        assertEquals(new Coordinate(1, -1), cooList.get(0));
        assertEquals(new Coordinate(0, 1), cooList.get(1));

        // L'etang se trouve dans la direction Y de notre nouveau plot
        cooPos = new Coordinate(-1, 0);
        cooList = poseablePlacePlot.generateCoordinateFromAdjCoordinate(cooPos, etang.getCoordinate(), Direction.Y, referee);
        assertEquals(2, cooList.size());
        assertEquals(new Coordinate(-1, 1), cooList.get(0));
        assertEquals(new Coordinate(0, -1), cooList.get(1));

        // L'etang se trouve dans la direction MX de notre nouveau plot
        cooPos = new Coordinate(0, 1);
        cooList = poseablePlacePlot.generateCoordinateFromAdjCoordinate(cooPos, etang.getCoordinate(), Direction.MX, referee);
        assertEquals(2, cooList.size());
        assertEquals(new Coordinate(-1, 1), cooList.get(0));
        assertEquals(new Coordinate(1, 0), cooList.get(1));

        // L'etang se trouve dans la direction X de notre nouveau plot
        cooPos = new Coordinate(0, -1);
        cooList = poseablePlacePlot.generateCoordinateFromAdjCoordinate(cooPos, etang.getCoordinate(), Direction.X, referee);
        assertEquals(2, cooList.size());
        assertEquals(new Coordinate(1, -1), cooList.get(0));
        assertEquals(new Coordinate(-1, 0), cooList.get(1));

        // L'etang se trouve dans la direction Z de notre nouveau plot
        cooPos = new Coordinate(1, -1);
        cooList = poseablePlacePlot.generateCoordinateFromAdjCoordinate(cooPos, etang.getCoordinate(), Direction.Z, referee);
        assertEquals(2, cooList.size());
        assertEquals(new Coordinate(1, 0), cooList.get(0));
        assertEquals(new Coordinate(0, -1), cooList.get(1));

        // L'etang se trouve dans la direction MZ de notre nouveau plot
        cooPos = new Coordinate(-1, 1);
        cooList = poseablePlacePlot.generateCoordinateFromAdjCoordinate(cooPos, etang.getCoordinate(), Direction.MZ, referee);
        assertEquals(2, cooList.size());
        assertEquals(new Coordinate(-1, 0), cooList.get(0));
        assertEquals(new Coordinate(0, 1), cooList.get(1));
    }

    @Test
    public void testToString() {
        assertEquals("Poseable = [ x: 0, y: -1| x: 1, y: 0| x: 0, y: 1| x: -1, y: 1| x: -1, y: 0| x: 1, y: -1|  ]", poseablePlacePlot.toString());
    }
}
