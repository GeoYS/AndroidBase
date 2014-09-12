package com.me.simplebase.catcher.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.me.simplebase.base.Game;
import com.me.simplebase.base.GameScreen;
import com.me.simplebase.catcher.screens.ui.MainMenu;

public class MainScreen extends GameScreen{

	public static final int ID = 0;
	private MainMenu menu;
	
	@Override
	public void render() {
		Gdx.gl20.glClearColor(0,0,0,0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		menu.draw();
	}

	@Override
	public void update(float delta) {
		menu.act();
	}

	@Override
	public void init(Game game) {
		menu = new MainMenu(game);
		this.addProcessor(menu);
	}

	@Override
	public int getID() {
		return ID;
	}
	
	// transition related stuff

	private final float TRANS_IN_TIME = 0.5f, TRANS_OUT_TIME = 0.5f;
	private float timeIn, timeOut;
	private ShapeRenderer fadebox = new ShapeRenderer();
	
	@Override
	public void startInTransition(Screen otherScreen) {
		timeIn = 0;
	}
	
	@Override
	public void updateInTransition(float delta) {
		timeIn += delta;
	}
	
	@Override
	public void renderInTransition() {
		render();
		Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
		fadebox.setColor(0, 0, 0, 1 - timeIn / TRANS_IN_TIME);
		fadebox.begin(ShapeType.Filled);
		fadebox.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fadebox.end();
	}
	
	@Override
	public boolean inTransitionFinished() {
		return timeIn >= TRANS_IN_TIME;
	}
	
	@Override
	public void startOutTransition(Screen otherScreen) {
		timeOut = 0;
	}
	
	@Override
	public void updateOutTransition(float delta) {
		timeOut += delta;
	}
	
	@Override
	public void renderOutTransition() {
		render();
		Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
		fadebox.setColor(0, 0, 0, timeOut / TRANS_OUT_TIME);
		fadebox.begin(ShapeType.Filled);
		fadebox.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fadebox.end();
	}
	
	@Override
	public boolean outTransitionFinished() {
		return timeOut >= TRANS_OUT_TIME;
	}
}
