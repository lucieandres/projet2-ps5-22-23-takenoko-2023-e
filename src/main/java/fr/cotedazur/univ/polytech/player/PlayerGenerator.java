package fr.cotedazur.univ.polytech.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerGenerator {
    /** generate a list of nbOfPlayers random players
     *
     * @param nbBotMax is the number of bot who play card with max score point
     * @param nbBotMin is the number of bot who play card with min score point
     * @return the list of bot
     */
   /* public List<Player> generateRandom(int nbOfPlayers) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < nbOfPlayers; i++) {
            players.add(new RandomBot(i+1));
        }
        return players;
    }*/

    public List<Player> generateBot(int nbBotMax, int nbBotMin, int nbBotRandom) {
        List<Player> players = new ArrayList<>();
        if (nbBotMax + nbBotMin > 4) {
            throw new IllegalArgumentException("too much bot");
        }
        if(nbBotMax > 0) {
            for (int i = 0; i < nbBotMax; i++) {
                players.add(new Bot(i+1,"max"));
            }
        }
        if(nbBotMin > 0) {
            for (int i = 0; i < nbBotMin; i++) {
                players.add(new Bot(nbBotMax+i+1,"min"));
            }
        }
        if(nbBotRandom > 0) {
            for (int i = 0; i < nbBotRandom; i++) {
                players.add(new Bot(nbBotMax+nbBotMin+i+1,"random"));
            }
        }
        return players;
    }

    public List<Player> generateBot(int nbOfPlayers) {
        List<Player> players = new ArrayList<>();
        if (nbOfPlayers > 4) {
            throw new IllegalArgumentException("too much bot");
        }
        for (int i = 0; i < nbOfPlayers; i++) {
            players.add(new Bot(i+1,"max"));
        }
        return players;
    }
}
