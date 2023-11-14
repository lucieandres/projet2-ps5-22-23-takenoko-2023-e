package fr.cotedazur.univ.polytech.object;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImprovementGeneratorTest {

    @Test
    public void testGenerateImprovement() {
        ImprovementGenerator improvementGenerator = new ImprovementGenerator();
        LinkedList<Improvement> improvements = improvementGenerator.generateImprovement(3);
        assertEquals(9, improvements.size());
    }
}
