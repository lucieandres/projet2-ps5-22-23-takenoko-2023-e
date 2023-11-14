package fr.cotedazur.univ.polytech.card;

/**
 * Emperor card of the game (score = 2)
 */
public class CardEmperor implements CardObjective{
    private static int score = 2;
    Boolean isPlayed = false;

    /**
     * Constructor of the Emperor card
     */
    @Override
    public void randGeneration() {
        //nothing to do
    }

    /**
     * Get the score of the Emperor card (2)
     * @return the score of the Emperor card
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the score of the Emperor card (2)
     * @return the score of the Emperor card
     */
    @Override
    public int calculScore() {
        return score;
    }

    /**
     * Change the state of the card (played or not)
     */
    public void setPlayed(Boolean played) {
        isPlayed = played;
    }

    /**
     * Get the state of the card (played or not)
     * @return the state of the card
     */
    public Boolean getPlayed() {
        return isPlayed;
    }

}
