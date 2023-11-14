package fr.cotedazur.univ.polytech.object;

/**
 * Class representing an improvement on a tile
 */
public class Improvement extends ObjectPoseable {
    private ImprovementType type;

    /**
     * Constructor
     * @param type the type of improvement
     */
    public Improvement(ImprovementType type) {
        this.type = type;
    }

    /**
     * Get the type of improvement
     * @return the type of improvement
     */
    public ImprovementType getType() {
        return type;
    }

    /**
     * Set the type of improvement
     * @param type the type of improvement
     */
    public String toString() {
        return "Amenagement " + type.toString();
    }

}