package com.mygdx.game;

/**
 * Player class, handles individual player attributes
 *
 * Created by Nick on 05/10/2015.
 */
public class Player {

    private int playerNo;
    private boolean hasWon;
    private String playerType;
    private boolean currentTurn;

    public Player(int playerNo, String playerType, boolean currentTurn){
        setPlayerNo(playerNo);

        setCurrentTurn(false);

        setPlayerType("human");

        setHasWon(false);
    }

    public void setCurrentTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public String getPlayerType() {
        return playerType;
    }

    public boolean getHasWon(){
        return hasWon;
    }

    public boolean getCurrentTurn(){
       return currentTurn;
    }
}
