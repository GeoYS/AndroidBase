package com.me.simplebase.catcher.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.simplebase.base.InputScreen;
import com.me.simplebase.base.Game;
import com.me.simplebase.catcher.game.Level;
import com.me.simplebase.catcher.game.LevelHud;
import com.me.simplebase.catcher.game.LevelStage;
import com.me.simplebase.catcher.game.base.Hud;

public class GameScreen extends InputScreen{

	public static int ID = 2;
	
	private LevelStage testStage;
	private Hud levelHud;
	private SpriteBatch batch;
	
	public GameScreen(){
		testStage = new LevelStage(Level.TEST_LEVEL);
		this.addProcessor(testStage);
		batch = new SpriteBatch();
	}
	
	@Override
	public void render() {
		batch.begin();
		
		Gdx.gl20.glClearColor(0,0,0,0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		testStage.draw(batch);
		
		levelHud.draw(batch);
		
		batch.end();
	}

	@Override
	public void update(float delta) {
		testStage.update(delta);
	}

	@Override
	public void init(Game game) {
		testStage.init();		
		levelHud = new LevelHud(testStage.stageData());
	}

	@Override
	public void pause() {
		testStage.pause();
	}
	
	@Override
	public void resume() {
		testStage.resume();
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
