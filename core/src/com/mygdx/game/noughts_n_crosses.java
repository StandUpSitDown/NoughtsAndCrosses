package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class noughts_n_crosses extends ApplicationAdapter {

	public static final int SCREEN_WIDTH = 1920;
	public static final int SCREEN_HEIGHT = 1080;
	private SpriteBatch batch;
	private Texture underlayer;
	private Texture backgrnd;
	private Texture nought;
	private Texture cross;
	private OrthographicCamera camera;
	private Viewport viewport;
	private BoardManager boardManager;
	private Player playerOne, playerTwo, currentPlayer;
	private FindWinner findWinner;



	
	@Override
	public void create () {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		viewport = new StretchViewport(SCREEN_WIDTH,SCREEN_HEIGHT,camera);
		viewport.update(SCREEN_WIDTH, SCREEN_HEIGHT);
		batch = new SpriteBatch();
		underlayer = new Texture("underlayer.png");
		backgrnd = new Texture("noughts_n_crosses_backgrnd_1080x1080.png");
		nought = new Texture("nought.png");
		cross = new Texture("cross.png");
		playerOne = new Player(1);
		playerTwo = new Player(2);
		boardManager = new BoardManager();
		currentPlayer  = playerOne;
		findWinner = new FindWinner();

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(underlayer, 0, 0);
		batch.draw(backgrnd, (SCREEN_WIDTH - backgrnd.getWidth()) / 2, 0);
		boardManager.update(batch, backgrnd, nought, cross);
		if (boardManager.getGameStatus().equals("Start")){
			drawFont("Welcome to Noughts and Crosses");
		}

		Player currentPlayer;
		if (boardManager.getPlayerTurn()==2)
			currentPlayer = playerOne;
		else
			currentPlayer = playerTwo;

		Player winner = findWinner.hasWon(currentPlayer, boardManager);
		if (winner!=null){
			boardManager.setGameStatus(currentPlayer.getPlayerName() + " has won");
			drawFont(boardManager.getGameStatus());
		}

		if(boardManager.getGameStatus().equals("Game finished"))
			drawFont("Game Drawn");




		batch.end();
		handleInput();

	}

	@Override
	public void resize(int width,int height){
		viewport.update(width, height);
	}

	private void drawFont(String textString){
		BitmapFont font = new BitmapFont();
		font.getData().setScale(4);
		font.draw(batch, textString, SCREEN_WIDTH/3, SCREEN_HEIGHT / 2);
	}



	private void handleInput() {

		if(Gdx.input.justTouched()) {

			if(boardManager.getGameStatus().equals("Start"))
				boardManager.setGameStatus("Playing");

			else if(boardManager.getGameStatus().equals("Playing")) {
				Vector3 touchPosn = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				viewport.unproject(touchPosn);
				if (boardManager.getPlayerTurn()==1)
					currentPlayer = playerOne;
				else
					currentPlayer = playerTwo;
				chooseSquareTouched(touchPosn, currentPlayer);
				findWinner.hasWon(currentPlayer,boardManager);
			}
			else if(boardManager.getGameStatus().equals("Game finished")||boardManager.getGameStatus().endsWith("has won")){
				newGame();
				boardManager.setGameStatus("Playing");}
		}
	}





	private void   chooseSquareTouched(Vector3 touchPosn, Player currentPlayer) {
		boardManager.chooseSquare(touchPosn, backgrnd, currentPlayer);

	}

	private void newGame() {
		boardManager.setPlayerTurn(1);
		boardManager.clearBoard();
		boardManager.resetTurnCounter();
	}

}
