package fr.cotedazur.univ.polytech.plot;

import fr.cotedazur.univ.polytech.utilities.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EtangTest {

    @Test
    public void testEtang() {
        Etang etang = new Etang();
        assertEquals(0, etang.getCoordinate().getX());
        assertEquals(0, etang.getCoordinate().getY());
        assertEquals(6, etang.getIrrigation().size());
        assertTrue(etang.getIrrigation().contains(Direction.X));
        assertTrue(etang.getIrrigation().contains(Direction.Y));
        assertTrue(etang.getIrrigation().contains(Direction.Z));
        assertTrue(etang.getIrrigation().contains(Direction.MX));
        assertTrue(etang.getIrrigation().contains(Direction.MY));
        assertTrue(etang.getIrrigation().contains(Direction.MZ));
        assertEquals("- Etang -", etang.toString());
    }
}
