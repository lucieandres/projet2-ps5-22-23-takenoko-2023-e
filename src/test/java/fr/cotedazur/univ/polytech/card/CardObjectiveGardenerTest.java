package fr.cotedazur.univ.polytech.card;

import fr.cotedazur.univ.polytech.utilities.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardObjectiveGardenerTest {

    CardObjectiveGardener cardObjGar;

    @BeforeEach
    public void setUp() {
        do {
            cardObjGar = new CardObjectiveGardener();
        } while(!(cardObjGar.getNombreSectionBambou()==3 && cardObjGar.getCouleurBambou()==Color.PINK && !cardObjGar.isImprovement() && cardObjGar.getScore()==3));
    }

    @Test
    void CardObjectiveGardenerTest(){
        assertEquals(3, cardObjGar.getNombreSectionBambou());
        assertEquals(Color.PINK, cardObjGar.getCouleurBambou());
        assertFalse(cardObjGar.isImprovement());
        assertEquals(3, cardObjGar.getScore());
    }

    @Test
    void toStringTest(){
        assertEquals("Il faut 3 sections de bambous avec la couleur pink sans aménagement,cette carte rapporte 3 points", cardObjGar.toString());
    }

}
