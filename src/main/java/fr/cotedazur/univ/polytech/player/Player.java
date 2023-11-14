package fr.cotedazur.univ.polytech.player;

import fr.cotedazur.univ.polytech.board.Board;
import fr.cotedazur.univ.polytech.card.CardObjective;
import fr.cotedazur.univ.polytech.dice.*;
import fr.cotedazur.univ.polytech.object.Bamboo;
import fr.cotedazur.univ.polytech.object.Improvement;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.startingpoint.Referee;
import fr.cotedazur.univ.polytech.utilities.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class Player {

    private int number;
    private int stockIrrigation;
    private List<CardObjective> ListCards;
    private List<CardObjective> ListCardsPlayed;
    private List<Bamboo> ListBamboo;
    private List<Improvement> ListImprovement;
    private boolean haveFinish;
    private int nbAction;


    public Player(int number) {
        this.number = number;
        stockIrrigation = 0;
        ListCards = new ArrayList<CardObjective>();
        ListCardsPlayed = new ArrayList<CardObjective>();
        ListBamboo = new ArrayList<Bamboo>();
        ListImprovement = new ArrayList<Improvement>();
        this.haveFinish = false;
    }

    public int getNumber() {
        return number;
    }
    public void addCard(CardObjective c) {
        ListCards.add(c);
    }

    public void addCardPlayed(CardObjective c) {
        ListCardsPlayed.add(c);
    }

    public void addBamboo(Bamboo b) {
        ListBamboo.add(b);
    }

    public void addImprovement(Improvement i) { ListImprovement.add(i); }

    public void addIrrigation(int i) {
        stockIrrigation += i;
    }
    public int removeIrrigation() {
        if(stockIrrigation>0) {
            stockIrrigation--;
            return 1;
        }
            return 0;
    }

    public void setHaveFinish (boolean b) {
        this.haveFinish = b;
    }


    public List<Bamboo> getListBamboo() {
        return ListBamboo;
    }

    public int getIrrigation() {
        return stockIrrigation;
    }


    public int getSizeListCards() {
        return ListCards.size();
    }

    public int getSizeListCardsPlayed() {
        return ListCardsPlayed.size();
    }

    public List<CardObjective> getListCards() {
        return ListCards;
    }
    public List<CardObjective> getListCardsPlayed() {
        return ListCardsPlayed;
    }

    public int getScore() {
        int score = 0;
        for (CardObjective c : ListCardsPlayed) {
            score += c.getScore();
        }
        return score;
    }

    public boolean haveFinish(Referee rf) {
        int objective = 7;
        switch (rf.getPlayers().size()) {
            case 2:
                objective = 9;
                break;
            case 3:
                objective = 8;
                break;
            default:

        }
        if (ListCardsPlayed.size() >= objective) {
            this.setHaveFinish(true);
            ListCardsPlayed.add(rf.getCardEmperor());
        }
        return this.haveFinish;
    }

    public void resetNbAction(){
        nbAction = 0;
    }
    public void addNbAction() {
        nbAction += 1;
    }

    public int getNbAction() { return nbAction;}



    public abstract void brain(Referee rf, Board board);

    public String showListImprovement() {
        String result = "[";
        for (Improvement i : ListImprovement) {
            result += i.toString() + ", ";
        }
        result += "]";
        return result;
    }

    public String showListCards() {
        String result = "[";
        for (CardObjective c : ListCards) {
            result += c.toString() + ", ";
        }
        result += "]";
        return result;
    }

    public String showListCardsPlayed() {
        String result = "[";
        for (CardObjective c : ListCardsPlayed) {
            result += c.toString() + ", ";
        }
        result += "]";
        return result;
    }

    public String showListBamboo() {
        String result = "[";
        for (Bamboo b : ListBamboo) {
            result += b.toString() + ", ";
        }
        result += "]";
        return result;
    }

    public String toString() {
        return "Player "+number;
    }

    /*public Optional<PlotColor> playPlot(LinkedList<PlotColor> plotColorList) {
        if(plotColorList.size() == 0) return Optional.empty();
        List<PlotColor> tmpPlot = new ArrayList<>();
        for(int i = 0; i<Math.min(plotColorList.size(), 3); i++) {
            tmpPlot.add(plotColorList.pop());
        }

        PlotColor plot = choosePlot(tmpPlot);
        tmpPlot.remove(plot);
        for(PlotColor p : tmpPlot) {
            plotColorList.push(p);
        }

        return Optional.of(plot);
    }*/

    public void pickIrrigation(Referee referee) {
        stockIrrigation += referee.pickIrrigate();
    }
}
