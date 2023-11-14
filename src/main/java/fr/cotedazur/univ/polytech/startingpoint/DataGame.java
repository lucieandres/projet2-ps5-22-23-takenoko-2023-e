package fr.cotedazur.univ.polytech.startingpoint;

import java.util.ArrayList;
import java.util.List;

public class DataGame {
    int tie;
    int parties;

    List<DataGamePlayer> dataGamePlayerList;

    public DataGame() {
        this.dataGamePlayerList = new ArrayList<>();
        tie = 0;
        parties = 0;
    }

    public DataGame(List<DataGamePlayer> playersData) {
        this.dataGamePlayerList = playersData;
        tie = 0;
        parties = 0;
    }

    public DataGame(List<DataGamePlayer> playersData, int tie, int parties) {
        this.dataGamePlayerList = playersData;
        this.tie = tie;
        this.parties = parties;
    }

    public List<DataGamePlayer> getDataGamePlayerList() {
        return dataGamePlayerList;
    }

    public void addTie() {
        tie++;
    }

    public void addParties() {
        parties++;
    }

    @Override
    public String toString() {
        String s = "Egalite = " + tie + "  \tNombre de parties = " + parties + "\n";
        for (DataGamePlayer dgp : dataGamePlayerList) {
            s += dgp.toString() + "\n";
        }
        return s;
    }

    public int getTie() {
        return tie;
    }
}
