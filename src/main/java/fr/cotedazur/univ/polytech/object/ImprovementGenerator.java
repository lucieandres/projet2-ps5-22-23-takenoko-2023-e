package fr.cotedazur.univ.polytech.object;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * Generate the deck of improvement
 */
public class ImprovementGenerator {

    /**
     * Generate the deck of improvement
     * @param NBIMPROVEMENTPERTYPE the number of improvement per type
     * @return the deck of improvement
     */
    public LinkedList<Improvement> generateImprovement(int NBIMPROVEMENTPERTYPE) {
        LinkedList<Improvement> tmp = new LinkedList<Improvement>();
        for (int i = 0; i < NBIMPROVEMENTPERTYPE; i++) {
            tmp.add(new Improvement(ImprovementType.ENCLOSURE));
            tmp.add(new Improvement(ImprovementType.FERTILIZER));
            tmp.add(new Improvement(ImprovementType.WATERSHED));
        }
        Collections.shuffle(tmp, new Random());
        return tmp;
    }

}
