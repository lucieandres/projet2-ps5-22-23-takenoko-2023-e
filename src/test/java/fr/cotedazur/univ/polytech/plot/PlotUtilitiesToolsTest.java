package fr.cotedazur.univ.polytech.plot;

import fr.cotedazur.univ.polytech.board.Board;
import fr.cotedazur.univ.polytech.board.BoardGenerator;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;
import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PlotUtilitiesToolsTest {

    Etang etang;
    Board board;
    private static PlotUtilitiesTools TOOLS = new PlotUtilitiesTools();

    @BeforeEach
    public void before() {
        etang = new Etang();
        board = (new BoardGenerator(etang)).generateFixed();
    }

    @Test
    public void ConstructorTest() {
        Coordinate coo1 = new Coordinate(1, 1);
        Coordinate coo2 = new Coordinate(1, 2);
        IrrigableCoordinates irrigableCoordinates = new IrrigableCoordinates(coo1, Direction.X, coo2);
        IrrigableCoordinates irrigableCoordinates2 = new IrrigableCoordinates(coo2, Direction.MX, coo1);

        assertEquals(coo1, irrigableCoordinates.getMainCoordinate());
        assertEquals(coo1, irrigableCoordinates2.getMainCoordinate());

        assertEquals(coo2, irrigableCoordinates.getSecondCoordinate());
        assertEquals(coo2, irrigableCoordinates2.getSecondCoordinate());

        assertEquals(irrigableCoordinates2.getMainSide(), irrigableCoordinates.getMainSide());

    }

    @Test
    public void findNewIrrigableTest() {
        Coordinate coo1 = new Coordinate(1, 1);
        Coordinate coo2 = new Coordinate(1, 2);
        IrrigableCoordinates irrigableCoordinates = new IrrigableCoordinates(coo1, Direction.X, coo2);

        List<IrrigableCoordinates> list = TOOLS.findNewIrrigable(irrigableCoordinates, board).stream().toList();

        assertEquals(4, list.size());


        irrigableCoordinates = new IrrigableCoordinates(board.get(1).get(1).getCoordinate(), Direction.X, board.get(1).get(2).getCoordinate());

        list = TOOLS.findNewIrrigable(irrigableCoordinates, board).stream().toList();
        assertEquals(2, list.size());

        board.get(1).get(2).addIrrigation(Direction.MY);

        irrigableCoordinates = new IrrigableCoordinates(board.get(1).get(1).getCoordinate(), Direction.X, board.get(1).get(2).getCoordinate());

        list = TOOLS.findNewIrrigable(irrigableCoordinates, board).stream().toList();
        //tous les bords de l'etang irrigués + un cote du plot le plus a droite de l'etang a sur MY une irrigation
        assertEquals(1, list.size());
        assertTrue(list.get(0).contains(board.get(1).get(2).getCoordinate()));
    }

    @Test
    public void irrigableIsEqualsTest() {
        Coordinate coo1 = new Coordinate(1, 1);
        Coordinate coo2 = new Coordinate(1, 2);
        IrrigableCoordinates irrigableCoordinates = new IrrigableCoordinates(coo1, Direction.X, coo2);
        IrrigableCoordinates irrigableCoordinates2 = new IrrigableCoordinates(coo2, Direction.MX, coo1);

        assertTrue(irrigableCoordinates.equals(irrigableCoordinates2));
    }

    @Test
    public void compareHashCodeTest() {
        Coordinate coo1 = new Coordinate(1, 1);
        Coordinate coo2 = new Coordinate(1, 2);
        Coordinate coo3 = new Coordinate(-1, -1);
        Coordinate coo4 = new Coordinate(-1, -2);
        IrrigableCoordinates irrigableCoordinates = new IrrigableCoordinates(coo1, Direction.X, coo2);
        IrrigableCoordinates irrigableCoordinates2 = new IrrigableCoordinates(coo2, Direction.MX, coo1);
        IrrigableCoordinates irrigableCoordinates3 = new IrrigableCoordinates(coo4, Direction.X, coo3);
        IrrigableCoordinates irrigableCoordinates4 = new IrrigableCoordinates(coo3, Direction.MX, coo4);

        assertEquals(irrigableCoordinates.hashCode(), irrigableCoordinates2.hashCode());
        assertEquals(irrigableCoordinates3.hashCode(), irrigableCoordinates4.hashCode());
        assertNotEquals(irrigableCoordinates2.hashCode(), irrigableCoordinates3.hashCode());
    }

    @Test
    public void testFindAjoiningIrrigated() {
        //TODO
    }

    @Test
    public void testFindNoIrrigatePlot() {
        //TODO
    }
}
