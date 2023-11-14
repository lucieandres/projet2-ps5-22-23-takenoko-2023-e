package fr.cotedazur.univ.polytech.dice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterrogationTest {

    @Test
    public void testInterrogation() {
        Interrogation interrogation = new Interrogation();
        interrogation.action();
    }
}
