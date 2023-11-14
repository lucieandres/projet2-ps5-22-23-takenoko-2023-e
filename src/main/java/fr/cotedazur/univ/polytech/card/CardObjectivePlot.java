package fr.cotedazur.univ.polytech.card;

import fr.cotedazur.univ.polytech.board.Board;
import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.utilities.Coordinate;

import java.util.ArrayList;

/**
 * CardObjectivePlot is a class that represents a card objective plot.
 */
public class CardObjectivePlot implements CardObjective {

    ArrayList<ArrayList<PlotColor>> patterns;
    int numCards = 4;
    int numColors = 3;
    private int score;
    int shapeId =0;
    PatternPlot shape;
    int colorId1;
    Color color1;
    int colorId2;
    Color color2;

    /**
     * Constructor of the class CardObjectivePlot.
     */
    public CardObjectivePlot(){
        this.randGeneration();
        if(shape==PatternPlot.LINE){
            createListLine(color1);
        } else if (shape==PatternPlot.PARENTHESIS) {
            createListParenthesis(color1);
        } else if (shape==PatternPlot.TRIANGLE) {
            createListTriangle(color1);
        }else{
            createListParallelogram(color1,color2);
        }
    }

    /**
     * Constructor of the class CardObjectivePlot.
     * @param shape the shape of the card objective plot.
     * @param color1 the color of the card objective plot.
     * @param color2 the color of the card objective plot.
     */
    public CardObjectivePlot(PatternPlot shape, Color color1, Color color2){
        if(shape==PatternPlot.LINE){
            createListLine(color1);
        } else if (shape==PatternPlot.PARENTHESIS) {
            createListParenthesis(color1);
        } else if (shape==PatternPlot.TRIANGLE) {
            createListTriangle(color1);
        }else{
            createListParallelogram(color1,color2);
        }
    }

    /**
     * Create a list of patterns of line for the card objective plot.
     * @param color the color of the card objective plot.
     */
    public void createListLine(Color color) {
        ArrayList<ArrayList<PlotColor>> pattern = new ArrayList<>();

        ArrayList<PlotColor> line1 = new ArrayList<>();
        line1.add(new PlotColor(new Coordinate(0, 0), color));
        line1.add(new PlotColor(new Coordinate(1, -1), color));
        line1.add(new PlotColor(new Coordinate(2, -2), color));
        pattern.add(line1);

        ArrayList<PlotColor> line2 = new ArrayList<>();
        line2.add(new PlotColor(new Coordinate(0, 0), color));
        line2.add(new PlotColor(new Coordinate(1, 0), color));
        line2.add(new PlotColor(new Coordinate(2, 0), color));
        pattern.add(line2);

        ArrayList<PlotColor> line3 = new ArrayList<>();
        line3.add(new PlotColor(new Coordinate(0, 0), color));
        line3.add(new PlotColor(new Coordinate(0, 1), color));
        line3.add(new PlotColor(new Coordinate(0, 2), color));
        pattern.add(line3);

        patterns=pattern;
    }

    /**
     * Create a list of patterns of parenthesis for the card objective plot.
     * @param color the color of the card objective plot.
     */
    public void createListParenthesis(Color color) {
        ArrayList<ArrayList<PlotColor>> pattern = new ArrayList<>();

        ArrayList<PlotColor> parenthesis1 = new ArrayList<>();
        parenthesis1.add(new PlotColor(new Coordinate(0, 0), color));
        parenthesis1.add(new PlotColor(new Coordinate(1, -1), color));
        parenthesis1.add(new PlotColor(new Coordinate(2, -1), color));
        pattern.add(parenthesis1);

        ArrayList<PlotColor> parenthesis2 = new ArrayList<>();
        parenthesis2.add(new PlotColor(new Coordinate(0, 0), color));
        parenthesis2.add(new PlotColor(new Coordinate(1, 0), color));
        parenthesis2.add(new PlotColor(new Coordinate(1, 1), color));
        pattern.add(parenthesis2);

        ArrayList<PlotColor> parenthesis3 = new ArrayList<>();
        parenthesis3.add(new PlotColor(new Coordinate(0, 0), color));
        parenthesis3.add(new PlotColor(new Coordinate(0, 1), color));
        parenthesis3.add(new PlotColor(new Coordinate(-1, 2), color));
        pattern.add(parenthesis3);

        ArrayList<PlotColor> parenthesis4 = new ArrayList<>();
        parenthesis4.add(new PlotColor(new Coordinate(0, 0), color));
        parenthesis4.add(new PlotColor(new Coordinate(-1, 1), color));
        parenthesis4.add(new PlotColor(new Coordinate(-2, 1), color));
        pattern.add(parenthesis4);

        ArrayList<PlotColor> parenthesis5 = new ArrayList<>();
        parenthesis5.add(new PlotColor(new Coordinate(0, 0), color));
        parenthesis5.add(new PlotColor(new Coordinate(-1, 0), color));
        parenthesis5.add(new PlotColor(new Coordinate(-1, -1), color));
        pattern.add(parenthesis5);

        ArrayList<PlotColor> parenthesis6 = new ArrayList<>();
        parenthesis6.add(new PlotColor(new Coordinate(0, 0), color));
        parenthesis6.add(new PlotColor(new Coordinate(0, -1), color));
        parenthesis6.add(new PlotColor(new Coordinate(1, -2), color));
        pattern.add(parenthesis6);

        patterns=pattern;
    }

    /**
     * Create a list of patterns of triangle for the card objective plot.
     * @param color the color of the card objective plot.
     */
    public void createListTriangle(Color color) {
        ArrayList<ArrayList<PlotColor>> pattern = new ArrayList<>();

        ArrayList<PlotColor> triangle1 = new ArrayList<>();
        triangle1.add(new PlotColor(new Coordinate(0, 0), color));
        triangle1.add(new PlotColor(new Coordinate(0, -1), color));
        triangle1.add(new PlotColor(new Coordinate(1, -1), color));
        pattern.add(triangle1);

        ArrayList<PlotColor> triangle2 = new ArrayList<>();
        triangle2.add(new PlotColor(new Coordinate(0, 0), color));
        triangle2.add(new PlotColor(new Coordinate(1, -1), color));
        triangle2.add(new PlotColor(new Coordinate(1, 0), color));
        pattern.add(triangle2);

        patterns=pattern;
    }

    /**
     * Create a list of patterns of parallelogram for the card objective plot.
     * @param color the color of the card objective plot.
     * @param color2 the color of the card objective plot.
     */
    public void createListParallelogram(Color color, Color color2) {
        ArrayList<ArrayList<PlotColor>> pattern = new ArrayList<>();

        ArrayList<PlotColor> parallelogram1 = new ArrayList<>();
        parallelogram1.add(new PlotColor(new Coordinate(0, 0), color));
        parallelogram1.add(new PlotColor(new Coordinate(0, -1), color));
        parallelogram1.add(new PlotColor(new Coordinate(1, -2), color2));
        parallelogram1.add(new PlotColor(new Coordinate(1, -1), color2));
        pattern.add(parallelogram1);

        ArrayList<PlotColor> parallelogram2 = new ArrayList<>();
        parallelogram2.add(new PlotColor(new Coordinate(0, 0), color));
        parallelogram2.add(new PlotColor(new Coordinate(1, -1), color));
        parallelogram2.add(new PlotColor(new Coordinate(2, -1), color2));
        parallelogram2.add(new PlotColor(new Coordinate(1, 0), color2));
        pattern.add(parallelogram2);

        ArrayList<PlotColor> parallelogram3 = new ArrayList<>();
        parallelogram3.add(new PlotColor(new Coordinate(0, 0), color));
        parallelogram3.add(new PlotColor(new Coordinate(1, 0), color));
        parallelogram3.add(new PlotColor(new Coordinate(1, 1), color2));
        parallelogram3.add(new PlotColor(new Coordinate(0, 1), color2));
        pattern.add(parallelogram3);

        ArrayList<PlotColor> parallelogram4 = new ArrayList<>();
        parallelogram4.add(new PlotColor(new Coordinate(0, 0), color));
        parallelogram4.add(new PlotColor(new Coordinate(0, 1), color));
        parallelogram4.add(new PlotColor(new Coordinate(-1, 2), color2));
        parallelogram4.add(new PlotColor(new Coordinate(-1, 1), color2));
        pattern.add(parallelogram4);

        ArrayList<PlotColor> parallelogram5 = new ArrayList<>();
        parallelogram5.add(new PlotColor(new Coordinate(0, 0), color));
        parallelogram5.add(new PlotColor(new Coordinate(-1, 1), color));
        parallelogram5.add(new PlotColor(new Coordinate(-2, 1), color2));
        parallelogram5.add(new PlotColor(new Coordinate(-1, 0), color2));
        pattern.add(parallelogram5);

        ArrayList<PlotColor> parallelogram6 = new ArrayList<>();
        parallelogram6.add(new PlotColor(new Coordinate(0, 0), color));
        parallelogram6.add(new PlotColor(new Coordinate(-1, 0), color));
        parallelogram6.add(new PlotColor(new Coordinate(-1, -1), color2));
        parallelogram6.add(new PlotColor(new Coordinate(0, -1), color2));
        pattern.add(parallelogram6);

        patterns=pattern;
    }

    /**
     * Generate a random card objective plot.
     */
    @Override
    public void randGeneration() {

        colorId1=(int)(Math.random() * (numColors));
        switch(colorId1) {
            case 0:
                color1 = Color.PINK;
                break;
            case 1:
                color1 = Color.YELLOW;
                break;
            case 2:
                color1 = Color.GREEN;
                break;
        }

        do {
            colorId2=(int) (Math.random() * (numColors));
        }while (colorId2==colorId1);

        switch(colorId2) {
            case 0:
                color2 = Color.PINK;
                break;
            case 1:
                color2 = Color.YELLOW;
                break;
            case 2:
                color2 = Color.GREEN;
                break;
        }

        shapeId=(int)(Math.random() * (numCards));
        switch(shapeId) {
            case 0:
                shape = PatternPlot.LINE;
                break;
            case 1:
                shape = PatternPlot.PARENTHESIS;
                break;
            case 2:
                shape = PatternPlot.TRIANGLE;
                break;
            case 3:
                shape = PatternPlot.PARALLELOGRAM;
                break;
        }
    }

    /**
     * Get the score of the card objective plot.
     * @return the score of the card objective plot.
     */
    public int getScore(){
        return score;
    }

    /**
     * Calculate the score of the card objective plot.
     * @return the score of the card objective plot.
     */
    @Override
    public int calculScore() {
        if(shape==PatternPlot.LINE)
            score=2;
        else if(shape==PatternPlot.PARENTHESIS)
            score=3;
        else
            score=4;
        return score;
    }

    /**
     * Verify if the card objective plot is completed.
     * @param board
     * @return true if the card objective plot is completed, false otherwise.
     */
    public boolean matchPattern(Board board){
        for(ArrayList<PlotColor> pattern : patterns){
            if(matchPattern(board,pattern)){
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if the card objective plot is completed.
     * @param board
     * @param pattern
     * @return true if the card objective plot is completed, false otherwise.
     */
    private boolean matchPattern(Board board,ArrayList<PlotColor> pattern){
        for(Plot p : board.allPlots()){
            if(matchPattern(board,p,pattern)){
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if the card objective plot is completed.
     * @param b
     * @param boardPlot
     * @param pattern
     * @return true if the card objective plot is completed, false otherwise.
     */
    private boolean matchPattern(Board b, Plot boardPlot, ArrayList<PlotColor> pattern){

        Coordinate boardCoord = new Coordinate(boardPlot.getCoordinate().getX(),boardPlot.getCoordinate().getY());
        Coordinate patternCoordinate = new Coordinate(0,0);


        for(PlotColor p : pattern){
            patternCoordinate.setX(p.getCoordinate().getX());
            patternCoordinate.setY(p.getCoordinate().getY());

            patternCoordinate.setX(patternCoordinate.getX()+boardCoord.getX());
            patternCoordinate.setY(patternCoordinate.getY()+boardCoord.getY());

            Plot foundPlot = b.findPlot(patternCoordinate.getX(),patternCoordinate.getY());
            if (foundPlot == null)
            {
                return false;
            }
            if (!(foundPlot instanceof PlotColor))
            {
                return false;
            }
            PlotColor foundPlotColor = (PlotColor)foundPlot;
            if (foundPlotColor.getColor() != p.getColor())
            {
                return false;
            }
        }
        return true;
    }

    /**
     * To string method.
     * @return the string of the card objective plot.
     */
    public String toString(){
        String result = "";
        for(ArrayList<PlotColor> a : patterns){
            result += "-----------------------------------\n";
            for (PlotColor p : a){
                result += p.toString();
                result += "\n";
            }
        }
        return result;
    }
}
