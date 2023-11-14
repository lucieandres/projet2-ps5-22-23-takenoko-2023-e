package fr.cotedazur.univ.polytech.board;

import fr.cotedazur.univ.polytech.board.Row;

import java.util.Comparator;

/**
 * Class to compare two rows
 */
public class RowComparator implements Comparator<Row> {

    /**
     * Compare two rows
     * @param o1 first row
     * @param o2 second row
     * @return 0 if the rows are equals, 1 if the first row is greater than the second row, -1 if the first row is lower than the second row
     */
    @Override
    public int compare(Row o1, Row o2) {
        return o1.getRowPosition()-o2.getRowPosition();
    }
}
