package fr.cotedazur.univ.polytech.card;

import fr.cotedazur.univ.polytech.utilities.Color;

/**
 * Card Objective Gardener : 3 or 4 bamboo sections of the same color (pink, yellow or green) with or without improvement
 */
public class CardObjectiveGardener implements CardObjective {
    private int numberBamboo;
    private Color bambooColor;
    private boolean improvement;
    private int score;

    /**
     * Constructor of the card objective gardener
     */
    public CardObjectiveGardener(){
        this.randGeneration();
        this.calculScore();
    }

    /**
     * Generate a random card objective gardener
     */
    @Override
    public void randGeneration() {
        numberBamboo=3 + (int)(Math.random() * ((4 - 3) + 1));
        int bambou=0 + (int)(Math.random() * ((2 - 0) + 1));
            switch(bambou) {
                case 0:
                    bambooColor = Color.PINK;
                    break;
                case 1:
                    bambooColor=Color.YELLOW;
                    break;
                case 2:
                    bambooColor=Color.GREEN;
                    break;
                default :
                    break;

            }

        if(numberBamboo==3){
            improvement =false;
        }else{
            int amenagementBool=0 + (int)(Math.random() * ((1 - 0) + 1));
            if(amenagementBool==1){
                improvement =false;
            }else{
                improvement =true;
            }
        }

    }

    /**
     * Calculate the score of the card objective gardener
     * @return the score of the card objective gardener
     */
    public int calculScore(){
        if(bambooColor==Color.PINK){
            score=numberBamboo*1;
        }
        else if(bambooColor==Color.YELLOW){
            score=(int)(numberBamboo*0.93);
        }
        else if(bambooColor==Color.GREEN){
            score=(int)(numberBamboo*0.85);
        }

        if(improvement){
            score+=2;
        }

        return score;
    }

    /**
     * Get the score of the card objective gardener
     * @return the score of the card objective gardener
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Get the color of the bamboo of the card objective gardener
     * @return the color of the bamboo of the card objective gardener
     */
    public Color getCouleurBambou(){
        return this.bambooColor;
    }

    /**
     * Get the number of bamboo of the card objective gardener
     * @return the number of bamboo of the card objective gardener
     */
    public int getNombreSectionBambou(){
        return this.numberBamboo;
    }

    /**
     * To string of the card objective gardeners
     * @return the string of the card objective gardener
     */
    public String toString(){
        String result = "Il faut "+this.numberBamboo+" sections de bambous avec la couleur "+this.bambooColor;
        if(this.improvement){
            result+=" avec aménagement";
        }else{
            result+=" sans aménagement";
        }
        result+=",cette carte rapporte "+this.score+" points";
        return result;
    }

    /**
     * Get the improvement of the card objective gardener
     * @return the improvement of the card objective gardener
     */
    public boolean isImprovement() {
        return this.improvement;
    }

}
