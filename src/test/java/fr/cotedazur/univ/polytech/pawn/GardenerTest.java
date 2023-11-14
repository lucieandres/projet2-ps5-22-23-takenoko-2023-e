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

import java.util.ArrayList;
import java.util.HashSet;

import static fr.cotedazur.univ.polytech.startingpoint.Referee.TOOLS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GardenerTest {
    Board board;
    Etang etang = new Etang();
    Referee referee;
    Gardener gardener;

    @Test
    public void testAction(){
        board = (new BoardGenerator(etang)).generateEmptyBoard();
        PlotColor pc1;
        do {
            pc1 = new PlotColor(new Coordinate(-1, 0), Color.PINK, 0);
        } while (pc1.getImprovement().isPresent());
        pc1.addIrrigation(Direction.Y);
        board.push(pc1);
        do {
            pc1 = new PlotColor(new Coordinate(-1, 1), Color.PINK, 0);
        } while (pc1.getImprovement().isPresent());
        pc1.addIrrigation(Direction.MZ);
        board.push(pc1);

        Panda panda = new Panda(etang);
        gardener = new Gardener(etang);
        referee = new Referee(board, null, null, null, panda, gardener, (new PlayerGenerator()).generateBot(1), null, null, null, null);

        HashSet<PlotColor> plots = TOOLS.coloredZone(referee.getBoard().get(0).get(1), board);
        assertEquals(2, plots.size());
        for(PlotColor plotColor : plots) {
            assertEquals(0, plotColor.getBambooList().size());
        }
        referee.moveGardener(1, Direction.MY);
        for(PlotColor plotColor : plots) {
            assertEquals(1, plotColor.getBambooList().size());
        }
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
                || (pc1.getImprovement().isPresent() && pc1.getImprovement().get().getType() != ImprovementType.FERTILIZER));
        pc1.addIrrigation(Direction.MZ);
        board.push(pc1);

        Panda panda = new Panda(etang);
        gardener = new Gardener(etang);
        referee = new Referee(board, null, null,null, panda, gardener, (new PlayerGenerator()).generateBot(1), null, null, null, null);

        HashSet<PlotColor> plots = TOOLS.coloredZone(referee.getBoard().get(0).get(1), board);
        assertEquals(2, plots.size());
        for(PlotColor plotColor : plots) {
            assertEquals(0, plotColor.getBambooList().size());
        }
        referee.moveGardener(1, Direction.MY);
        int nbBamboo = plots.stream().toList().get(0).getBambooList().size() + plots.stream().toList().get(1).getBambooList().size();
        assertEquals(3, nbBamboo);

    }

    @Test
    public void testToString(){
        gardener = new Gardener(etang);
        assertEquals("Gardener > x: 0, y: 0", gardener.toString());
        assertEquals(etang.getCoordinate(), gardener.getCoordinate());
    }
}