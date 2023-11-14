package fr.cotedazur.univ.polytech.plot;

import fr.cotedazur.univ.polytech.object.Bamboo;
import fr.cotedazur.univ.polytech.object.Improvement;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.object.ImprovementType;

import java.util.*;

/**
 * A plot with a color and a bamboo list (can have an improvement)
 */
public class PlotColor extends Plot {
    private Color color;
    private List<Bamboo> bambooList;
    private Optional<Improvement> improvement = Optional.empty();

    /**
     * Constructor of a plot with a color and a bamboo list (can have an improvement)
     * @param coordinate the coordinate of the plot
     * @param color the color of the plot
     */
    public PlotColor(Coordinate coordinate, Color color) {
        super(coordinate);
        this.color = color;
        this.bambooList = new ArrayList<>(4);
        int number = (int) (Math.random() * 50) + 1;
        switch (number) {
            case 1:
                this.improvement = Optional.of(new Improvement(ImprovementType.ENCLOSURE));
                break;
            case 2:
                this.improvement = Optional.of(new Improvement(ImprovementType.FERTILIZER));
                break;
            case 3:
                this.improvement = Optional.of(new Improvement(ImprovementType.WATERSHED));
                break;
            default:
                this.improvement = Optional.empty();
                break;
        }
    }

    /**
     * Constructor of a plot with a color and a bamboo list (can have an improvement)
     * @param coordinate the coordinate of the plot
     * @param color the color of the plot
     * @param i the number of bamboo in the bamboo list
     */
    public PlotColor(Coordinate coordinate, Color color, int i) {
        super(coordinate);
        this.color = color;
        this.bambooList = new ArrayList<>();
        int number = (int) (Math.random() * 50) + 1;
        switch (number) {
            case 1:
                this.improvement = Optional.of(new Improvement(ImprovementType.ENCLOSURE));
                break;
            case 2:
                this.improvement = Optional.of(new Improvement(ImprovementType.FERTILIZER));
                break;
            case 3:
                this.improvement = Optional.of(new Improvement(ImprovementType.WATERSHED));
                break;
            default:
                this.improvement = Optional.empty();
                break;
        }
        for (int j = 0; j < i; j++) {
            bambooList.add(new Bamboo(color));
        }
    }

    /**
     * Get the coordinate of the plot
     * @return the coordinate of the plot
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Set the coordinate of the plot
     * @param coordinate
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Get the color of the plot
     * @return the color of the plot
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set the color of the plot
     * @param pColor the color of the plot
     */
    public void setColor(Color pColor){ color=pColor; }

    /**
     * Get the bamboo list of the plot
     * @return the bamboo list of the plot
     */
    public List<Bamboo> getBambooList() {
        return bambooList;
    }

    /**
     * Get the number of bamboo list of the plot
     * @return number of bamboo list of the plot
     */
    public int getNbBamboo() {
        return bambooList.size();
    }

    /**
     * Add a bamboo to the bamboo list
     * @param bamboo
     */
    public void addBamboo(Bamboo bamboo) {
        bambooList.add(bamboo);
    }

    /**
     * Remove a bamboo from the bamboo list
     * @param bamboo
     */
    public void removeBamboo(Bamboo bamboo) {
        bambooList.remove(bamboo);
    }

    /**
     * Get the size of the bamboo list
     * @return the size of the bamboo list
     */
    public int getSizeBambooList() {
        return bambooList.size();
    }

    /**
     * Get the improvement of the plot
     * @return the improvement of the plot
     */
    public Optional<Improvement> getImprovement() {
        return improvement;
    }

    /**
     * Set the improvement of the plot
     * @param improvement
     */
    public void setImprovement(Improvement improvement) {
        this.improvement = Optional.of(improvement);
    }

    /**
     * Check if the plot has an improvement of type enclosure
     * @return
     */
    public boolean improvementIsEnclosure() {
        if(improvement.isEmpty()) return false;
        if(improvement.get().getType().equals(ImprovementType.ENCLOSURE)) return true;
        return false;
    }

    /**
     * toString method
     * @return a string representing the plot
     */
    @Override
    public String toString() {
        String str = "Plot with color " + color.toString();
        if (improvement != null) {
            str += " and " + improvement.toString();
        }
        if(bambooList.size() > 0) {
            str += " and bamboo list : ";
            for (Bamboo bamboo : bambooList) {
                str += bamboo.toString() + " ";
            }
        }
        if(coordinate != null) {
            str += " at " + coordinate.toString();
        }
        if(this.getIrrigation().size()>0) str +=", Irrigation :" + this.getIrrigation().toString();
        return str;
    }

    /**
     * Check if the plot is irrigated
     * @return true if the plot is irrigated, false otherwise
     */
    @Override
    public boolean isIrrigated() {
        return (!irrigation.isEmpty() || (improvement.isPresent() && improvement.get().getType() == ImprovementType.WATERSHED));
    }


}