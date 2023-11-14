package fr.cotedazur.univ.polytech.object;

import fr.cotedazur.univ.polytech.utilities.Color;

/**
 * Bamboo class
 */
public class Bamboo extends ObjectPoseable {
    private Color color;

    /**
     * Constructor
     * @param color
     */
    public Bamboo(Color color) {
        this.color = color;
    }

    /**
     * Get the color of the bamboo
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * toString method
     * @return
     */
    public String toString() {
        return "Bamboo " + color.toString();
    }

}
