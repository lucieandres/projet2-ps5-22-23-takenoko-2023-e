package fr.cotedazur.univ.polytech.card;

import fr.cotedazur.univ.polytech.utilities.Color;

/**
 * Class which represents a card objective panda
 */
public class CardObjectivePanda implements CardObjective {
    private int numberOfBamboo;
    private int score;
    private Color[] bamboos;

    /**
     * Constructor of the class CardObjectivePanda
     */
    public CardObjectivePanda(){
        this.randGeneration();
        this.calculScore();
    }

    /**
     * Method which generates randomly the number of bamboo and the colors of the bamboo
     */
    @Override
    public void randGeneration() {
        this.numberOfBamboo = 2 + (int)(Math.random() * ((3 - 2) + 1));
        bamboos = new Color[numberOfBamboo];
        int color;
        for(int i=0; i<numberOfBamboo; i++){
            color=0 + (int)(Math.random() * ((2 - 0) + 1));
            switch(color) {
                case 0:
                    bamboos[i]=Color.PINK;
                    break;
                case 1:
                    bamboos[i]=Color.YELLOW;
                    break;
                case 2:
                    bamboos[i]=Color.GREEN;
                    break;

            }
        }
    }

    /**
     * Method which calculates the score of the card
     * @return the score of the card
     */
    public int calculScore(){
        for(int i=0; i<numberOfBamboo; i++){
            if(bamboos[i]==Color.PINK){
                score+=numberOfBamboo*1;
            }
            else if(bamboos[i]==Color.YELLOW){
                score+=(int)(numberOfBamboo*0.93);
            }
            else if(bamboos[i]==Color.GREEN){
                score+=(int)(numberOfBamboo*0.85);
            }
        }

        return score;
    }

    /**
     * Method which prints the array of the colors of the bamboo
     */
    public void printArrayBambouCard(){
        for(int i=0; i<numberOfBamboo;i++){
            System.out.print(bamboos[i]);
        }
    }

    /**
     * Method which returns the score of the card
     * @return the score of the card
     */
    public int getScore(){
        return score;
    }

    /**
     * Method which returns the number of bamboo
     * @return the number of bamboo
     */
    public int getNumberOfBambou(){
        return numberOfBamboo;
    }

    /**
     * Method which returns the array of the colors of the bamboo
     * @return the array of the colors of the bamboo
     */
    public Color[] getTableauBambou(){
        return bamboos;
    }

    /**
     * Method which returns the string of the card
     * @return the string of the card
     */
    public String toString(){
        String result = "Il faut "+numberOfBamboo+" bambous avec les couleurs ";
        for(int i=0; i<numberOfBamboo;i++){
            result += bamboos[i] + " ";
        }
        result +=",cette carte rapporte "+score+" points";
        return result;
    }


}

