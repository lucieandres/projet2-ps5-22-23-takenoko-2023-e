package fr.cotedazur.univ.polytech.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Direction enum class for the 6 directions of the plot
 */
public enum Direction {
    /**
     * differents directions
     */
    X(0) {
        /**
         * toString method
         * @return the string of the direction
         */
        @Override
        public String toString() {
            return "X";
        }
    },
    Y(1){
        /**
         * toString method
         * @return the string of the direction
         */
        @Override
        public String toString() {
            return "Y";
        }
    },
    MZ(2){
        /**
         * toString method
         * @return the string of the direction
         */
        @Override
        public String toString() {
            return "MZ";
        }
    },
    MX(3){
        /**
         * toString method
         * @return the string of the direction
         */
        @Override
        public String toString() {
            return "MX";
        }
    },
    MY(4){
        /**
         * toString method
         * @return the string of the direction
         */
        @Override
        public String toString() {
            return "MY";
        }
    },
    Z(5){
        /**
         * toString method
         * @return the string of the direction
         */
        @Override
        public String toString() {
            return "Z";
        }
    };

    int hash;

    /**
     * Constructor
     * @param hash
     */
    private Direction(int hash) {
        this.hash = hash;
    }

    /**
     * randomDirection method
     * @return a random direction
     */
    public static Direction randomDirection() {
        Direction[] direction = values();
        return direction[(new Random()).nextInt(direction.length)];
    }

    /**
     * allDirection method
     * @return a list of all the directions
     */
    public static List<Direction> allDirection() {
        return new ArrayList<Direction>(List.of(values()));
    }

    /**
     * nextDirection method
     * @return the next direction
     */
    public Direction nextDirection() {
        return allDirection().get((this.hash+1)%6);
    }

    /**
     * previousDirection method
     * @return the previous direction
     */
    public Direction previousDirection() {
        if(this.hash==0) return allDirection().get(5);
        return allDirection().get((this.hash-1));
    }

    /**
     * oppositeDirection method
     * @return the opposite direction
     */
    public Direction oppositeDirection() {
        return allDirection().get((this.hash+3)%6);
    }
}
