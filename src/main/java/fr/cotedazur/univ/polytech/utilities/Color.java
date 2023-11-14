package fr.cotedazur.univ.polytech.utilities;

/**
 * Enum of the colors of the game
 */
public enum Color {

    /**
     * The colors of the game
     */
    PINK {
        /**
         * To string method
         * @return the color in string
         */
        @Override
        public String toString() {
            return "pink";
        }
    },
    GREEN {
        /**
         * To string method
         * @return the color in string
         */
        @Override
        public String toString() {
            return "green";
        }
    },
    YELLOW {
        /**
         * To string method
         * @return the color in string
         */
        @Override
        public String toString() {
            return "yellow";
        }
    };
}
