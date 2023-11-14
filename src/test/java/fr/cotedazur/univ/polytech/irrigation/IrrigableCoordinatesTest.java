package fr.cotedazur.univ.polytech.irrigation;

import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinates;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IrrigableCoordinatesTest {
    private IrrigableCoordinates irrigableCoordinate;
    private Coordinate coo1;
    private Coordinate coo2;
    private Direction dir;

    @BeforeEach
    public void before() {
        coo1 = new Coordinate(0, 0);
        coo2 = new Coordinate(0, 1);
        dir = Direction.X;
        irrigableCoordinate = new IrrigableCoordinates(coo1, dir, coo2);
    }

    @Test
    public void IrrigableSideTest() {
        assertEquals(irrigableCoordinate.getMainSide(), irrigableCoordinate.getSideByCoordinate(coo1));
        assertEquals(irrigableCoordinate.getMainSide().oppositeDirection(), irrigableCoordinate.getSideByCoordinate(coo2));

        Coordinate cooNotInIrrigableCoordinate = new Coordinate(1, 1);
        assertNull(irrigableCoordinate.getSideByCoordinate(cooNotInIrrigableCoordinate));


        assertEquals(irrigableCoordinate.getCoordinates(), List.of(coo1, coo2));
    }

}
