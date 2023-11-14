package fr.cotedazur.univ.polytech.player;

import fr.cotedazur.univ.polytech.board.Board;
import fr.cotedazur.univ.polytech.board.PoseablePlacePlot;
import fr.cotedazur.univ.polytech.card.CardObjectiveGardener;
import fr.cotedazur.univ.polytech.card.CardObjectivePanda;
import fr.cotedazur.univ.polytech.object.Bamboo;
import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.startingpoint.Referee;
import fr.cotedazur.univ.polytech.utilities.Color;
import fr.cotedazur.univ.polytech.utilities.Coordinate;
import fr.cotedazur.univ.polytech.utilities.Direction;

import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinates;


import java.util.*;
import java.util.stream.Collectors;

import static fr.cotedazur.univ.polytech.startingpoint.Main.LOGGER;
import static fr.cotedazur.univ.polytech.startingpoint.Referee.TOOLS;

public class Bot extends Player {
    String param;

    public Bot(int number, String param) {
        super(number);
        this.param = param;
    }
    public Bot(int number) {
        super(number);
        this.param = "";
    }

    int numCard = 0;

    public String getParam() {
        return param;
    }

    @Override
    public void brain(Referee rf, Board board) {
    int indexCardHand = 0;

        resetNbAction();

        LOGGER.finer("Player n°" + getNumber() + " played : ");
        while (getNbAction() < 2) {
            Color color = null;
            Color[] colorPanda = null;
            int nbBamboo = 0;




            if (this.getSizeListCards() < 5) {
                if (getNbAction() < 2) {
                    drawnCard(rf);
                    if(param == "max"){
                        Collections.sort(getListCards(), ((o1, o2) -> o2.getScore() - o1.getScore()));
                    } else if (param == "min"){
                        Collections.sort(getListCards(), ((o1, o2) -> o1.getScore() - o2.getScore()));
                    }
                }
            }

            if (indexCardHand < getSizeListCards()) {
                int nbBambooGreen = 0;
                int nbBambooYellow = 0;
                int nbBambooPink = 0;

                int nbBambooGreenPlayer = 0;
                int nbBambooYellowPlayer = 0;
                int nbBambooPinkPlayer = 0;


                if (getListCards().get(indexCardHand) instanceof CardObjectiveGardener) {
                    color = ((CardObjectiveGardener) getListCards().get(indexCardHand)).getCouleurBambou();
                    nbBamboo = ((CardObjectiveGardener) getListCards().get(indexCardHand)).getNombreSectionBambou();
                } else if (getListCards().get(indexCardHand) instanceof CardObjectivePanda) {
                    Color[] colors = ((CardObjectivePanda) this.getListCards().get(indexCardHand)).getTableauBambou();

                    for (int j = 0; j < colors.length; j++) {
                        if (colors[j] == Color.GREEN) {
                            nbBambooGreen++;
                        }
                        if (colors[j] == Color.YELLOW) {
                            nbBambooYellow++;
                        }
                        if (colors[j] == Color.PINK) {
                            nbBambooPink++;
                        }
                    }

                    List<Bamboo> bamboos = this.getListBamboo();

                    for (int j = 0; j < getListBamboo().size(); j++) {
                        if (getListBamboo().get(j).getColor() == Color.GREEN) {
                            nbBambooGreenPlayer++;
                        }
                        if (getListBamboo().get(j).getColor() == Color.YELLOW) {
                            nbBambooYellowPlayer++;
                        }
                        if (getListBamboo().get(j).getColor() == Color.PINK) {
                            nbBambooPinkPlayer++;
                        }
                    }
                }


                List<Plot> plotColor = board.getPlotWithColor(color);

            /*System.out.println("Couleur de la parcelle : " + color);
            System.out.println("Nombre de bambou vert sur la carte : " + nbBambooGreen);
            System.out.println("Nombre de bambou jaune sur la carte : " + nbBambooYellow);
            System.out.println("Nombre de bambou rose sur la carte : " + nbBambooPink);
            System.out.println("\n\nNombre de bambou vert sur le joueur : " + nbBambooGreenPlayer);
            System.out.println("Nombre de bambou jaune sur le joueur : " + nbBambooYellowPlayer);
            System.out.println("Nombre de bambou rose sur le joueur : " + nbBambooPinkPlayer);
            System.out.println("\nCoordonnees du Panda : " + rf.getPanda().getCoordinate());*/

                //System.out.println(board.getPlotWithColor(color));

                if (getListCards().get(indexCardHand) instanceof CardObjectiveGardener) {
                    if (plotColor.size() > 0) {
                        if (getNbAction() >= 2) {
                            break;
                        }
                        boolean isPlay = false;
                        for (Plot p : plotColor) {
                            if (((PlotColor) p).getNbBamboo() >= nbBamboo) {
                                rf.playCard();
                                isPlay = true;
                                break;
                            }
                        }
                        if (!isPlay) {
                            if (getNbAction() >= 2) {
                                break;
                            }

                            if (moveGardener(rf, color)) break;

                        }
                    } else {
                        // choisir un plot sur les 3 piochés et un endroit ou le poser
                        if (getNbAction() >= 2) {
                            break;
                        }
                        if (playAPlot(rf, color)) return;
                    }
                } else if (getListCards().get(indexCardHand) instanceof CardObjectivePanda) {
                    if (nbBambooGreenPlayer >= nbBambooGreen && nbBambooYellowPlayer >= nbBambooYellow && nbBambooPinkPlayer >= nbBambooPink) {
                        rf.playCard();
                    } else {
                        if (nbBambooGreen > 0) {
                            if(nbBambooGreenPlayer < nbBambooGreen) {

                                if (getNbAction() >= 2) {
                                    break;
                                }

                                LOGGER.finer("Je cherche une parcelle verte");
                                List<Plot> plotColorGreen = board.getPlotWithColor(Color.GREEN);


                                Plot maxBamboo = null;
                                for (Plot p : plotColorGreen) {
                                    if (((PlotColor) p).isIrrigated() && !((PlotColor) p).improvementIsEnclosure()) {
                                        if (maxBamboo == null) {
                                            maxBamboo = p;
                                        } else if (((PlotColor) p).getNbBamboo() > ((PlotColor) maxBamboo).getNbBamboo()) {
                                            maxBamboo = p;
                                        }
                                    }

                                }
                                if (maxBamboo == null || ((PlotColor) maxBamboo).getBambooList().isEmpty()) {
                                    if (!irrigate(rf, Color.GREEN)) {
                                        if (!playAPlot(rf, Color.GREEN)) {
                                            other(rf, Color.GREEN);
                                        }
                                    }
                                } else {
                                    Coordinate maxC = maxBamboo.getCoordinate();
                                    Coordinate panC = rf.getPanda().getCoordinate();

                                    if (rf.checkLine(panC, maxC)) {
                                        if (getNbAction() >= 2) {
                                            break;
                                        }
                                        addNbAction();
                                        printAction();
                                        LOGGER.finer("Le panda ce déplace vers la parcelle " + maxBamboo.getCoordinate());
                                        rf.movePanda(maxBamboo);
                                    } else {
                                        if (getNbAction() >= 2) {
                                            break;
                                        }
                                        Direction dir = panC.getDirectionToReachCoo(maxC);
                                        LOGGER.finer("Le panda ce déplace vers la direction " + dir);
                                        if (pathFinding(dir, panC, maxC, rf, "Panda")) {
                                            if (getNbAction() >= 2) {
                                                break;
                                            }
                                            addNbAction();
                                            printAction();
                                            rf.movePanda(maxBamboo);
                                        }


                                    }

                                }
                            }

                        }
                        else {
                            moveGardener(rf, color);
                        }

                        if (nbBambooYellow > 0) {
                            if(nbBambooYellowPlayer < nbBambooYellow) {

                            if (getNbAction() >= 2) {
                                break;
                            }

                            LOGGER.finer("Je cherche une parcelle jaune");
                            List<Plot> plotColorYellow = board.getPlotWithColor(Color.YELLOW);


                            Plot maxBamboo = null;
                            for (Plot p : plotColorYellow) {
                                if (((PlotColor) p).isIrrigated() && !((PlotColor) p).improvementIsEnclosure()) {
                                    if (maxBamboo == null) {
                                        maxBamboo = p;
                                    } else if (((PlotColor) p).getNbBamboo() > ((PlotColor) maxBamboo).getNbBamboo()) {
                                        maxBamboo = p;
                                    }
                                }

                            }
                            if (maxBamboo == null || ((PlotColor) maxBamboo).getBambooList().isEmpty()) {
                                if (!irrigate(rf, Color.YELLOW)) {
                                    if (!playAPlot(rf, Color.YELLOW)) {
                                        other(rf, Color.YELLOW);
                                    }
                                }
                            } else {
                                Coordinate maxC = maxBamboo.getCoordinate();
                                Coordinate panC = rf.getPanda().getCoordinate();

                                if (rf.checkLine(panC, maxC)) {
                                    if (getNbAction() >= 2) {
                                        break;
                                    }
                                    addNbAction();
                                    printAction();
                                    LOGGER.finer("Le panda ce déplace vers la parcelle " + maxBamboo.getCoordinate());
                                    rf.movePanda(maxBamboo);
                                } else {
                                    if (getNbAction() >= 2) {
                                        break;
                                    }
                                    Direction dir = panC.getDirectionToReachCoo(maxC);
                                    LOGGER.finer("Le panda ce déplace vers la direction " + dir);
                                    if (pathFinding(dir, panC, maxC, rf, "Panda")) {
                                        if (getNbAction() >= 2) {
                                            break;
                                        }
                                        addNbAction();
                                        printAction();
                                        rf.movePanda(maxBamboo);
                                    }
                                }
                            }


                        }
                        }
                        else {
                            moveGardener(rf, color);
                        }

                        if (nbBambooPink > 0) {
                            if (nbBambooPinkPlayer < nbBambooPink) {

                                if (getNbAction() >= 2) {
                                    break;
                                }

                                LOGGER.finer("Je cherche une parcelle rose");
                                List<Plot> plotColorPink = board.getPlotWithColor(Color.PINK);


                                Plot maxBamboo = null;
                                for (Plot p : plotColorPink) {
                                    if (((PlotColor) p).isIrrigated() && !((PlotColor) p).improvementIsEnclosure()) {
                                        if (maxBamboo == null) {
                                            maxBamboo = p;
                                        } else if (((PlotColor) p).getNbBamboo() > ((PlotColor) maxBamboo).getNbBamboo()) {
                                            maxBamboo = p;
                                        }
                                    }

                                }
                                if (maxBamboo == null || ((PlotColor) maxBamboo).getBambooList().isEmpty()) {
                                    if (!irrigate(rf, Color.PINK)) {
                                        if (!playAPlot(rf, Color.PINK)) {
                                            other(rf, Color.PINK);
                                        }
                                    }
                                } else {
                                    Coordinate maxC = maxBamboo.getCoordinate();
                                    Coordinate panC = rf.getPanda().getCoordinate();

                                    if (rf.checkLine(panC, maxC)) {
                                        if (getNbAction() >= 2) {
                                            break;
                                        }
                                        addNbAction();
                                        printAction();
                                        LOGGER.finer("Le panda ce déplace vers la parcelle " + maxBamboo.getCoordinate());
                                        rf.movePanda(maxBamboo);
                                    } else {
                                        if (getNbAction() >= 2) {
                                            break;
                                        }
                                        Direction dir = panC.getDirectionToReachCoo(maxC);
                                        LOGGER.finer("Le panda ce déplace vers la direction " + dir);
                                        if (pathFinding(dir, panC, maxC, rf, "Panda")) {
                                            if (getNbAction() >= 2) {
                                                break;
                                            }
                                            addNbAction();
                                            printAction();
                                            rf.movePanda(maxBamboo);
                                        }
                                    }
                                }


                            }
                        }
                        else {
                            moveGardener(rf, color);
                        }
                    }
                }
                /*else if (getListCards().get(indexCardHand) instanceof CardObjectivePlot) {
                    if (getNbAction() >= 2) {
                        break;
                    }
                    addNbAction();
                    printAction();
                    rf.playCard();
                }*/
                if (getNbAction() < 2) {
                    indexCardHand++;
                }
                rf.playCard();
            }
            else {
                moveGardener(rf);
                addNbAction();
                printAction();
            }
        }
    }
    public void moveGardener(Referee rf) {
        Direction direction = Direction.randomDirection();
        int maxDistance = rf.TOOLS.calcDistanceMax(rf.getGardener().getCoordinate(), direction, rf.getBoard());
        int distance = (int)Math.random() * maxDistance + 1;
        rf.moveGardener(distance, direction);
    }
    private boolean moveGardener(Referee rf, Color color) {
        List<Plot> plotColor = rf.getBoard().getPlotWithColor(color);
        Plot maxBamboo = null;
        for (Plot p : plotColor) {
            if (((PlotColor) p).isIrrigated()) {
                if(maxBamboo == null) {
                    maxBamboo = p;
                }
                else if (((PlotColor) p).getNbBamboo() > ((PlotColor) maxBamboo).getNbBamboo()) {
                    maxBamboo = p;
                }
            }

        }
        if (maxBamboo != null) {
            Coordinate maxC = maxBamboo.getCoordinate();
            Coordinate garC = rf.getGardener().getCoordinate();

            if (rf.checkLine(garC, maxC)) {
                if (getNbAction() >= 2) {
                    return true;
                }
                addNbAction();
                printAction();
                LOGGER.finer("Le Jardinier se déplace vers la parcelle " + maxBamboo.getCoordinate());
                rf.moveGardener(maxBamboo);
            } else {
                if (getNbAction() >= 2) {
                    return true;
                }
                Direction dir = garC.getDirectionToReachCoo(maxC);
                LOGGER.finer("Le Jardinier déplace vers la direction " + dir);
                if (pathFinding(dir, garC, maxC, rf, "Jardinier")) {
                    if (getNbAction() >= 2) {
                        return true;
                    }
                    addNbAction();
                    printAction();
                    rf.moveGardener(maxBamboo);
                }


            }

        } else {
            if (!irrigate(rf, color)) {
                playAPlot(rf, color);
            }
        }
        return false;
    }

    private boolean playAPlot(Referee rf, Color color) {

        LinkedList<PlotColor> drawPlots = rf.getPlotColorDraw();
        Optional<PlotColor> pcopt = choosePlot(drawPlots, color);
        Optional<Coordinate> cooOpt = chooseCoordinateForPlot(rf, color);

        if (pcopt.isPresent() && cooOpt.isPresent()) {
            PlotColor pco = pcopt.get();
            pco.setCoordinate(cooOpt.get());
            rf.pushInBoard(pco);
            addNbAction();
            printAction();
            LOGGER.finer("Je pose la parcelle : " + pco.getCoordinate());

            if (getIrrigation() > 0) {

                HashMap<Coordinate, List<IrrigableCoordinates>> mapCooIrrigable = (HashMap<Coordinate, List<IrrigableCoordinates>>) rf.getIrrigablePlotWDirections();
                if (mapCooIrrigable.containsKey(cooOpt)) {
                    int reward = 0;
                    IrrigableCoordinates bestic = mapCooIrrigable.get(cooOpt).get(0);
                    for (IrrigableCoordinates ic : mapCooIrrigable.get(cooOpt)) {
                        int currentreward = 0;

                        //System.out.println("\u001B[31m"+ ic + "\u001B[0m");
                        for (Coordinate coo : ic.getCoordinates()) {
                            Optional<Plot> optplot = TOOLS.findPlot(coo, rf.getBoard());
                            if (optplot.isPresent() && optplot.get() instanceof PlotColor) {
                                PlotColor pc = (PlotColor) optplot.get();
                                if (pc.getColor() == color) currentreward += 2;
                                if (pc.isIrrigated()) currentreward--;
                            }
                            //System.out.println("\u001B[31m"+ currentreward + "\u001B[0m");
                        }
                        if (currentreward > reward) {
                            reward = currentreward;
                            bestic = ic;
                        }
                        if (reward >= 4) {
                            rf.irrigatePlot(ic);
                            removeIrrigation();
                        }
                    }

                    //System.out.println("\u001B[31m"+ bestic + "\u001B[0m");
                    rf.irrigatePlot(bestic);
                    removeIrrigation();
                }
            } else {
                if(getNbAction() >= 2){
                    return true;
                }
                addIrrigation(rf.pickIrrigate());
                addNbAction();
                printAction();
                LOGGER.finer("Je pioche une irrigation");
                if (rf.getIrrigateDraw() > 0) {
                    List<PlotColor> noIrrigatePlot = TOOLS.findNoIrrigatePlot(rf.getBoard());
                    List<PlotColor> coordinatesMatchesColor = TOOLS.findMatchesColorPlots(color, rf.getBoard());
                    Set<PlotColor> coordinatesMatchesColorNoIrrigated = noIrrigatePlot.stream().distinct().filter(coordinatesMatchesColor::contains).collect(Collectors.toSet());
                    if (!coordinatesMatchesColorNoIrrigated.isEmpty()) {
                        for (PlotColor pc : coordinatesMatchesColorNoIrrigated) {
                            if(irrigate(rf, color)) {
                                return true;
                            }
                            other(rf, color);

                        }
                    } else {
                        other(rf, color);
                    }
                }


            }
        }
        return false;
    }
    private boolean irrigate(Referee rf, Color color) {

        LOGGER.finer("\u001B[31m"+ "Irrigating ! " + "\u001B[0m");
        if(getNbAction()<2){
            if(getIrrigation()==0) {
                if(rf.getIrrigateDraw()>0) {
                    addIrrigation(rf.pickIrrigate());
                    addNbAction();
                    printAction();
                }
            }
        }
        //Classic irrigation in first if
        if (getIrrigation() > 0) {
            List<PlotColor> noIrrigatePlot = TOOLS.findNoIrrigatePlot(rf.getBoard());
            List<PlotColor> coordinatesMatchesColor = TOOLS.findMatchesColorPlots(color, rf.getBoard());
            Set<PlotColor> coordinatesMatchesColorNoIrrigated = noIrrigatePlot.stream().distinct().filter(coordinatesMatchesColor::contains).collect(Collectors.toSet());
            HashMap<Coordinate, List<IrrigableCoordinates>> mapCooIrrigable = (HashMap<Coordinate, List<IrrigableCoordinates>>) rf.getIrrigablePlotWDirections();
            if (!coordinatesMatchesColorNoIrrigated.isEmpty()) {
                for (PlotColor pc : coordinatesMatchesColorNoIrrigated) {
                    if (mapCooIrrigable.containsKey(pc.getCoordinate())) {
                        LOGGER.finer("\u001B[31m"+ "color & irrigated ! " + "\u001B[0m");
                        rf.irrigatePlot(mapCooIrrigable.get(pc.getCoordinate()).get(0));
                        removeIrrigation();
                        return true;
                    }
                }
                //find a neighbour of coordinates who matches with color and is not irrigate
                for (PlotColor pc : coordinatesMatchesColorNoIrrigated) {
                    List<Plot> neighbours = TOOLS.findNeighbourPlot(pc.getCoordinate(), rf.getBoard());
                    for (Plot np : neighbours) {
                        if (np instanceof PlotColor) {
                            PlotColor npc = (PlotColor) np;
                            if (mapCooIrrigable.containsKey(npc.getCoordinate())) {
                                LOGGER.finer("\u001B[31m"+ "irrigated ! " + "\u001B[0m");
                                rf.irrigatePlot(mapCooIrrigable.get(npc.getCoordinate()).get(0));
                                removeIrrigation();
                                return true;
                            }
                        }
                    }
                }
                //if we do not find a neighbour than didn't irrigable on all plot of this color

                for (PlotColor pc : coordinatesMatchesColorNoIrrigated) {
                    Coordinate tmpCoo = pc.getCoordinate();
                    Direction dirTmp = tmpCoo.getDirectionToReachCoo(rf.getBoard().getEtang().getCoordinate());
                    while (!tmpCoo.equals(rf.getBoard().getEtang().getCoordinate())) {
                        Optional<Plot> optPlot = TOOLS.findPlot(TOOLS.newCoordinateWithADirection(tmpCoo, 1, dirTmp), rf.getBoard());
                        if (optPlot.isPresent() && optPlot.get() instanceof PlotColor) {
                            PlotColor tmppc = (PlotColor) optPlot.get();
                            if (mapCooIrrigable.containsKey(tmppc.getCoordinate())) {
                                LOGGER.finer("\u001B[31m"+ "neighbour irrigated ! " + "\u001B[0m");
                                rf.irrigatePlot(mapCooIrrigable.get(tmppc.getCoordinate()).get(0));
                                removeIrrigation();
                                return true;
                            } else {
                                tmpCoo = optPlot.get().getCoordinate();
                                dirTmp = tmpCoo.getDirectionToReachCoo(rf.getBoard().getEtang().getCoordinate());
                            }
                        } else break;
                    }
                }
            }
        }
        return false;
    }
    private void other(Referee rf, Color color) {
        if (getListCards().size() < 5) {
            if (rf.getSizeListObjGardener() > 0) {
                if(!(getNbAction() >= 2)){
                    addNbAction();
                    printAction();
                    rf.drawnCardObjectiveGardener();
                }

            }
        } else {
            if(!movePandaToTakeABamboo(rf)) {
                if(moveGardener(rf, color)) {
                    moveGardener(rf);
                    addNbAction();
                    printAction();
                    LOGGER.finer("OTHER");
                }
            }
        }
    }

    /**
     *
     * @param rf
     * @return
     */
    //TODO check the method if this is working
    private boolean movePandaToTakeABamboo(Referee rf) {
        for (Direction dir : Direction.values()) {
            List<Plot> plots = TOOLS.getListPlotInDirection(rf.getPanda().getCoordinate(), dir, rf.getBoard());
            if (plots.size() > 0) {
                for (int i = 0; i < plots.size(); i++) {
                    if (plots.get(i) instanceof PlotColor) {
                        PlotColor pc = (PlotColor) plots.get(i);
                        if (pc.getBambooList().size() > 0) {
                            if(!(getNbAction() >= 2)){
                                addNbAction();
                                printAction();
                                rf.movePanda(i, dir);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    private Optional<Coordinate> chooseCoordinateForPlot(Referee referee, Color color) {
        Coordinate coo;
        float distanceFloat;
        PoseablePlacePlot posPP = referee.getPoseablePlace();
        Set<Coordinate> setCooIrrigateAndSameColor = new HashSet<>();
        Set<Coordinate> setCooIrrigate = new HashSet<>();
        Set<Coordinate> setCooSameColor = new HashSet<>();

        for (Coordinate coPP : posPP) {
            List<Plot> neighbour = TOOLS.findNeighbourPlot(coPP, referee.getBoard());

            for (Plot p : neighbour) {
                int increm = 0;
                if (p.getClass() == PlotColor.class) {
                    if (p.isIrrigated()) increm++;
                    if (((PlotColor) p).getColor() == color) increm += 2;
                }
                if (increm == 1) {
                    setCooSameColor.add(coPP);
                } else if (increm == 2) {
                    setCooIrrigate.add(coPP);
                } else if (increm == 3) {
                    setCooIrrigateAndSameColor.add(coPP);
                    setCooIrrigate.add(coPP);
                    setCooSameColor.add(coPP);
                }
            }
        }

        if (setCooIrrigateAndSameColor.size() > 0) {
            //System.out.println("\u001B[31m"+ "color and irrigate" + "\u001B[0m");
            return Optional.of(getBestCooMinDist(setCooIrrigateAndSameColor, referee.getGardener().getCoordinate()));
        }
        if (setCooSameColor.size() > 0) {
            //System.out.println("\u001B[31m"+ "color" + "\u001B[0m");
            return Optional.of(getBestCooMinDist(setCooSameColor, referee.getGardener().getCoordinate()));
        }
        if (setCooIrrigate.size() > 0) {
            //System.out.println("\u001B[31m"+ "irrigate" + "\u001B[0m");
            return Optional.of(getBestCooMinDist(setCooIrrigate, referee.getGardener().getCoordinate()));
        }
        if (referee.getBoard().allPlots().size() < 5) {
            //System.out.println("\u001B[31m"+ "size" + "\u001B[0m");
            return Optional.of(getBestCooMinDist(posPP, referee.getGardener().getCoordinate()));
        }
        return Optional.of(referee.getPoseablePlace().stream().toList().get(0));
    }

    public float calculateDistance(Coordinate coo1, Coordinate coo2) {
        return (Math.abs(coo1.getX() - coo2.getX())
                + Math.abs(coo1.getX() + coo1.getY() - coo2.getX() - coo2.getY())
                + Math.abs(coo1.getY() - coo2.getY())) / 2;
    }

    public Coordinate getBestCooMinDist(Set<Coordinate> coordinates, Coordinate coo) {
        Coordinate coor = coordinates.stream().toList().get(0);
        float distanceFloat = calculateDistance(coor, coo);
        for (Coordinate cos : coordinates) {
            float tmpF = calculateDistance(cos, coo);
            if (distanceFloat > tmpF) {
                distanceFloat = tmpF;
                coor = cos;
            }
        }
        return coor;
    }

    private Optional<PlotColor> choosePlot(LinkedList<PlotColor> plotColorList, Color color) {
        if (plotColorList.isEmpty()) return Optional.empty();
        List<PlotColor> tmpPlot = new ArrayList<>();
        for (int i = 0; i < Math.min(plotColorList.size(), 3); i++) {
            tmpPlot.add(plotColorList.pop());
        }

        PlotColor plot = null;
        for (PlotColor p : tmpPlot) {
            if (p.getColor() == color) {
                plot = p;
                break;
            }
        }
        if (plot == null) {
            plot = tmpPlot.get(0);
        }

        tmpPlot.remove(plot);
        for (PlotColor p : tmpPlot) {
            plotColorList.push(p);
        }

        return Optional.of(plot);
    }

    public void drawnCard(Referee rf) {
        if(numCard == 0){
            if (this.getSizeListCards() < 5) {
                if(rf.getSizeListObjGardener()==0){
                }
                else {
                    this.addCard(rf.drawnCardObjectiveGardener());
                    addNbAction();
                    printAction();
                    LOGGER.finer("Je tire une carte objectif jardinier");
                }
                numCard++;
            } else {
                LOGGER.finer("Je ne peux pas piocher de carte car j'ai atteint le nombre maximum de carte dans ma main donc je joue une carte");
            }
        } else if (numCard == 1) {
                if (this.getSizeListCards() < 5) {
                this.addCard(rf.drawnCardObjectivePanda());
                addNbAction();
                printAction();
                LOGGER.finer("Je tire une carte objectif Panda");
                numCard = 0;
            } else {
                LOGGER.finer("Je ne peux pas piocher de carte car j'ai atteint le nombre maximum de carte dans ma main donc je joue une carte");
            }
        } else if (numCard == 2) {
            if (this.getSizeListCards() < 5) {
                this.addCard(rf.drawnCardObjectivePlot());
                addNbAction();
                printAction();
                LOGGER.finer("Je tire une carte objectif Parcelle");
                numCard = 0;
            } else {
                LOGGER.finer("Je ne peux pas piocher de carte car j'ai atteint le nombre maximum de carte dans ma main donc je joue une carte");
            }
        }
    }

    public int returnPositive(int i) {
        if (i < 0) return -i;
            return i;
    }

    public boolean pathFinding(Direction dir, Coordinate garC, Coordinate maxC, Referee rf, String type){
        if(getNbAction() >= 2){
            return false;
        }
        int nbDepl = 0;

        if(dir == Direction.MZ){
            nbDepl = returnPositive(garC.getX()) + returnPositive(maxC.getX());
            if(rf.checkLine(garC,new Coordinate(garC.getX() + nbDepl, garC.getY() - nbDepl))){

                addNbAction();
                printAction();
                if(type.equals("Jardinier")) {
                    rf.moveGardener(nbDepl,dir);
                }else if(type.equals("Panda")){
                    rf.movePanda(nbDepl,dir);
                }

                return true;
            }
        }
        if(dir == Direction.Z){
            nbDepl = returnPositive(garC.getX()) + returnPositive(maxC.getX());
            if(rf.checkLine(garC,new Coordinate(garC.getX() - nbDepl, garC.getY() + nbDepl))){
                addNbAction();
                printAction();
                if(type.equals("Jardinier")) {
                    rf.moveGardener(nbDepl,dir);
                }else if(type.equals("Panda")){
                    rf.movePanda(nbDepl,dir);
                }
                return true;
            }
        }
        if (dir == Direction.X) {
            nbDepl = returnPositive(garC.getX()) + returnPositive(maxC.getX());
            if(rf.checkLine(garC,new Coordinate(garC.getX() - nbDepl, garC.getY()))){
                addNbAction();
                printAction();
                if(type.equals("Jardinier")) {
                    rf.moveGardener(nbDepl,dir);
                }else if(type.equals("Panda")){
                    rf.movePanda(nbDepl,dir);
                }
                return true;
            }
        }
        if (dir == Direction.MX) {
            nbDepl = returnPositive(garC.getX()) + returnPositive(maxC.getX());
            if(rf.checkLine(garC,new Coordinate(garC.getX() + nbDepl, garC.getY()))){
                addNbAction();
                printAction();
                if(type.equals("Jardinier")) {
                    rf.moveGardener(nbDepl,dir);
                }else if(type.equals("Panda")){
                    rf.movePanda(nbDepl,dir);
                }
                return true;
            }
        }
        if (dir == Direction.Y) {
            nbDepl = returnPositive(garC.getY()) + returnPositive(maxC.getY());
            if(rf.checkLine(garC,new Coordinate(garC.getX(), garC.getY() + nbDepl))){
                addNbAction();
                printAction();
                if(type.equals("Jardinier")) {
                    rf.moveGardener(nbDepl,dir);
                }else if(type.equals("Panda")){
                    rf.movePanda(nbDepl,dir);
                }
                return true;
            }
        }
        if (dir == Direction.MY) {
            nbDepl = returnPositive(garC.getY()) + returnPositive(maxC.getY());
            if(rf.checkLine(garC,new Coordinate(garC.getX(), garC.getY() - nbDepl))){
                addNbAction();
                printAction();
                if(type.equals("Jardinier")) {
                    rf.moveGardener(nbDepl,dir);
                }else if(type.equals("Panda")){
                    rf.movePanda(nbDepl,dir);
                }
                return true;
            }
        }
        return false;
    }


    private void printAction() {
        LOGGER.finer("Action " + this.getNbAction() + "/2 du tour");
    }

    //@Override
    public PlotColor choosePlot(List<PlotColor> tmpPlot) {
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "_" + this.getParam() + " " + getNumber();
    }
}


