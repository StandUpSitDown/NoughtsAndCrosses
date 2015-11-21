package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * BoardManager is a single object used to hold and manage game board state
 *
 * Created by Nick on 03/10/2015.
 */
public class BoardManager {

    BoardSquare[][] boardSquares;
    private int turnCounter;
    private String gameStatus;
    private int playerTurn;

    public BoardManager(){
        boardSquares = new BoardSquare[3][3];
        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
            boardSquares[x][y]= new BoardSquare();}
        }
        System.out.println(boardSquares.length);
        gameStatus = "Start";
        setPlayerTurn(1);

    }



    public void setSquareUsed(int x,int y,int squareValue){
        if (boardSquares[x][y].isSquareEmpty()) {
            boardSquares[x][y].setSquareValue(squareValue);
            turnCounter++;
            if (getPlayerTurn()==1)
                setPlayerTurn(2);
            else setPlayerTurn(1);
            if (turnCounter > 8){
                setGameStatus("Game finished");
                turnCounter = 0;

            }
        }
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setEmpty(int x, int y){
        boardSquares[x][y].setSquareEmpty();
    }

    public boolean getCross(int x, int y){ return boardSquares[x][y].isSquareCross();}

    public boolean getNought(int x, int y){ return boardSquares[x][y].isSquareNought();}

    public int getSquareValue(int x,int y){ return boardSquares[x][y].getSquareValue();}

    public void update(SpriteBatch batch, Texture backgrnd, Texture nought, Texture cross){
        int boardXLeftHand = (noughts_n_crosses.SCREEN_WIDTH - backgrnd.getWidth())/2;
        int squareWidth = backgrnd.getWidth()/3;
        for (int x=0;x < 3;x++) {
            for (int y=0;y < 3;y++) {
                if(getCross(x,y)) {
                      batch.draw(cross, boardXLeftHand + x * squareWidth,y * squareWidth);
                }
                if(getNought(x,y)){
                    batch.draw(nought, boardXLeftHand + x * squareWidth,y * squareWidth);                 }
            }
        }
    }

    public void chooseSquare(Vector3 touchPosn, Texture backgrnd, Player player){

        int boardXLeftHand = (noughts_n_crosses.SCREEN_WIDTH - backgrnd.getWidth())/2;
        int squareWidth = backgrnd.getWidth()/3;
        for (int x=0;x < 3;x++) {
            for (int y=0;y < 3;y++) {
                if(touchPosn.x >= boardXLeftHand + x * squareWidth &&
                        touchPosn.x <= boardXLeftHand + (x+1) * squareWidth &&
                        touchPosn.y >= y * squareWidth &&
                        touchPosn.y <= (y+1)* squareWidth) {
                    if (player.getPlayerNo() == 1) {

                        setSquareUsed(x, y, 1);
                    } else {
                        setSquareUsed(x, y, -1);
                    }

                }
            }
        }
    }

    public void clearBoard() {
        for (int x=0;x < 3;x++) {
            for (int y=0;y < 3;y++) {
                setEmpty(x, y);
            }
        }
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }


    public void resetTurnCounter() {
        turnCounter = 0;
    }
}
