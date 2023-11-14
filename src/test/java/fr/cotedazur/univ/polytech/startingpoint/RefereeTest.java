package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.board.Board;
import fr.cotedazur.univ.polytech.board.BoardGenerator;
import fr.cotedazur.univ.polytech.card.CardObjectivePanda;
import fr.cotedazur.univ.polytech.object.ImprovementType;
import fr.cotedazur.univ.polytech.plot.Etang;
import fr.cotedazur.univ.polytech.player.PlayerGenerator;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.pawn.Gardener;
import fr.cotedazur.univ.polytech.pawn.Panda;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static fr.cotedazur.univ.polytech.startingpoint.Referee.TOOLS;
import static org.junit.jupiter.api.Assertions.*;

public class RefereeTest {
    Board board;
    Etang etang = new Etang();
    Referee referee;

    @BeforeEach
    public void setUp() {
        board = (new BoardGenerator(etang)).generateFixedWithIrrigable();
        Panda panda = new Panda(etang);
        Gardener gardener = new Gardener(etang);
        referee = new Referee(board, null, null, null, panda, gardener, (new PlayerGenerator()).generateBot(1), null, null, /*TODO: change*/ null, /*TODO: change*/ null);
        //TODO : Faire les test sur les push des plots sur le plateau
        referee.getCurrentPlayer().addCard(new CardObjectivePanda());

    }


    @Test
    public void testMoveGardener() {
        referee.moveGardener(1, Direction.MX);
        assertEquals(0, referee.getGardener().getPlot().getCoordinate().getX());
        assertEquals(-1, referee.getGardener().getPlot().getCoordinate().getY());

        referee.moveGardener(2, Direction.X);
        assertEquals(0, referee.getGardener().getPlot().getCoordinate().getX());
        assertEquals(1, referee.getGardener().getPlot().getCoordinate().getY());

        referee.moveGardener(1, Direction.Y);
        assertEquals(1, referee.getGardener().getPlot().getCoordinate().getX());
        assertEquals(1, referee.getGardener().getPlot().getCoordinate().getY());

        referee.moveGardener(1, Direction.MY);
        assertEquals(0, referee.getGardener().getPlot().getCoordinate().getX());
        assertEquals(1, referee.getGardener().getPlot().getCoordinate().getY());

        referee.moveGardener(1, Direction.MZ);
        assertEquals(1, referee.getGardener().getPlot().getCoordinate().getX());
        assertEquals(0, referee.getGardener().getPlot().getCoordinate().getY());

        referee.moveGardener(1, Direction.Z);
        assertEquals(0,referee.getGardener().getPlot().getCoordinate().getX());
        assertEquals(1, referee.getGardener().getPlot().getCoordinate().getY());

    }

    @Test
    public void testMovePanda() {
        referee.movePanda(1, Direction.MX);
        assertEquals(0, referee.getPanda().getPlot().getCoordinate().getX());
        assertEquals(-1, referee.getPanda().getPlot().getCoordinate().getY());

        referee.movePanda(2, Direction.X);
        assertEquals(0, referee.getPanda().getPlot().getCoordinate().getX());
        assertEquals(1, referee.getPanda().getPlot().getCoordinate().getY());

        referee.movePanda(1, Direction.Y);
        assertEquals(1, referee.getPanda().getPlot().getCoordinate().getX());
        assertEquals(1, referee.getPanda().getPlot().getCoordinate().getY());

        referee.movePanda(1, Direction.MY);
        assertEquals(0, referee.getPanda().getPlot().getCoordinate().getX());
        assertEquals(1, referee.getPanda().getPlot().getCoordinate().getY());

        referee.movePanda(1, Direction.MZ);
        assertEquals(1, referee.getPanda().getPlot().getCoordinate().getX());
        assertEquals(0, referee.getPanda().getPlot().getCoordinate().getY());

        referee.movePanda(1, Direction.Z);
        assertEquals(0,referee.getPanda().getPlot().getCoordinate().getX());
        assertEquals(1, referee.getPanda().getPlot().getCoordinate().getY());
    }
    @Test
    public void testActionGardener() {
        do {
            board = (new BoardGenerator(etang)).generateFixedWithIrrigable();
        } while (((PlotColor) board.get(0).get(1)).getImprovement().isEmpty() || ((PlotColor) board.get(0).get(1)).getImprovement().get().getType() == ImprovementType.FERTILIZER);
        Panda panda = new Panda(etang);
        Gardener gardener = new Gardener(etang);
        referee = new Referee(board, null, null, null, panda, gardener, (new PlayerGenerator()).generateBot(1), null, null, null, null);

        referee.getCurrentPlayer().addCard(new CardObjectivePanda());
        referee.moveGardener(1, Direction.MY);
        assertEquals(1, ((PlotColor) referee.getGardener().getPlot()).getSizeBambooList());

    }

    @Test
    public void testCalculMaxDistance() {
        assertEquals(TOOLS.calcDistanceMax(new Coordinate(1, 0), Direction.MY, board), 2);
        assertEquals(TOOLS.calcDistanceMax(new Coordinate(0, 0), Direction.MX, board), 1);
        assertEquals(TOOLS.calcDistanceMax(new Coordinate(1, -1), Direction.MZ, board), 0);
    }

    @Test
    public void testfindColoredZone() {
        HashSet<PlotColor> plots = TOOLS.coloredZone(referee.getBoard().get(1).get(2), board);
        assertEquals(1, plots.size());

        //sur l'etang
        plots = TOOLS.coloredZone(referee.getBoard().get(1).get(1), board);
        assertEquals(0, plots.size());
    }

    @Test
    public void testFindAdjoiningColor() {
        HashSet<PlotColor> plotsAdj = new HashSet<>();
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
        Gardener gardener = new Gardener(etang);
        referee = new Referee(board, null, null,null, panda, gardener, (new PlayerGenerator()).generateBot(1), null, null, null, null);


        TOOLS.findAdjoiningColor((PlotColor)referee.getBoard().get(0).get(1), plotsAdj, board);
        // not irrigate because of build of the board ?
        assertEquals(2, plotsAdj.size());
        assertTrue(plotsAdj.contains(referee.getBoard().get(0).get(1)));
        assertFalse(plotsAdj.contains(etang));
    }

    @Test
    public void testPlayCardPandaList() {

        referee.playCard();

        assertEquals(1,referee.getCurrentPlayer().getSizeListCards());
        assertEquals(0,referee.getCurrentPlayer().getSizeListCardsPlayed());
    }

    @Test
    public void testIrrigatePlotUpdateIrrigableCoordinateSet() {

    }
}


