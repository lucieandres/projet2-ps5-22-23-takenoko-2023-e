package fr.cotedazur.univ.polytech.object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImprovementTypeTest {

    @Test
    public void testImprovementType() {
        ImprovementType improvementType = ImprovementType.ENCLOSURE;
        assertEquals(ImprovementType.ENCLOSURE, improvementType);

        improvementType = ImprovementType.FERTILIZER;
        assertEquals(ImprovementType.FERTILIZER, improvementType);

        improvementType = ImprovementType.WATERSHED;
        assertEquals(ImprovementType.WATERSHED, improvementType);
    }
}
