package fr.cotedazur.univ.polytech.utilities;


import fr.cotedazur.univ.polytech.utilities.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectionTest {

    Direction direction;

    @BeforeEach
    public void before() {
        direction = Direction.X;
    }

    @Test
    public void testDirectionNext() {
        direction = direction.nextDirection();
        assertEquals(Direction.Y, direction);

        direction = direction.nextDirection();
        assertEquals(Direction.MZ, direction);

        direction = direction.nextDirection();
        assertEquals(Direction.MX, direction);

        direction = direction.nextDirection();
        assertEquals(Direction.MY, direction);

        direction = direction.nextDirection();
        assertEquals(Direction.Z, direction);

        direction = direction.nextDirection();
        assertEquals(Direction.X, direction);
    }

    @Test
    public void testDirectionPrevious() {
        direction = direction.previousDirection();
        assertEquals(Direction.Z, direction);

        direction = direction.previousDirection();
        assertEquals(Direction.MY, direction);

        direction = direction.previousDirection();
        assertEquals(Direction.MX, direction);

        direction = direction.previousDirection();
        assertEquals(Direction.MZ, direction);

        direction = direction.previousDirection();
        assertEquals(Direction.Y, direction);

        direction = direction.previousDirection();
        assertEquals(Direction.X, direction);
    }
}
