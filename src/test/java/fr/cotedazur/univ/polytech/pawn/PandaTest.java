package fr.cotedazur.univ.polytech.pawn;

import fr.cotedazur.univ.polytech.board.Board;
import fr.cotedazur.univ.polytech.board.BoardGenerator;
import fr.cotedazur.univ.polytech.object.ImprovementType;
import fr.cotedazur.univ.polytech.plot.Etang;
import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.startingpoint.Referee;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;
import fr.cotedazur.univ.polytech.player.PlayerGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PandaTest {
    Board board;
    Etang etang = new Etang();
    Referee referee;
    Panda panda;

    @Test
    public void testAction(){
        do {
            board = (new BoardGenerator(etang)).generateFixedWithIrrigable();
        } while (((PlotColor) board.get(0).get(1)).getImprovement().isPresent());
        panda = new Panda(etang);
        Gardener gardener = new Gardener(etang);
        referee = new Referee(board, null, null, null, panda, gardener, (new PlayerGenerator()).generateBot(1), null, null, null, null);

        Plot plot = referee.getBoard().get(0).get(1);
        referee.moveGardener(1, Direction.MY);
        assertEquals(1, ((PlotColor)plot).getBambooList().size());
        referee.movePanda(1, Direction.MY);
        assertEquals(0, ((PlotColor)plot).getBambooList().size());
    }

    @Test
    public void testActionIfImprovement(){
        board = (new BoardGenerator(etang)).generateEmptyBoard();
        PlotColor pc1;
        do {
            pc1 = new PlotColor(new Coordinate(-1, 0), Color.PINK, 0);
        } while (pc1.getImprovement().isPresent());
        pc1.addIrrigation(Direction.Y);
        board.push(pc1);
        do {
            pc1 = new PlotColor(new Coordinate(-1, 1), Color.PINK, 0);
        } while (pc1.getImprovement().isEmpty()
                || (pc1.getImprovement().isPresent() && pc1.getImprovement().get().getType() != ImprovementType.ENCLOSURE));
        pc1.addIrrigation(Direction.MZ);
        board.push(pc1);

        panda = new Panda(etang);
        Gardener gardener = new Gardener(etang);
        referee = new Referee(board, null, null,  null, panda, gardener, (new PlayerGenerator()).generateBot(1), null, null, null, null);

        Plot plot = referee.getBoard().get(0).get(1);
        referee.moveGardener(1, Direction.MY);
        assertEquals(1, ((PlotColor)plot).getBambooList().size());
        referee.movePanda(1, Direction.MY);
        assertEquals(1, ((PlotColor)plot).getBambooList().size());
    }

    @Test
    public void testToString(){
        panda = new Panda(etang);
        assertEquals("Panda > x: 0, y: 0", panda.toString());
        assertEquals(etang.getCoordinate(), panda.getCoordinate());
    }
}