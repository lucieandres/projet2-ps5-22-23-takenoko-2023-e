package fr.cotedazur.univ.polytech.dice;

/**
 * Dice class represents a dice with 6 faces
 */
public class Dice {

    private int value;
    private Face face;

    /**
     * Constructor
     */
    public Dice() {
        this.value = 0;
        this.face = null;
    }

    /**
     * Get the value of the dice (the face between 1 and 6)
     * @return the value of the dice
     */
    public int getValue() {
        return value;
    }

    /**
     * Set the value of the dice (the face between 1 and 6)
     * @param value the value of the dice
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Get the face of the dice (Cloud, Interrogation, Rain, Storm, Sun, Wind)
     * @return the face of the dice
     */
    public Face getFace() {
        return face;
    }

    /**
     * Set the face of the dice
     * @param face the face of the dice
     */
    public void setFace(Face face) {
        this.face = face;
    }

    /**
     * Roll the dice and set the value and the face
     */
    public void roll() {
        this.value = (int) (Math.random() * 6) + 1;
        switch (this.value) {
            case 1:
                this.face = new Sun();
                break;
            case 2:
                this.face = new Rain();
                break;
            case 3:
                this.face = new Wind();
                break;
            case 4:
                this.face = new Storm();
                break;
            case 5:
                this.face = new Cloud();
                break;
            case 6:
                this.face = new Interrogation();
                break;
        }
        face.action();
    }

}
