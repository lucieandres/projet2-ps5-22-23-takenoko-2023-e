package fr.cotedazur.univ.polytech.card;

import fr.cotedazur.univ.polytech.utilities.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CardObjectivePandaTest {

    CardObjectivePanda cardObjPanda;

    @BeforeEach
    public void setUp() {
        do {
            cardObjPanda = new CardObjectivePanda();
        }while(!(cardObjPanda.getNumberOfBambou()==2 && Arrays.stream(cardObjPanda.getTableauBambou()).toList().get(0)==Color.PINK && Arrays.stream(cardObjPanda.getTableauBambou()).toList().get(1)==Color.GREEN && cardObjPanda.getScore()==3));
    }

    @Test
    void CardObjectiveGardenerTest(){
        assertEquals(2, cardObjPanda.getNumberOfBambou());
        assertEquals(3, cardObjPanda.getScore());
        assertEquals(Color.PINK, Arrays.stream(cardObjPanda.getTableauBambou()).toList().get(0));
        assertEquals(Color.GREEN, Arrays.stream(cardObjPanda.getTableauBambou()).toList().get(1));
        cardObjPanda.printArrayBambouCard();
    }

    @Test
    void toStringTest(){
        assertEquals("Il faut 2 bambous avec les couleurs pink green ,cette carte rapporte 3 points", cardObjPanda.toString());
    }

}
