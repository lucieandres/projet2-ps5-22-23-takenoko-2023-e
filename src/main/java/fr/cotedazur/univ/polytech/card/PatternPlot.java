package fr.cotedazur.univ.polytech.card;

/**
 * Enum of the different pattern of the plot
 */
public enum PatternPlot {

    /**
     * The different pattern of the plot
     */
    LINE {
        /**
         * Return the string of the pattern
         * @return the string of the pattern
         */
        @Override
        public String toString() {return "line";}
    },
    PARENTHESIS {
        /**
         * Return the string of the pattern
         * @return the string of the pattern
         */
        @Override
        public String toString() {
            return "parenthesis";
        }
    },
    TRIANGLE {
        /**
         * Return the string of the pattern
         * @return the string of the pattern
         */
        @Override
        public String toString() {
            return "triangle";
        }
    },
    PARALLELOGRAM {
        /**
         * Return the string of the pattern
         * @return the string of the pattern
         */
        @Override
        public String toString() {
            return "parallelogram";
        }
    };
}
