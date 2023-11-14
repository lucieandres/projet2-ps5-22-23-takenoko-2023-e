package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.board.Board;
import fr.cotedazur.univ.polytech.board.PoseablePlacePlot;
import fr.cotedazur.univ.polytech.card.CardEmperor;
import fr.cotedazur.univ.polytech.card.CardObjective;
import fr.cotedazur.univ.polytech.card.CardObjectiveGardener;
import fr.cotedazur.univ.polytech.card.CardObjectivePanda;
import fr.cotedazur.univ.polytech.card.CardObjectivePlot;
import fr.cotedazur.univ.polytech.irrigation.IrrigableCoordinates;
import fr.cotedazur.univ.polytech.object.Bamboo;
import fr.cotedazur.univ.polytech.object.Improvement;
import fr.cotedazur.univ.polytech.player.Player;
import fr.cotedazur.univ.polytech.plot.Plot;
import fr.cotedazur.univ.polytech.plot.PlotColor;
import fr.cotedazur.univ.polytech.pawn.Gardener;
import fr.cotedazur.univ.polytech.pawn.Panda;
import fr.cotedazur.univ.polytech.pawn.Pawn;
import fr.cotedazur.univ.polytech.plot.PlotUtilitiesTools;
import fr.cotedazur.univ.polytech.utilities.*;

import java.util.*;

import static fr.cotedazur.univ.polytech.startingpoint.Main.LOGGER;

/**
 * Class which manage the game
 */
public class Referee {
    LinkedList<CardObjectivePanda> listObjPanda;
    LinkedList<CardObjectiveGardener> listObjGarderner;
    List<CardObjectivePlot> listObjPlot;
    List<Player> players;
    public Player currentPlayer;
    private Panda panda;
    private Gardener gardener;
    private Board board;
    private PoseablePlacePlot poseablePlace;
    private LinkedList<PlotColor> plotColorDraw; //pioche
    private LinkedList<Improvement> improvementsDraw; //pioche
    public final static PlotUtilitiesTools TOOLS = new PlotUtilitiesTools();
    private int irrigationdraw = 20;
    private CardEmperor cardEmperor = new CardEmperor();
    private Map<Color, LinkedList<Bamboo>> bambooMap;
    ArrayList<Plot> arrayListPosePlots = new ArrayList<>();
    ArrayList<Plot> arrayListCanPutPlots = new ArrayList<>();

    /**
     * Constructor of the referee
     * @param board
     * @param listObjPanda
     * @param listObjGarderner
     * @param listObjPlot
     * @param panda
     * @param gardener
     * @param players
     * @param poseablePlacePlot
     * @param plotColorList
     * @param improvementsList
     * @param bambooMap
     */
    public Referee(Board board,
                   List<CardObjectivePanda> listObjPanda,
                   List<CardObjectiveGardener> listObjGarderner,
                   List<CardObjectivePlot> listObjPlot,
                   Panda panda,
                   Gardener gardener,
                   List<Player> players,
                   PoseablePlacePlot poseablePlacePlot,
                   LinkedList<PlotColor> plotColorList,
                   LinkedList<Improvement> improvementsList,
                   Map<Color, LinkedList<Bamboo>> bambooMap
                   ) {

        this.players = players;
        this.board = board;
        if(listObjGarderner!=null) this.listObjGarderner = new LinkedList<>(listObjGarderner);
        if(listObjPanda!=null) this.listObjPanda = new LinkedList<>(listObjPanda);
        if(listObjPlot!=null) this.listObjPlot = listObjPlot;
        this.gardener = gardener;
        this.panda = panda;
        this.currentPlayer = players.get(0);
        this.poseablePlace = poseablePlacePlot;
        this.plotColorDraw = plotColorList;
        this.improvementsDraw = improvementsList;
        this.bambooMap = bambooMap;
    }

    /**
     * Method wich run the game
     */
    public void run() {
        Player winner = currentPlayer;
        int finisher = 0;
        int endList = 0;
        while(!cardEmperor.getPlayed()) {
            LOGGER.finer("Nouveau tour !");
            for(Player player : players) {
                if (!player.haveFinish(this)) {
                    currentPlayer = player;
                    LOGGER.finer(board.toString());
                    player.brain(this, board);
                    LOGGER.finer(player.toString());
                } else {
                    this.cardEmperor.setPlayed(true);
                    winner = player;
                    finisher = currentPlayer.getNumber();
                    break;
                }
            }
        }
        for(int i = 0; i<players.size()-1; i++) {
            endList = (finisher+i)%players.size();
            LOGGER.finer("Dernier tour !");
            currentPlayer = players.get(endList);
            players.get(endList).brain(this, board);
            LOGGER.finer(board.toString());
            if(winner.getScore()<players.get(endList).getScore()) {
                winner = players.get(endList);
            }
        }
        LOGGER.finer("Le gagnant est : " + winner.getNumber()+ " avec un score de : " + winner.getScore());

    }

    /**
     * Get the current player
     * @return currentPlayer
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Method which return the size of the list of the objective gardener
     * @return listObjGarderner size
     */
    public int getSizeListObjGardener() {
        return listObjGarderner.size();
    }

    /**
     * Method which return the size of the list of the objective panda
     * @return listObjPanda size
     */
    public int getSizeListObjPanda() {
        return listObjPanda.size();
    }

    /**
     * Method which pick an irrigation
     * @return 1 if the irrigation is picked, 0 if not
     */
    public int pickIrrigate() {
        if(irrigationdraw<=0) return 0;
        --irrigationdraw;
        return 1;
    }

    /**
     * Get the bamboo map
     * @return bambooMap
     */
    public Map<Color, LinkedList<Bamboo>> getBambooMap() {
        return bambooMap;
    }

    /**
     * Get the irrigation draw
     * @return irrigationdraw
     */
    public int getIrrigateDraw() {
        return irrigationdraw;
    }

    /**
     * Get the poseable place of the board
     * @return poseablePlace
     */
    public PoseablePlacePlot getPoseablePlace() {
        return poseablePlace;
    }

    /**
     * Get the PlotColor draw
     * @return plotColorDraw
     */
    public LinkedList<PlotColor> getPlotColorDraw() {
        return plotColorDraw;
    }

    /**
     * Get the improvement draw
     * @return improvementsDraw
     */
    public List<Improvement> getImprovementsDraw() {
        return improvementsDraw;
    }

    /**
     * Get the board
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get the list of players
     * @return players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Get the emperor card
     * @return cardEmperor
     */
    public CardEmperor getCardEmperor() {
        return cardEmperor;
    }

    /**
     * Get the coordinates of the irrigable plots
     * @return set of irrigableCoordinates
     */
    public Set<IrrigableCoordinates> getIrrigableCoordinates() {
        return board.getIrrigableCoordinates();
    }

    /**
     * Push a plot in the board
     * @param plot
     */
    public void pushInBoard(Plot plot) {
        if(!(plot instanceof PlotColor)) return;
        PlotColor cplot = (PlotColor) plot;
        if(poseablePlace.contains(cplot.getCoordinate())) {
            poseablePlace.remove(cplot.getCoordinate());
            cplot.setAllIrrigation(TOOLS.findAdjoiningIrrigated(cplot.getCoordinate(), board));
            if(!cplot.getIrrigation().isEmpty()) {
                if(!bambooMap.get(cplot.getColor()).isEmpty()) cplot.addBamboo(bambooMap.get(cplot.getColor()).pop());
            }
            board.push(cplot);
            poseablePlace.findNewPoseablePlace(cplot, this);
        }
    }

    /**
     * Method which irrigate a plot
     * @param irrigableCoordinate
     */
    public void irrigatePlot(IrrigableCoordinates irrigableCoordinate) {
        for (Coordinate coo : irrigableCoordinate.getCoordinates()) {
            Optional<Plot> optPlot = TOOLS.findPlot(coo, board);
            if(optPlot.isPresent()) {
                Plot plot = optPlot.get();
                LOGGER.finer("irrigation de " + plot + " sur " + irrigableCoordinate.getSideByCoordinate(coo));
                plot.addIrrigation(irrigableCoordinate.getSideByCoordinate(coo));
                if(plot instanceof PlotColor) {
                    PlotColor plotc = (PlotColor) plot;
                    if(bambooMap.get(plotc.getColor()).size() > 0) plotc.addBamboo(bambooMap.get(plotc.getColor()).pop());
                }

                plot.addIrrigation(irrigableCoordinate.getSideByCoordinate(coo));
                if(!(plot instanceof PlotColor)) continue;
                PlotColor plotc = (PlotColor) plot;
                if(bambooMap.get(plotc.getColor()).size() == 0) continue;
                plotc.addBamboo(bambooMap.get(plotc.getColor()).pop());
            }

        }
        board.addIrrigableCoordinates(TOOLS.findNewIrrigable(irrigableCoordinate, board));
        board.removeIrrigableCoordinates(irrigableCoordinate);
    }

    public List<PlotColor> drawPlot() {
        List<PlotColor> res = new ArrayList<>();
        for(int i = 0; i<Math.min(3, plotColorDraw.size()); i++) {
            res.add(plotColorDraw.pop());
        }
        return res;
    }

    /**
     * Get the irrigable plot with the directions
     * @return map of irrigableCoordinates and list of directions
     */
    public Map<Coordinate, List<IrrigableCoordinates>> getIrrigablePlotWDirections() {
        return board.getIrrigablePlotWDirections();
    }

    /**
     * Method which pick a card in the drawn a card objective gardener
     * @return the card picked
     */
    public CardObjective drawnCardObjectiveGardener() {
        return listObjGarderner.pop();
    }

    /**
     * Method which pick a card in the drawn a card objective panda
     * @return the card picked
     */
    public CardObjective drawnCardObjectivePanda() {
        return listObjPanda.pop();
    }

    /**
     * Method which pick a card in the drawn a card objective plot
     * @return the card picked
     */
    public CardObjective drawnCardObjectivePlot() {
        CardObjectivePlot card = listObjPlot.get(0);
        listObjPlot.remove(0);
        return card;
    }

    /**
     * Get the panda
     * @return panda
     */
    public Panda getPanda() {
        return panda;
    }

    /**
     * Set the panda
     * @param panda
     */
    public void setPanda(Panda panda) {
        this.panda = panda;
    }

    /**
     * To move the panda in a direction and a distance
     * @param distance of the movement
     * @param direction of the movement
     */
    public void movePanda(int distance, Direction direction) {
        this.movePawn(panda, distance, direction);
        panda.action(this, TOOLS, board);
    }

    /**
     * To move the panda in a plot
     * @param plot
     */
    public void movePanda(Plot plot) {
        panda.setPlot(plot);
        panda.action(this, TOOLS, board);
    }

    /**
     * Get the gardener
     * @return gardener
     */
    public Gardener getGardener() {
        return gardener;
    }

    /**
     * Set the gardener
     * @param gardener
     */
    public void setGardener(Gardener gardener) {
        this.gardener = gardener;
    }

    /** To move the gardener
     *
     * @param distance of the movement
     * @param direction of the movement
     */
        public void moveGardener(int distance, Direction direction) {
        this.movePawn(gardener, distance, direction);
        gardener.action(TOOLS, board, bambooMap);
    }

    public void moveGardener(Plot plot) {
        gardener.setPlot(plot);
        gardener.action(TOOLS, board, bambooMap);
    }

    /** Method used to move a pawn
     *
     * @param pawn target
     * @param distance distance from where the pawn have to be moved
     * @param direction direction of the movement
     */
    private void movePawn(Pawn pawn, int distance, Direction direction) {
        Coordinate coordinate = TOOLS.newCoordinateWithADirection(pawn.getPlot().getCoordinate(), distance, direction);
        Optional<Plot> optplot = TOOLS.findPlot(coordinate, board);
        if(optplot.isPresent()) pawn.setPlot(optplot.get());
    }

    /**
     * Check if the line is free
     * @param start
     * @param end
     * @return true if the line is free, false otherwise
     */
    public boolean checkLine(Coordinate start, Coordinate end) {
        if(start.getX() != end.getX() && start.getY() == end.getY()) {
            if(checkHole(start, end)) return true;
        }
        if(start.getX() == end.getX() && start.getY() != end.getY()) {
            if(checkHole(start, end)) return true;
        }
        if(start.getX() != end.getX() && start.getY() != end.getY()){
            if(end.getX() + end.getY() == start.getX() + start.getY()) {
                if(checkHole(start, end)) return true;
            }
        }
        if(start.getX() == end.getX() && start.getY() == end.getY()) {
            return true;
        }
        return false;
    }

    /**
     * Check if there is a hole in the line
     * @param start
     * @param end
     * @return true if there is no hole, false otherwise
     */
    public boolean checkHole(Coordinate start, Coordinate end) {
        if(start.getX() > end.getX() && start.getY() < end.getY()){
            if(TOOLS.findPlot(new Coordinate(start.getX() - 1, start.getY() + 1), board).isEmpty()) {
                return false;
            }else{
                return checkHole(new Coordinate(start.getX() - 1, start.getY() + 1), end);
            }
        }else if(start.getX() < end.getX() && start.getY() > end.getY()) {
            if (TOOLS.findPlot(new Coordinate(start.getX() + 1, start.getY() - 1), board).isEmpty()) {
                return false;
            }else{
                return checkHole(new Coordinate(start.getX() + 1, start.getY() - 1), end);
            }
        }else if(start.getX() > end.getX()){
            if(TOOLS.findPlot(new Coordinate(start.getX() - 1, start.getY()), board).isEmpty()) {
                return false;
            }else{
                return checkHole(new Coordinate(start.getX() - 1, start.getY()), end);
            }
        }else if(start.getX() < end.getX()){
            if(TOOLS.findPlot(new Coordinate(start.getX() + 1, start.getY()), board).isEmpty()) {
                return false;
            }else{
                return checkHole(new Coordinate(start.getX() + 1, start.getY()), end);
            }
        }else if(start.getY() < end.getY()){
            if(TOOLS.findPlot(new Coordinate(start.getX(), start.getY() + 1), board).isEmpty()) {
                return false;
            }else{
                return checkHole(new Coordinate(start.getX(), start.getY() + 1), end);
            }
        }else if(start.getY() > end.getY()){
            if(TOOLS.findPlot(new Coordinate(start.getX(), start.getY() - 1), board).isEmpty()) {
                return false;
            }else{
                return checkHole(new Coordinate(start.getX(), start.getY() - 1), end);
            }
        }else if(start.getX() == end.getX() && start.getY() == end.getY()){
            return true;
        }
        return true;
    }

    /**
     * Play a card from a player, used by the referee only to get objective completed by the current player
     */
    public void playCard() {
        if(getCurrentPlayer().getSizeListCards() > 0){
            for(int i = 0; i < getCurrentPlayer().getSizeListCards(); i++){
                if(getCurrentPlayer().getListCards().get(i) instanceof CardObjectivePanda){
                    Color[] colors = ((CardObjectivePanda) getCurrentPlayer().getListCards().get(i)).getTableauBambou();
                    int nbBambooGreen = 0;
                    int nbBambooYellow = 0;
                    int nbBambooPink = 0;
                    for (int j = 0; j < colors.length; j++){
                        if(colors[j] == Color.GREEN){
                            nbBambooGreen++;
                        }
                        if(colors[j] == Color.YELLOW){
                            nbBambooYellow++;
                        }
                        if(colors[j] == Color.PINK){
                            nbBambooPink++;
                        }
                    }

                    List<Bamboo> bamboos = getCurrentPlayer().getListBamboo();
                    int nbBambooGreenPlayer = 0;
                    int nbBambooYellowPlayer = 0;
                    int nbBambooPinkPlayer = 0;
                    for (int j = 0; j < bamboos.size(); j++){
                        if(bamboos.get(j).getColor() == Color.GREEN){
                            nbBambooGreenPlayer++;
                        }
                        if(bamboos.get(j).getColor() == Color.YELLOW){
                            nbBambooYellowPlayer++;
                        }
                        if(bamboos.get(j).getColor() == Color.PINK){
                            nbBambooPinkPlayer++;
                        }
                    }
                    if(nbBambooGreenPlayer >= nbBambooGreen && nbBambooYellowPlayer >= nbBambooYellow && nbBambooPinkPlayer >= nbBambooPink){
                        for(int a = bamboos.size()-1; a >= 0; a--){
                            if(bamboos.get(a).getColor() == Color.GREEN && nbBambooGreen > 0){
                                bambooMap.get(Color.GREEN).add(bamboos.remove(a));
                                nbBambooGreen--;
                            }
                            else if(bamboos.get(a).getColor() == Color.YELLOW && nbBambooYellow > 0){
                                bambooMap.get(Color.YELLOW).add(bamboos.remove(a));
                                nbBambooYellow--;
                            }
                            else if(bamboos.get(a).getColor() == Color.PINK && nbBambooPink > 0){
                                bambooMap.get(Color.PINK).add(bamboos.remove(a));
                                nbBambooPink--;
                            }

                        }

                        getCurrentPlayer().getListCardsPlayed().add(getCurrentPlayer().getListCards().get(i));
                        getCurrentPlayer().getListCards().remove(i);
                        LOGGER.finer("J'ai joué une carte objectif Panda");
                        LOGGER.finer("\n\n");
                        playCard();
                    }
                }
                else if(getCurrentPlayer().getListCards().get(i) instanceof CardObjectiveGardener){

                    Color color = ((CardObjectiveGardener) getCurrentPlayer().getListCards().get(i)).getCouleurBambou();
                    int nbBamboo = ((CardObjectiveGardener) getCurrentPlayer().getListCards().get(i)).getNombreSectionBambou();


                    List<Plot> listPlots = board.allPlots();

                    for(int j = 0; j < listPlots.size(); j++){
                        if(listPlots.get(j) instanceof PlotColor){
                            if(((PlotColor) listPlots.get(j)).getColor() == color){
                                if(((PlotColor) listPlots.get(j)).getSizeBambooList() == nbBamboo){
                                    getCurrentPlayer().getListCardsPlayed().add(getCurrentPlayer().getListCards().remove(i));
                                    LOGGER.finer("J'ai joué une carte objectif Jardinier");
                                    LOGGER.finer("\n\n");
                                    playCard();
                                    break;
                                }
                            }
                        }
                    }
                }
                else if(getCurrentPlayer().getListCards().get(i) instanceof CardObjectivePlot){
                    if(((CardObjectivePlot) getCurrentPlayer().getListCards().get(i)).matchPattern(this.getBoard())){
                        getCurrentPlayer().getListCardsPlayed().add(getCurrentPlayer().getListCards().remove(i));

                        LOGGER.finer("J'ai joué une carte objectif Plot");
                        LOGGER.finer("\n\n");

                        playCard();
                        break;
                    }
                }
            }
        }
    }

    /**
     * Get the winner by number
     * @return the number of the winner
     */
    public int getWinnerByNumber() {
        if(players.size()<2) return 1;
        List<Player> sortedPlayers = new ArrayList<>(players);
        Collections.sort(sortedPlayers, (o1, o2) -> o2.getScore() - o1.getScore());
        if(sortedPlayers.get(0).getScore() == sortedPlayers.get(1).getScore()) return 0;
        return sortedPlayers.get(0).getNumber();
    }


    // Permet de faire la passerelle (arbitrage) entre les joueurs et le plateau
    // Arbitrage des actions des joueurs
}
