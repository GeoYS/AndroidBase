package com.me.simplebase.catcher.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.me.simplebase.base.InputScreen;
import com.me.simplebase.base.Game;
import com.me.simplebase.catcher.screens.ui.LevelSelect;

public class LevelScreen extends InputScreen{
	
	public static final int ID = 1;

	private LevelSelect menu;
	
	@Override
	public void render() {
		Gdx.gl20.glClearColor(0,0,0,0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		menu.draw();
	}

	@Override
	public void update(float delta) {
		menu.act(delta);
	}

	@Override
	public void init(Game game) {
		menu = new LevelSelect(game);
		this.addProcessor(menu);
	}

	@Override
	public int getID() {
		return ID;
	}
}
