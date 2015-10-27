package com.mygdx.game;

/**
 * Created by Nick on 27/10/2015. This object is to hold board square values
 */
public class BoardSquare {

    private int squareValue;

    public BoardSquare(){
        this.squareValue = 0;
    }

    public void setSquareEmpty(){
        this.squareValue = 0;
    }

    public boolean isSquareCross(){
        return (squareValue == -1);
    }

    public boolean isSquareNought(){
        return (squareValue == 1);
    }

    public int getSquareValue(){
        return squareValue;
    }
}
