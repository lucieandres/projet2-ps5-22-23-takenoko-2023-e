package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Container for the player's score and victory
 */
public class PlayerResultContainer {
    private int score;
    private int victory;

    /**
     * Constructor
     */
    public PlayerResultContainer() {
        this.score = 0;
        this.victory = 0;
    }

    /**
     * Add score to the player's score
     * @param score
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Add victory to the player's victory
     */
    public void addVictory() {
        victory++;
    }

    /**
     * Get the player's victory
     * @return
     */
    public int getVictory() {
        return victory;
    }

    /**
     * Get the player's score
     * @return
     */
    public int getScore() { return score;}

    /**
     * toString method
     * @return
     */
    @Override
    public String toString() {
        return "score = " + score +", victory = " + victory;
    }
}
