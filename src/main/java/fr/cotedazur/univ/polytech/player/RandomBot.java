
package fr.cotedazur.univ.polytech.player;

import fr.cotedazur.univ.polytech.board.Board;
import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinates;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.startingpoint.Referee;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.Main.LOGGER;

public class RandomBot extends Player {

    public RandomBot(int number) {
        super(number);
    }

    public void brain(Referee rf, Board bd) {
        int randomDes = (int)(Math.random() * (4 - 0));
        LOGGER.finer("Player n°" + getNumber() + " played : ");

        resetNbAction();

        while(getNbAction() < 2) {
            rf.playCard();

            switch (randomDes) {
                case 0:
                    if (getNbAction() >= 2) {
                        break;
                    }
                    //Cartes Objectifs
                    if (this.getSizeListCards() < 5) {
                        addNbAction();
                        printAction();
                        this.drawnCard(rf);
                    }
                    break;
                case 1:

                    //Pions
                    int rndPawn = choosePawn();
                    switch (rndPawn) {
                        case 0:
                            if (getNbAction() >= 2) {
                                break;
                            }
                            LOGGER.finer("from : " + rf.getPanda().toString());
                            addNbAction();
                            printAction();
                            movePanda(rf);
                            LOGGER.finer("to : " + rf.getPanda().toString());
                            LOGGER.finer("----------------");
                            break;
                        case 1:
                            if (getNbAction() >= 2) {
                                break;
                            }
                            LOGGER.finer("from : " + rf.getGardener().toString());
                            addNbAction();
                            printAction();
                            moveGardener(rf);
                            LOGGER.finer("to : " + rf.getGardener().toString());
                            LOGGER.finer("----------------");
                            break;
                    }
                case 2:
                    if (getNbAction() >= 2) {
                        break;
                    }
                    //Placer un Plot
                    Optional<PlotColor> optPlot = this.playPlot((List<PlotColor>) rf.drawPlot());
                    if (optPlot.isPresent()) {
                        PlotColor plot = optPlot.get();
                        plot.setCoordinate((Coordinate) rf.getPoseablePlace().toArray()[new Random().nextInt(rf.getPoseablePlace().size())]);
                        addNbAction();
                        printAction();
                        rf.pushInBoard(plot);
                    }
                    break;
                case 3:
                    if (getNbAction() >= 2) {
                        break;
                    }
                    int pick = rf.pickIrrigate();
                    if(pick == 0) break;
                    addNbAction();
                    printAction();
                    super.addIrrigation(pick);


                    this.irrigation(rf);
                    break;
            }
        }
    }



/** Method to move Panda pawn
     *
     * @param rf referee
  */



   public void movePanda(Referee rf) {
        Direction direction = Direction.randomDirection();
        int maxDistance = rf.TOOLS.calcDistanceMax(rf.getPanda().getCoordinate(), direction, rf.getBoard());
        int distance = (int)Math.random() * maxDistance + 1;
        rf.movePanda(distance, direction);
    }


    public Optional<PlotColor> playPlot(List<PlotColor> plotColorList) {
        if(plotColorList.size() == 0) return Optional.empty();
        List<PlotColor> tmpPlot = new ArrayList<>();

        PlotColor plot = choosePlot(tmpPlot);
        tmpPlot.remove(plot);

        return Optional.of(plot);
    }




/** Method to move Gardener pawn
     *
     * @param rf referee
  */



    public void moveGardener(Referee rf) {
        Direction direction = Direction.randomDirection();
        int maxDistance = rf.TOOLS.calcDistanceMax(rf.getGardener().getCoordinate(), direction, rf.getBoard());
        int distance = (int)Math.random() * maxDistance + 1;
        rf.moveGardener(distance, direction);
    }

    public void irrigation(Referee rf) {
        HashMap<Coordinate, List<IrrigableCoordinates>> mapCooIC = (HashMap<Coordinate, List<IrrigableCoordinates>>) rf.getIrrigablePlotWDirections();
        List<Coordinate> listKey = mapCooIC.keySet().stream().toList();
        if(listKey.size()>0) {
            Coordinate coo = listKey.get(new Random().nextInt(listKey.size()));
            IrrigableCoordinates ic = mapCooIC.get(coo).get(new Random().nextInt(mapCooIC.get(coo).size()));
            LOGGER.finer("j'irigue le plot au coordonnées " + coo + " sur le coté " + ic.getSideByCoordinate(coo));
            rf.irrigatePlot(ic);
            this.removeIrrigation();
        }
    }





/**
     * Method who draw a card and stock the card in the player

*/


   public void drawnCard(Referee rf) {
       if(this.getSizeListCards() < 5) {
           int random = randomCard();
           if (random == 0){
               LOGGER.finer("Je tire une carte objectif parcelle (Pas encore implémenté)");
           }
           if(random == 1){
               if(rf.getSizeListObjGardener()>0) {
                   this.addCard(rf.drawnCardObjectiveGardener());
                   LOGGER.finer("Je tire une carte objectif jardinier");
               }
           }
           if(random == 2){
               if(rf.getSizeListObjPanda()>0) {
                   this.addCard(rf.drawnCardObjectivePanda());
                   LOGGER.finer("Je tire une carte objectif panda");
               }
           }
       }else {
           LOGGER.finer("Je ne peux pas piocher de carte car j'ai atteint le nombre maximum de carte dans ma main donc je joue une carte");
       }
    }




/**
     * Method who return a random number between 0 and 2 for select card in the drawnCard method
     * @return int random number
*/






 public int randomCard() {
        return (0 + (int)(Math.random() * ((2 - 0) + 1)));
    }

    public int choosePawn() {
        return (new Random()).nextInt(2);
    }
    public Coordinate generateRandomCoordinate() {
        return new Coordinate((int) Math.random()*3 - 1, (int) Math.random()*3 - 1);
    }

    public PlotColor choosePlot(List<PlotColor> tmpPlot) {
        return tmpPlot.get((int)Math.random()*3);
    }

    private void printAction() {
        LOGGER.finer("Action " + this.getNbAction() + "/2 du tour");
    }

}



