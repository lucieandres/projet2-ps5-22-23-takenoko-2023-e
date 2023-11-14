package fr.cotedazur.univ.polytech.card;

import org.junit.jupiter.api.Test;

public class CardEmperorTest {

    @Test
    public void testCardEmperor() {
        CardEmperor cardEmperor = new CardEmperor();
        cardEmperor.setPlayed(true);
        assert(cardEmperor.getPlayed());
        assert(cardEmperor.getScore() == 2);
        assert(cardEmperor.calculScore() == 2);
    }
}
