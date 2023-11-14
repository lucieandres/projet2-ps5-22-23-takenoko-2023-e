package fr.cotedazur.univ.polytech.object;

/**
 * Enum of the different types of improvements
 */
public enum ImprovementType {

    /**
     * The different types of improvements
     */
    ENCLOSURE{

        /**
         * @return the name of the improvement
         */
        @Override
        public String toString() {
            return "enclos";
        }
    },
    FERTILIZER{
        /**
         * @return the name of the improvement
         */
        @Override
        public String toString() {
            return "engrais";
        }
    },
    WATERSHED{
        /**
         * @return the name of the improvement
         */
        @Override
        public String toString() {
            return "bassin";
        }
    };
}