package fr.cotedazur.univ.polytech.startingpoint;

import com.opencsv.*;
import fr.cotedazur.univ.polytech.board.BoardGenerator;
import fr.cotedazur.univ.polytech.card.CardGenerator;
import fr.cotedazur.univ.polytech.card.CardObjectiveGardener;
import fr.cotedazur.univ.polytech.card.CardObjectivePanda;
import fr.cotedazur.univ.polytech.card.CardObjectivePlot;
import fr.cotedazur.univ.polytech.object.BambooGenerator;
import fr.cotedazur.univ.polytech.object.ImprovementGenerator;
import fr.cotedazur.univ.polytech.pawn.Gardener;
import fr.cotedazur.univ.polytech.pawn.Panda;
import fr.cotedazur.univ.polytech.player.Player;
import fr.cotedazur.univ.polytech.player.PlayerGenerator;
import fr.cotedazur.univ.polytech.plot.Etang;
import fr.cotedazur.univ.polytech.plot.PlotColorGenerator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private final static int NBPLOTPERTYPE = 9;
    private final static int NBIMPROVEMENTPERTYPE = 3;
    private final static int CARDOBJECTIVEPERTYPE = 27;  //TODO change by default value 15
    public static Logger LOOGGER;
    private int nbPartie;

    private int itteratorParties = 0;

    public Game(Logger logger, int nbPartie) {
        LOOGGER = logger;
        this.nbPartie = nbPartie;

    }

    public void classicRun(int NBBOTMAX, int NBBOTMIN, int NBBOTRANDOM) {
        while(itteratorParties<nbPartie) {
            game((new PlayerGenerator()).generateBot(NBBOTMAX, NBBOTMIN, NBBOTRANDOM));
            itteratorParties++;
        }
    }

    public DataGame synthesisRun(int NBBOTMAX, int NBBOTMIN, int NBBOTRANDOM) {
        int NBPLAYERS = NBBOTMAX + NBBOTMIN + NBBOTRANDOM;

        HashMap<Integer,PlayerResultContainer> playerResultContainerHashMap = new HashMap<>();

        for (int j = 0; j < NBPLAYERS; j++) {
            playerResultContainerHashMap.put(j+1, new PlayerResultContainer());
        }

        List<Player> players = (new PlayerGenerator()).generateBot(NBBOTMAX, NBBOTMIN, NBBOTRANDOM);
        int tie = 0;
        while(itteratorParties<nbPartie) {
            players = (new PlayerGenerator()).generateBot(NBBOTMAX, NBBOTMIN, NBBOTRANDOM);
            Referee tmpref = game(players);

            int playerWinner = tmpref.getWinnerByNumber();
            if(playerWinner == 0) {
                tie ++;
            }
            else {
                playerResultContainerHashMap.get(playerWinner).addVictory();
            }
            for(Player player : tmpref.getPlayers()) {
                playerResultContainerHashMap.get(player.getNumber()).addScore(player.getScore());
            }
            itteratorParties++;
        }

        List<DataGamePlayer> dgp = new ArrayList<>();

        for ( Integer i : playerResultContainerHashMap.keySet()) {
            Player p = players.get(i-1);
            dgp.add(new DataGamePlayer(p, playerResultContainerHashMap.get(i)));
        }
        return new DataGame(dgp, tie, nbPartie);

    }

    public void csvRun(int NBBOTMAX, int NBBOTMIN, int NBBOTRANDOM) throws Exception {
        CSVWriter writer;
        String[] header = "Joueur,Nombre de victoire,Total de points accumules,Pourcentage de victoire".split(",");

        Path currentDir = Paths.get(".\\stats\\");
        Files.createDirectories(currentDir.toAbsolutePath());
        currentDir = Paths.get(".\\stats\\gamestats.csv");

        File file = new File(currentDir.toString());

        boolean notExistedFile = file.createNewFile();
        DataGame dg = synthesisRun(NBBOTMAX, NBBOTMIN, NBBOTRANDOM);

        if(notExistedFile) {
            writer = new CSVWriter(new FileWriter(file, false),';','"','\\',CSVWriter.DEFAULT_LINE_END);
            writer.writeNext(header, false);

            for (DataGamePlayer dgp : dg.getDataGamePlayerList()) {
                float ratio = Integer.valueOf(dgp.getPlayerResultContainer().getVictory()).floatValue() /Integer.valueOf(nbPartie).floatValue();
                String[] s = {dgp.getPlayer().toString(), String.valueOf(dgp.getPlayerResultContainer().getVictory()), String.valueOf(dgp.getPlayerResultContainer().getScore()), String.valueOf(ratio*100)};
                //String s = "Joueur n°"+res + " " + playerResultContainerHashMap.get(res).toString();
                writer.writeNext(s, false);
            }
            writer.writeNext(new String[header.length]);
            String[] s = {"Total de partie", String.valueOf(nbPartie), "Nombre d'egalite", String.valueOf(dg.getTie())};
            writer.writeNext(s, false);

        } else {
            CSVParser csvParser = new CSVParserBuilder().withSeparator(';').withQuoteChar('"').withEscapeChar('\\').build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(csvParser).build();
            List<String[]> allData = reader.readAll();
            List<String[]> sb = new ArrayList<>(allData.size());
            String[] lastScore = allData.get(allData.size()-1);

            reader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(csvParser).build();


            String[] csvline = reader.readNext();
            if(csvline != null && csvline.length == header.length && Arrays.asList(csvline).equals(Arrays.asList(header))) {
                writer = new CSVWriter(new FileWriter(file, false),';','"','\\',CSVWriter.DEFAULT_LINE_END);
                sb.add(csvline);
                csvline = reader.readNext();
                while(!csvline[0].isEmpty()) {
                    int iter = Integer.parseInt(csvline[0].split(" ")[1]);
                    if(dg.getDataGamePlayerList().get(iter-1).toString().contains(csvline[0].split(" ")[0])) {
                        String[] res = new String[header.length];
                        res[0] = csvline[0];
                        res[1] = String.valueOf((Integer.parseInt(csvline[1]) + dg.getDataGamePlayerList().get(iter-1).getPlayerResultContainer().getVictory()));
                        res[2] = String.valueOf((Integer.parseInt(csvline[2]) + dg.getDataGamePlayerList().get(iter-1).getPlayerResultContainer().getScore()));
                        float ratio = Integer.valueOf(res[1]).floatValue() / Integer.valueOf(Integer.valueOf(lastScore[1])+nbPartie).floatValue();
                        res[3] = String.valueOf(ratio*100);
                        sb.add(res);
                    }
                    else {
                        System.out.println("player not found");
                    }
                    csvline = reader.readNext();
                }
                sb.add(csvline);
                csvline = reader.readNext();
                csvline[1] = String.valueOf( Integer.valueOf(csvline[1]) + nbPartie);
                csvline[3] = String.valueOf( Integer.valueOf(csvline[3]) + dg.getTie());
                sb.add(csvline);
                writer.writeAll(sb);
            }
            else {
                throw new IOException("stats cant be recorded, header of csv doesnt match");
            }

        }
        writer.close();

    }

    private Referee game( List<Player> players) {
        List<CardObjectiveGardener> cardObjectiveGardenerList = new ArrayList<CardObjectiveGardener>();
        List<CardObjectivePanda> cardObjectivePandaList = new ArrayList<CardObjectivePanda>();
        List<CardObjectivePlot> cardObjectivePlotList = new ArrayList<CardObjectivePlot>();
        new CardGenerator().generatorCard(CARDOBJECTIVEPERTYPE, cardObjectiveGardenerList, cardObjectivePandaList, cardObjectivePlotList);


        Etang etang = new Etang();
        BoardGenerator boardGen = new BoardGenerator(etang);


        Referee referee = new Referee(boardGen.generateEmptyBoard(), cardObjectivePandaList, cardObjectiveGardenerList, cardObjectivePlotList, new Panda(etang), new Gardener(etang), players, boardGen.generatePoseablePlacePlotForEmptyBoard(), new PlotColorGenerator().generatePlotList(NBPLOTPERTYPE), new ImprovementGenerator().generateImprovement(NBIMPROVEMENTPERTYPE), (new BambooGenerator()).generateMapBamboos());
        referee.run();
        return referee;
    }
}
