package fr.cotedazur.univ.polytech.dice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {

    @Test
    public void testDice() {
        Dice dice = new Dice();
        dice.roll();
        assertTrue(dice.getValue() >= 1 && dice.getValue() <= 6);
        assertTrue(dice.getFace() != null);
    }
}
