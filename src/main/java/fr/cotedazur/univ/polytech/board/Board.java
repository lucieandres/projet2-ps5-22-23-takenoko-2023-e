package fr.cotedazur.univ.polytech.board;

import fr.cotedazur.univ.polytech.plot.Etang;
import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinates;
import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinatesSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Board is a list of Row
 * Each Row is a list of Plot
 */
public class Board extends ArrayList<Row> {

    private IrrigableCoordinatesSet irrigableCoordinates;
    private Etang etang;

    /**
     * Constructor
     */
    public Board() {
        super();
        irrigableCoordinates = new IrrigableCoordinatesSet();
    }

    /**
     * Constructor
     * @param rows,etang
     */
    public Board(ArrayList<Row> rows, Etang etang) {
        super();
        this.addAll(rows);
        this.etang = etang;
        irrigableCoordinates = new IrrigableCoordinatesSet();
    }

    /**
     * Constructor
     * @param rows
     */
    public Board(ArrayList<Row> rows) {
        super();
        this.addAll(rows);
        this.etang = findEtang(rows);
        irrigableCoordinates = new IrrigableCoordinatesSet();
    }

    /**
     * find the Etang in the list of rows
     * @param rows
     * @return the Etang
     */
    private Etang findEtang(ArrayList<Row> rows) {
        for(Row row : rows) {
            for (Plot p : row) {
                if(p instanceof Etang) return (Etang) p;
            }
        }
        return null;
    }

    /**
     * get the etang
     * @return the etang
     */
    public Etang getEtang() {
        return etang;
    }

    /**
     * set the coordinates of the irrigable plots
     * @param irrigableCoordinates
     */
    public void setIrrigableCoordinates(IrrigableCoordinatesSet irrigableCoordinates) {
        this.irrigableCoordinates = irrigableCoordinates;

    }

    /**
     * get the coordinates of the irrigable plots
     * @return the coordinates
     */
    public IrrigableCoordinatesSet getIrrigableCoordinates() {
        return irrigableCoordinates;
    }

    /**
     * get the position of the first Row
     * @return the position
     */
    public int getFirstRowPosition() {
        return this.get(0).getRowPosition();
    }

    /**
     * get the position of the last Row
     * @return the position
     */
    public int getLastRowPosition() {
        return this.get(this.size()-1).getRowPosition();
    }


    /**
     * push a plot in the correct row, in the correct position in the row (all ordinate)
     *
     * Using in the else a dichotomy research to put the value on the correct row
     *
     * @param plot
     * @return the list of coordinate able to get a plot (neighbour)
     */
    public void push(Plot plot) {

        int rowPositionPlot = plot.getCoordinate().getX();

        if(this.size()==0) {
            Row row = new Row();
            row.push(plot);
            add(row);
        }
        else {
            if(rowPositionPlot < this.getFirstRowPosition()) {
                ArrayList<Row> rows = new ArrayList<>();
                Row row = new Row(plot);
                rows.add(row);
                rows.addAll(this);
                this.clear();
                this.addAll(rows);
            } else if (rowPositionPlot > this.getLastRowPosition()) {
                this.add(new Row(plot));
            }
            else {
                for (int i = 0; i < this.size(); i++) {
                    if (this.get(i).getRowPosition() == rowPositionPlot) {
                        this.get(i).push(plot);
                        return;
                    }
                }
            }
        }
    }

    /**
     * put a row in the param index with the plot
     *
     * @param plot is the plot which need a new plot
     * @param index is the index in the 'Board - list of Rows'
     */
    public void addRow(Plot plot, int index) {
        Row row = new Row();
        row.push(plot);

        int lastPos = this.size()-1;
        this.add(this.get(lastPos));
        for(int i = lastPos; i>index; i--) {
            this.set(i, this.get(i-1));
        }
        this.set(index, row);
    }

    /**
     * get all the plots of the board
     * @return all the plots
     */
    public List<Plot> allPlots(){
        List<Plot> allPlots = new ArrayList<Plot>();
        for(int i = 0; i < this.size(); i++) {
            allPlots.addAll(this.get(i));
        }
        return allPlots;
    }

    /**
     * get the plot with the coordinate x,y
     * @param x
     * @param y
     * @return the plot
     */
    public Plot findPlot(int x, int y) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getRowPosition() == x) {
                Row row = this.get(i);
                for (int j = 0; j < row.size(); j++) {
                    if (row.get(j).getCoordinate().getY() == y) {
                        return row.get(j);
                    }
                    //break if j>y; csv
                }
            }
            //break if i>x;
        }
        return null;
    }

    /**
     * get the list of plots of the color
     * @param color
     * @return the list of plots
     */
    public List<Plot> getPlotWithColor(Color color) {
        List<Plot> plots = allPlots();
        List<Plot> res = new ArrayList<Plot>();
        for(Plot p : plots) {
            if(p instanceof PlotColor) {
                if (((PlotColor) p).getColor().equals(color)) {
                    if(((PlotColor) p).isIrrigated() && !((PlotColor) p).improvementIsEnclosure())
                        res.add(p);
                }
            }
        }
        return res;
    }

    /**
     * get the list of irrigable plots with the direction
     * @return the hashmap of the plots
     */
    public HashMap<Coordinate, List<IrrigableCoordinates>> getIrrigablePlotWDirections() {
        List<Plot> plots = allPlots();
        List<Coordinate> coosPlots = new ArrayList<>();
        for (Plot p : plots) {
            coosPlots.add(p.getCoordinate());
        }
        HashMap<Coordinate, List<IrrigableCoordinates>> res = new HashMap<Coordinate, List<IrrigableCoordinates>>();
        for (Coordinate coo : coosPlots) {
            List<IrrigableCoordinates> tmpIC = irrigableCoordinates.getICbyCoo(coo);
            if(tmpIC.size() > 0) {
                if (!res.containsKey(coo)) {
                    res.put(coo, tmpIC);
                } else {
                    res.get(coo).addAll(tmpIC);
                }
            }
        }
        return res;
    }

    /**
     * to string method
     * @return the string
     */
    @Override
    public String toString() {
        String res = "Board :\n--------------------------------------------------------------\n";
        for(Row row : this) {
            res += row.toString() + "\n--------------------------------------------------------------\n";
        }
        return res;
    }

    /**
     * remove the irrigable coordinates from the list
     * @param irrigableCoordinate
     */
    public void removeIrrigableCoordinates(IrrigableCoordinates irrigableCoordinate) {
        irrigableCoordinates.remove(irrigableCoordinate);
    }

    /**
     * add irrigable coordinates to the list
     * @param newIrrigable
     */
    public void addIrrigableCoordinates(Set<IrrigableCoordinates> newIrrigable) {
        irrigableCoordinates.addAll(newIrrigable);
    }

    /**
     * method equals
     * @param o the object to be compared for equality with this list
     * @return true if the specified object is equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;

        Board board = (Board) o;

        if (irrigableCoordinates != null ? !irrigableCoordinates.equals(board.irrigableCoordinates) : board.irrigableCoordinates != null)
            return false;
        return super.equals(o);
    }
}
