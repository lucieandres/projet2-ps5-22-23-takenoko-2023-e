package fr.cotedazur.univ.polytech.dice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WindTest {
    @Test
    public void testWind() {
        Wind wind = new Wind();
        wind.action();
    }
}
