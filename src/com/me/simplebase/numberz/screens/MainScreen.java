package com.me.simplebase.numberz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.simplebase.base.InputScreen;
import com.me.simplebase.base.Game;

public class MainScreen extends InputScreen{

	public static int ID = 0;
	
	private SpriteBatch batch;
	private MainMenu menu;
	private Texture background;
	
	@Override
	public void render() {
		 
        Gdx.gl.glClearColor(Color.TEAL.r, Color.TEAL.g, Color.TEAL.b, Color.TEAL.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);      
        
        batch.begin();
        
        batch.draw(background, 0, 0);
        
        batch.end();
		
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
		batch = new SpriteBatch();
		background = new Texture(new Pixmap(Gdx.files.internal("Numberz/data/Numberztitle.png")), true);
	}

	@Override
	public int getID() {
		return ID;
	}

}
