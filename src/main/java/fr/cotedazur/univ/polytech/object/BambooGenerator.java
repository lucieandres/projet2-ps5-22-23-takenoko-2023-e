package fr.cotedazur.univ.polytech.object;

import fr.cotedazur.univ.polytech.utilities.Color;

import java.util.*;

/**
 * This class is used to generate the bamboo deck
 */
public class BambooGenerator {

    /**
     * This method is used to generate the bamboo deck
     * @return the bamboo deck
     */
    public Map<Color, LinkedList<Bamboo>> generateMapBamboos() {
        Map<Color, LinkedList<Bamboo>> res = new HashMap<>();

        res.put(Color.GREEN, generateGreenBamboosList());
        res.put(Color.PINK, generatePinkBamboosList());
        res.put(Color.YELLOW, generateYellowBamboosList());

        return res;
    }

    /**
     * This method is used to generate the green bamboo deck
     * @return the green bamboo deck
     */
    public LinkedList<Bamboo> generateGreenBamboosList() {
        LinkedList<Bamboo> res = new LinkedList<>();
        for(int i=0; i<36; i++) {
            res.add(new Bamboo(Color.GREEN));
        }
        return res;
    }

    /**
     * This method is used to generate the pink bamboo deck
     * @return the pink bamboo deck
     */
    public LinkedList<Bamboo> generatePinkBamboosList() {
        LinkedList<Bamboo> res = new LinkedList<>();
        for(int i=0; i<30; i++) {
            res.add(new Bamboo(Color.PINK));
        }
        return res;
    }

    /**
     * This method is used to generate the yellow bamboo deck
     * @return the yellow bamboo deck
     */
    public LinkedList<Bamboo> generateYellowBamboosList() {
        LinkedList<Bamboo> res = new LinkedList<>();
        for(int i=0; i<24; i++) {
            res.add(new Bamboo(Color.YELLOW));
        }
        return res;
    }
}
