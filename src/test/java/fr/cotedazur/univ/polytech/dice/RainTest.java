package fr.cotedazur.univ.polytech.dice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RainTest {
        @Test
        public void testRain() {
            Rain rain = new Rain();
            rain.action();
        }
}
