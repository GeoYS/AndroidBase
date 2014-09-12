package com.me.simplebase.blockharvest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.simplebase.base.InputScreen;
import com.me.simplebase.base.Game;

public class HighscoreScreen extends InputScreen{

	public static final int ID = 1;
	
	private BitmapFont font;
	private SpriteBatch batch;
	private HighscoresMenu menu;
	
	@Override
	public void render() {
		batch.begin();
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		font.draw(batch,
				"Highscores",
				Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8,
				Gdx.graphics.getHeight() * 3 / 4);
		for(int i = 0; i < Highscores.getScores().size(); i++){
			font.draw(batch,
					(i + 1) + ". " + Highscores.getScores().get(i),
					Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8,
					Gdx.graphics.getHeight() * 3 / 4 - (i + 1) * Gdx.graphics.getHeight() / 10);
		}
		
		batch.end();
		
		batch.begin();
		
		menu.draw();
		
		batch.end();		
	}

	@Override
	public void update(float delta) {
		menu.act(delta);
	}

	@Override
	public void init(Game game) {
		font = new BitmapFont();
		batch = new SpriteBatch();
		menu = new HighscoresMenu(game);
		menu.getRoot().setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, 
				Gdx.graphics.getHeight() / 10);
		this.addProcessor(menu);
	}

	@Override
	public int getID() {
		return ID;
	}

}
