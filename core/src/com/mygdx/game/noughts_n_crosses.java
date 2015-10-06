package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class noughts_n_crosses extends ApplicationAdapter {

	public static final int SCREEN_WIDTH = 1920;
	public static final int SCREEN_HEIGHT = 1080;
	private SpriteBatch batch;
	private Texture underlayer;
	private Texture backgrnd;
	private Texture nought;
	private Texture cross;
	private OrthographicCamera camera;
	private BoardManager boardManager;
	private Player playerOne, playerTwo;



	
	@Override
	public void create () {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		batch = new SpriteBatch();
		underlayer = new Texture("underlayer.png");
		backgrnd = new Texture("noughts_n_crosses_backgrnd_1080x1080.png");
		nought = new Texture("nought.png");
		cross = new Texture("cross.png");
		playerOne = new Player(1,"",true);
		playerTwo = new Player(2,"",false);
		boardManager = new BoardManager();

		newGame();
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(underlayer, 0, 0);
		batch.draw(backgrnd, (SCREEN_WIDTH - backgrnd.getWidth()) / 2, 0);
		boardManager.update(batch, backgrnd, nought, cross);
		if (boardManager.getGameStatus().equals("Start")){
			BitmapFont font = new BitmapFont();
			font.getData().setScale(4);
			font.draw(batch, "Welcome to Noughts and Crosses", SCREEN_WIDTH/3, SCREEN_HEIGHT / 2);
		}

		batch.end();
		handleInput();
	}



	private void handleInput() {

		if(Gdx.input.justTouched()) {

			if(boardManager.getGameStatus().equals("Start")){
				boardManager.setGameStatus("Playing");}
			else if(boardManager.getGameStatus().equals("Playing")) {
				Vector3 touchPosn = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPosn);
				Player currentPlayer;
				if (playerOne.getCurrentTurn())
					currentPlayer = playerOne;
				else
					currentPlayer = playerTwo;
				chooseSquareTouched(touchPosn, currentPlayer);
				playerOne.setCurrentTurn(!playerOne.getCurrentTurn());
				playerTwo.setCurrentTurn(!playerTwo.getCurrentTurn());
			}
			else if(boardManager.getGameStatus().equals("Game finished")){
				newGame();
				boardManager.setGameStatus("Playing");}
		}
	}





	public void   chooseSquareTouched(Vector3 touchPosn, Player currentPlayer) {
		boardManager.chooseSquare(touchPosn, backgrnd, currentPlayer);

	}

	private void newGame() {
		playerOne.setCurrentTurn(true);
		playerTwo.setCurrentTurn(false);
		boardManager.clearBoard();
	}
}
