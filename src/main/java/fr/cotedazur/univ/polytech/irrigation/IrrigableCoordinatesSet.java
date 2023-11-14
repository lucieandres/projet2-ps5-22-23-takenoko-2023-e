package fr.cotedazur.univ.polytech.irrigation;

import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinates;
import fr.cotedazur.univ.polytech.utilities.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Set of IrrigableCoordinates
 */
public class IrrigableCoordinatesSet extends HashSet<IrrigableCoordinates> {

    /**
     * Constructor
     */
    public IrrigableCoordinatesSet() {
        super();
    }

    /**
     * Get the irrigable coordinates containing the given coordinate
     * @param coordinate
     * @return the irrigable coordinates containing the given coordinate
     */
    public List<IrrigableCoordinates> getICbyCoo(Coordinate coordinate) {
        List<IrrigableCoordinates> coos = new ArrayList<>();
        for (IrrigableCoordinates ic : this) {
            if(ic.contains(coordinate)) coos.add(ic);
        }
        return coos;
    }
}
