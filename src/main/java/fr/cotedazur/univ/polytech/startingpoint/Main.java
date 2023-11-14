package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.JCommander;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import fr.cotedazur.univ.polytech.board.BoardGenerator;
import fr.cotedazur.univ.polytech.card.CardGenerator;
import fr.cotedazur.univ.polytech.card.CardObjectiveGardener;
import fr.cotedazur.univ.polytech.card.CardObjectivePanda;
import fr.cotedazur.univ.polytech.card.CardObjectivePlot;
import fr.cotedazur.univ.polytech.object.BambooGenerator;
import fr.cotedazur.univ.polytech.object.ImprovementGenerator;
import fr.cotedazur.univ.polytech.pawn.Gardener;
import fr.cotedazur.univ.polytech.pawn.Panda;
import fr.cotedazur.univ.polytech.player.Bot;
import fr.cotedazur.univ.polytech.player.Player;
import fr.cotedazur.univ.polytech.player.PlayerGenerator;
import fr.cotedazur.univ.polytech.plot.Etang;
import fr.cotedazur.univ.polytech.plot.PlotColorGenerator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.beust.jcommander.Parameter;

public class Main {
    private final static int NBBOTMVP = 0;
    private final static int NBBOTMAX = 1;
    private final static int NBBOTMIN = 1;
    private final static int NBBOTRANDOM = 0;

    private final static int NBGAMEFORCSV = 20;

    public final static Logger LOGGER = Logger.getLogger(DemoLogger.class.getName());
    private static ConsoleHandler handler = new ConsoleHandler();;

    @Parameter(names = "--demo")
    private static boolean DEMO = false;

    @Parameter(names = "--2thousands")
    private static boolean THOUSAND = false;

    @Parameter(names = "--csv")
    private static boolean CSV = false;



    public static void main(String... argv) throws Exception {
        /*if(argv.length>1) {
            LOGGER.setLevel(Level.SEVERE);
            handler = new ConsoleHandler();
            LOGGER.addHandler(handler);
            LOGGER.severe("ARGUMENTS > 1, can't launch the game");

            return;
        }
        if(argv.length<1) {
            LOGGER.setLevel(Level.SEVERE);
            handler = new ConsoleHandler();
            LOGGER.addHandler(handler);
            LOGGER.severe("ARGUMENTS < 1, can't launch the game");

            return;
        }*/

        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(argv);
        main.run();

    }

    private void run() throws Exception {

        LOGGER.setLevel(Level.OFF);
        handler.setLevel(Level.OFF);


        if(DEMO) {
            //MAIN for GAME
            LOGGER.setLevel(Level.ALL);
            handler.setLevel(Level.ALL);

            for (Handler iHandler : LOGGER.getParent().getHandlers()) {
                LOGGER.getParent().removeHandler(iHandler);
            }
            DemoFormatter formatter = new DemoFormatter();
            handler.setFormatter(formatter);
            LOGGER.addHandler(handler);

            Game game = new Game(LOGGER, 1);
            game.classicRun(NBBOTMAX, NBBOTMIN, NBBOTRANDOM);

        }
        else if(THOUSAND) {

            Game game = new Game(LOGGER, 1000);
            DataGame dg1 = game.synthesisRun(2,2,0);

            game = new Game(LOGGER, 1000);
            DataGame dg2 = game.synthesisRun(0,2,0);

            LOGGER.setLevel(Level.ALL);

            handler.setLevel(Level.ALL);


            for (Handler iHandler : LOGGER.getParent().getHandlers()) {
                LOGGER.getParent().removeHandler(iHandler);
            }
            DemoFormatter formatter = new DemoFormatter();
            handler.setFormatter(formatter);
            LOGGER.addHandler(handler);
            LOGGER.fine(dg1.toString());
            LOGGER.fine("\n");
            LOGGER.fine(dg2.toString());
        }
        else if(CSV) {
            Game game = new Game(LOGGER, NBGAMEFORCSV);
            game.csvRun(NBBOTMAX, NBBOTMIN, NBBOTRANDOM);
        }
    }
}
