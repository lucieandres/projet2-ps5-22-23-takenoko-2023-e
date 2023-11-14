package fr.cotedazur.univ.polytech.object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImprovementTest {

    @Test
    public void testToString() {
        Improvement improvement = new Improvement(ImprovementType.ENCLOSURE);
        assertEquals("Amenagement enclos", improvement.toString());

        improvement = new Improvement(ImprovementType.FERTILIZER);
        assertEquals("Amenagement engrais", improvement.toString());

        improvement = new Improvement(ImprovementType.WATERSHED);
        assertEquals("Amenagement bassin", improvement.toString());
    }
}
