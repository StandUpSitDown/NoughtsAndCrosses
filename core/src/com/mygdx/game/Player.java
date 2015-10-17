package com.mygdx.game;

/**
 * Player class, handles individual player attributes
 *
 * Created by Nick on 05/10/2015.
 */
public class Player {

    private int playerNo;
    private String playerName;



    public Player(int playerNo){
        setPlayerNo(playerNo);


        setPlayerName("Player "+ playerNo);
    }

    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public String getPlayerName(){
        return playerName;
    }

}
