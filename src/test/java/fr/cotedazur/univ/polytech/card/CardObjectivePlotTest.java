package fr.cotedazur.univ.polytech.card;

        import fr.cotedazur.univ.polytech.board.Board;
        import fr.cotedazur.univ.polytech.plot.Plot;
        import fr.cotedazur.univ.polytech.plot.PlotColor;
        import fr.cotedazur.univ.polytech.utilities.Color;
        import fr.cotedazur.univ.polytech.utilities.Coordinate;
        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.*;

public class CardObjectivePlotTest {

    public Board initBoard(Color color) {
        Board board = new Board();
        for(int i=-2; i<3;i++){
            for(int j=-2; j<3;j++){
                board.push(new PlotColor(new Coordinate(i,j),color));
            }
        }
        return board;
    }

    @Test
    public void testFindLine1() {
        //System.out.println("DEBUT TEST");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.LINE,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(board+"\n");
        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(1,-1);
        Plot p3 = board.findPlot(2,-2);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println("FIN TEST");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindLine2() {
        //System.out.println("DEBUT TEST2");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.LINE,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(board+"\n");
        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(1,0);
        Plot p3 = board.findPlot(2,0);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println("FIN TEST2");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindLine3() {
        //System.out.println("DEBUT TEST3");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.LINE,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(board+"\n");
        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(0,1);
        Plot p3 = board.findPlot(0,2);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println("FIN TEST3");

        assertTrue(c1.matchPattern(board));
    }
    @Test
    public void testFindLine4() {
        //System.out.println("DEBUT TEST4");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.LINE,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(board+"\n");
        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(-1,1);
        Plot p3 = board.findPlot(-2,2);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println("FIN TEST4");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindLine5() {
        //System.out.println("DEBUT TEST5");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.LINE,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(board+"\n");
        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(-1,0);
        Plot p3 = board.findPlot(-2,0);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println("FIN TEST5");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindLine6() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.LINE,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(board+"\n");
        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(0,-1);
        Plot p3 = board.findPlot(0,-2);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindParenthesis1() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.PARENTHESIS,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(board+"\n");
        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(0,-1);
        Plot p3 = board.findPlot(1,-2);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindParenthesis2() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.PARENTHESIS,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);


        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(1,-1);
        Plot p3 = board.findPlot(2,-1);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println(board+"\n");
        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindParenthesis3() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.PARENTHESIS,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(board+"\n");
        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(1,0);
        Plot p3 = board.findPlot(1,1);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindParenthesis4() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.PARENTHESIS,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(0,1);
        Plot p3 = board.findPlot(-1,2);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindParenthesis5() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.PARENTHESIS,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(-1,1);
        Plot p3 = board.findPlot(-2,1);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindParenthesis6() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.PARENTHESIS,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(-1,0);
        Plot p3 = board.findPlot(-1,-1);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindTriangle1() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.TRIANGLE,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(0,-1);
        Plot p3 = board.findPlot(1,-1);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindTriangle2() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.TRIANGLE,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(1,-1);
        Plot p3 = board.findPlot(1,0);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindTriangle3() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.TRIANGLE,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(1,0);
        Plot p3 = board.findPlot(0,1);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindTriangle4() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.TRIANGLE,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(0,1);
        Plot p3 = board.findPlot(-1,1);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindTriangle5() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.TRIANGLE,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(-1,1);
        Plot p3 = board.findPlot(-1,0);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindTriangle6() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.TRIANGLE,Color.GREEN,Color.GREEN);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(-1,0);
        Plot p3 = board.findPlot(0,-1);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.GREEN);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindParallelogram1() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.PARALLELOGRAM,Color.GREEN,Color.PINK);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(1,-1);
        Plot p3 = board.findPlot(2,-1);
        Plot p4 = board.findPlot(1,0);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.PINK);
        ((PlotColor) p4).setColor(Color.PINK);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindParallelogram2() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.PARALLELOGRAM,Color.GREEN,Color.PINK);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(1,0);
        Plot p3 = board.findPlot(1,1);
        Plot p4 = board.findPlot(0,1);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.PINK);
        ((PlotColor) p4).setColor(Color.PINK);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindParallelogram3() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.PARALLELOGRAM,Color.GREEN,Color.PINK);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(0,1);
        Plot p3 = board.findPlot(-1,2);
        Plot p4 = board.findPlot(-1,1);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.PINK);
        ((PlotColor) p4).setColor(Color.PINK);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindParallelogram4() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.PARALLELOGRAM,Color.GREEN,Color.PINK);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(-1,1);
        Plot p3 = board.findPlot(-2,1);
        Plot p4 = board.findPlot(-1,0);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.PINK);
        ((PlotColor) p4).setColor(Color.PINK);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindParallelogram5() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.PARALLELOGRAM,Color.GREEN,Color.PINK);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(-1,0);
        Plot p3 = board.findPlot(-1,-1);
        Plot p4 = board.findPlot(0,-1);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.PINK);
        ((PlotColor) p4).setColor(Color.PINK);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testFindParallelogram6() {
        //System.out.println("DEBUT TEST6");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.PARALLELOGRAM,Color.GREEN,Color.PINK);
        Board board = initBoard(Color.YELLOW);

        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(0,-1);
        Plot p3 = board.findPlot(1,-2);
        Plot p4 = board.findPlot(1,-1);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.PINK);
        ((PlotColor) p4).setColor(Color.PINK);
        //System.out.println(board+"\n");

        //System.out.println("FIN TEST6");

        assertTrue(c1.matchPattern(board));
    }

    @Test
    public void testNotFindLine1() {
        //System.out.println("DEBUT TEST2");
        CardObjectivePlot c1 = new CardObjectivePlot(PatternPlot.LINE,Color.GREEN,Color.GREEN);
        CardObjectivePlot c2 = new CardObjectivePlot(PatternPlot.PARENTHESIS,Color.GREEN,Color.GREEN);
        CardObjectivePlot c3 = new CardObjectivePlot(PatternPlot.TRIANGLE,Color.GREEN,Color.GREEN);
        CardObjectivePlot c4 = new CardObjectivePlot(PatternPlot.PARALLELOGRAM,Color.GREEN,Color.PINK);

        Board board = initBoard(Color.YELLOW);

        //System.out.println(board+"\n");
        //System.out.println(c1);

        Plot p1 = board.findPlot(0,0);
        Plot p2 = board.findPlot(1,0);
        Plot p3 = board.findPlot(2,0);
        ((PlotColor) p1).setColor(Color.GREEN);
        ((PlotColor) p2).setColor(Color.GREEN);
        ((PlotColor) p3).setColor(Color.YELLOW);
        //System.out.println("FIN TEST2");

        assertFalse(c1.matchPattern(board));
        assertFalse(c2.matchPattern(board));
        assertFalse(c3.matchPattern(board));
        assertFalse(c4.matchPattern(board));
    }
}
