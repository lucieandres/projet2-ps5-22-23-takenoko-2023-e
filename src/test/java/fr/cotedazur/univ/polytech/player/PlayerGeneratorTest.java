package fr.cotedazur.univ.polytech.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerGeneratorTest {

    @Test
    public void testGenerateRandom() {
        PlayerGenerator playerGenerator = new PlayerGenerator();
        assertEquals(4, playerGenerator.generateBot(4).size());
    }
}
