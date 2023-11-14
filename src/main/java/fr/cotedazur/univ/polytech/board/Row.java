package fr.cotedazur.univ.polytech.board;

import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.plot.PlotComparator;

import java.util.ArrayList;
import java.util.Collections;

/***
 * Row is a list of Plots ordered by their coordinate
 */
public class Row extends ArrayList<Plot> {
    public Row() {
        super();
    }

    /**
     * Build a row with one plot on it
     * @param plot
     */
    public Row(Plot plot) {
        super();
        this.add(plot);
    }

    /**
     * Build a row based on a list of plots
     * @param plots
     */
    public Row(ArrayList<Plot> plots) {
        Collections.sort(plots, new PlotComparator());
        this.addAll(plots);
    }
    /**
     * the method push(plot) add a plot on the plot coordinate
     *
     * @param plot is the plot which need to be push in the row
     * @return return a list of coordinate able to get a neighbour (2 plots in its area to be able)
     */
    public void push(Plot plot) {
        if(size()==0) {
            this.add(plot);
        }
        else {
            int i = this.size();
            this.add(this.get(i-1));

            for(i = this.size()-1; i>0; i--){
                this.set(i, this.get(i-1));
                if(this.get(i).isBiggerInRow(plot)) {
                    break;
                }
            }
            this.set(i, plot);
        }
    }

    /***
     * get the coordinate's position of the first Plot in the current Row
     * @return the position (Y of the plot)
     */
    public int getFirstPlotPosition() {
        return this.get(0).getCoordinate().getY();
    }

    /***
     * get the coordinate's position of the last Plot in the current Row
     * @return the position (Y of the plot)
     */
    public int getLastPlotPosition() {
        return this.get(this.size()-1).getCoordinate().getY();
    }

    /***
     * get the position of the current Row
     * @return the position (Y of the plot)
     */
    public int getRowPosition() {
        return this.get(0).getCoordinate().getX();
    }

    /**
     * The toString method
     * @return the string representation of the current Row
     */
    @Override
    public String toString() {
        String result = "Row "+ this.getRowPosition() +" : [ ";
        for(Plot plot : this) {
            result += plot.toString() + " ; ";
        }
        return result + "]";
    }
}

