package fr.cotedazur.univ.polytech.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinateTest {

    @Test
    public void testCoordinate(){
        Coordinate coordinate = new Coordinate(1, 2);
        coordinate.setCoordonate(3, 4);
        assertEquals(3, coordinate.getX());
        assertEquals(4, coordinate.getY());
        coordinate.setX(5);
        coordinate.setY(6);
        assertEquals(5, coordinate.getX());
        assertEquals(6, coordinate.getY());
    }
}
