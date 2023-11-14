package fr.cotedazur.univ.polytech.card;

import java.util.List;

/**
 * Generate the cards
 */
public class CardGenerator {

    /**
     * Generate the cards for the game and add them to the list of cards
     * @param CARDOBJECTIVEPERTYPE
     * @param gardeners
     * @param pandas
     * @param plots
     */
    public void generatorCard(int CARDOBJECTIVEPERTYPE, List<CardObjectiveGardener> gardeners, List<CardObjectivePanda> pandas, List<CardObjectivePlot> plots) {
        for(int i = 0; i<CARDOBJECTIVEPERTYPE; i++) {
            gardeners.add(new CardObjectiveGardener());
            pandas.add(new CardObjectivePanda());
            plots.add(new CardObjectivePlot());
        }
    }
}
