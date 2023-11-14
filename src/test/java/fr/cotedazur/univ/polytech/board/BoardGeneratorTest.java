package fr.cotedazur.univ.polytech.board;

import fr.cotedazur.univ.polytech.plot.Etang;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;
import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BoardGeneratorTest {
    BoardGenerator boardGenerator;
    Etang etang;

    @BeforeEach
    public void before() {
        etang = new Etang();
        boardGenerator = new BoardGenerator(etang);
    }

    @Test
    void boardSettingEmptyTest() {
        IrrigableCoordinates irr = new IrrigableCoordinates(new Coordinate(0, 1), Direction.MY, new Coordinate(-1, 1));
        assertEquals(1, boardGenerator.generateEmptyBoard().size());
    }
}
