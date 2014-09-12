package com.me.simplebase.numberz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.simplebase.base.InputScreen;
import com.me.simplebase.base.Game;
import com.me.simplebase.blockharvest.screens.MainMenuScreen;
import com.me.simplebase.numberz.game.stage.Stage;

public class GameScreen extends InputScreen{

	public static int ID = 1;
	
	private Game sm; // I must add a reference to this in update() one of these days
	
	private Stage stage = Stage.CatcherControlStage;

	private SpriteBatch batch;
	
	@Override
	public void render() {
		batch.begin();		
		
        stage.draw(batch);
		
		batch.end();
	}

	@Override
	public void update(float delta) {
		stage.update(delta);
		if(stage.stageData().lost){
			sm.enterScreen(MainMenuScreen.ID);
		}
	}

	@Override
	public void init(Game game) {
		sm = game; // one of these days...
	}
	
	@Override
	public void show() {
		super.show();
		// initialising stuff
		batch = new SpriteBatch();
		stage.init();
		this.addProcessor(stage);
	}
	
	@Override
	public void hide() {
		super.hide();
		this.removeProcessor(stage);
	}

	@Override
	public int getID() {
		return ID;
	}

}
