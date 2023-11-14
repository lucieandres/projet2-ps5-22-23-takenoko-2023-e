package fr.cotedazur.univ.polytech.object;

import fr.cotedazur.univ.polytech.utilities.Color;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BambooGeneratorTest {

    @Test
    void testBambooGenerator() {
        BambooGenerator bambooGenerator = new BambooGenerator();
        Map<Color, LinkedList<Bamboo>> bamboos = bambooGenerator.generateMapBamboos();
        assertEquals(36, bamboos.get(Color.GREEN).size());
        assertEquals(30, bamboos.get(Color.PINK).size());
        assertEquals(24, bamboos.get(Color.YELLOW).size());
    }
}
