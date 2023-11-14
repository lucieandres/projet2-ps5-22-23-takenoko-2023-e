package fr.cotedazur.univ.polytech.card;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardGeneratorTest {

    @Test
    void testGeneratorCard() {
        CardGenerator cardGenerator = new CardGenerator();
        ArrayList<CardObjectiveGardener> gardeners = new ArrayList<>();
        ArrayList<CardObjectivePanda> pandas = new ArrayList<>();
        ArrayList<CardObjectivePlot> plots = new ArrayList<>();
        cardGenerator.generatorCard(3, gardeners, pandas, plots);
        assertEquals(3, gardeners.size());
        assertEquals(3, pandas.size());
        assertEquals(3, plots.size());
    }
}
