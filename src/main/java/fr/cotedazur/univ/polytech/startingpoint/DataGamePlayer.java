package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.player.Player;

public class DataGamePlayer {
    private Player player;
    private PlayerResultContainer playerResultContainer;

    DataGamePlayer(Player player, PlayerResultContainer prc) {
        this.player = player;
        this.playerResultContainer = prc;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerResultContainer getPlayerResultContainer() {
        return playerResultContainer;
    }

    @Override
    public String toString() {
        return player.toString() + " " + playerResultContainer.toString();
    }
}
