package com.mygdx.game;

/**
 * Created by Nick on 17/10/2015. When instantiated this class checks for a win condition
 */
public class FindWinner {

    public  FindWinner(){}

    public Player hasWon(Player player, BoardManager boardManager){
        if (horizVertBoardIterator(boardManager,player)!=null || diagonalBoardIterator(boardManager, player)!= null)
            return player;
        return null;
    }

    private Player horizVertBoardIterator(BoardManager boardManager, Player player){
        int firstAxis = 0;
        while (firstAxis <3){
            int secondAxis = 0;
            int squareMarkedX = 0;
            int squareMarkedY = 0;
            while (secondAxis <3) {
                squareMarkedX += boardManager.getSquareValue(firstAxis,secondAxis);
                squareMarkedY += boardManager.getSquareValue(secondAxis,firstAxis);
                if (Math.abs(squareMarkedX) == 3 || Math.abs(squareMarkedY)==3){
                    return player;
                }

                secondAxis++;
            }
            firstAxis++;
        }
        return null;
    }

    private Player diagonalBoardIterator(BoardManager boardManager, Player player){
        int xy = 0;
        int squareMarkedLBtoRT = 0;
        int squareMarkedLTtoRB = 0;
        while (xy < 3){
            squareMarkedLBtoRT += boardManager.getSquareValue(xy,xy);
            squareMarkedLTtoRB += boardManager.getSquareValue((2-xy),xy);
            if (Math.abs(squareMarkedLBtoRT) == 3 || Math.abs(squareMarkedLTtoRB) == 3){
                return player;}
            xy++;
        }
        return null;
    }

}
