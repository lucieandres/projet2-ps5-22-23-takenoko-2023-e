package fr.cotedazur.univ.polytech.object;

import fr.cotedazur.univ.polytech.utilities.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BambooTest {

    @Test
    void testBamboo() {
        Bamboo bamboo = new Bamboo(Color.GREEN);
        assertEquals(Color.GREEN, bamboo.getColor());
        assertEquals("Bamboo green", bamboo.toString());
    }
}
