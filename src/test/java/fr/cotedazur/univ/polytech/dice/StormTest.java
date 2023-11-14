package fr.cotedazur.univ.polytech.dice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StormTest {
    @Test
    public void testStorm() {
        Storm storm = new Storm();
        storm.action();
    }
}
