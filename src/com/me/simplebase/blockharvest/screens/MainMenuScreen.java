package com.me.simplebase.blockharvest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.simplebase.base.InputScreen;
import com.me.simplebase.base.Game;
import com.me.simplebase.blockharvest.blocks.Block;

public class MainMenuScreen extends InputScreen{

	public static int ID = 0;
	
	private MainMenu menu;
	private SpriteBatch batch;
	private BitmapFont font;
	private Block block;
	
	@Override
	public void render() {
		batch.begin();		

        Gdx.gl.glClearColor(0.f, 0.5f, 0.5f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		menu.draw();
		
		batch.end();
		
		batch.begin();
		
		block.render(batch);
				
		font.setColor(Color.WHITE);
		font.draw(batch, "Harvest Blocks", 
				Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8,
				Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 6);

		batch.end();
	}

	@Override
	public void update(float delta) {
		menu.act(delta);
	}

	@Override
	public void init(Game game) {
		menu = new MainMenu(game);
		menu.getRoot().setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8,
				Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 10);
		this.addProcessor(menu);
		batch = new SpriteBatch();
		font = new BitmapFont();
		block = new Block(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8,
				Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4,
				Gdx.graphics.getWidth() / 4,
				Gdx.graphics.getHeight() / 5);
		//block.setTimeElapsed(block.getTimeToReach() / 2);
	}

	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		System.out.println("Mouse lifted");
		return super.touchUp(screenX, screenY, pointer, button);
	}

}
