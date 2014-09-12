package com.me.simplebase.catcher.screens.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.me.simplebase.base.Game;
import com.me.simplebase.catcher.screens.GameScreen;

public class LevelSelect extends Stage{
	
	/**
	 * Variables for a slide-y menu
	 */
	private final float decel = 300; // px / sec
	private float vel; // px / sec
	private float thisX, lastX;
	private long lastTime, lastLastTime;
	private boolean dragging;
	
	private Button level1;
	
	public LevelSelect(final Game sm) {
		level1 = new ImageButton(LevelButtonStyle.LEVEL1);
		level1.setPosition(Gdx.graphics.getWidth() / 2 - level1.getWidth() / 2, Gdx.graphics.getHeight() / 6);
		level1.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				sm.enterScreen(GameScreen.ID);
			}
			
		});
		this.addActor(level1);
		vel = 0;
		dragging = false;
	}
	
	@Override
	public void act(float delta) {
		Group root = this.getRoot();
		root.setPosition(root.getX() + vel * delta, root.getY());
		
		float velDir = Math.signum(vel);
		float newVel = vel * velDir - delta * decel;
		vel = newVel < 0 ? 0 : newVel * velDir;
		
		if(root.getX() > 0){
			root.setPosition(0, root.getY());
		}
		
		super.act(delta);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		vel = 0;
		lastX = screenX;
		thisX = screenX;
		lastTime = System.nanoTime();
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		dragging = true; // dragging vs pressing button
		lastX = thisX;
		thisX = screenX;
		lastLastTime = lastTime;
		lastTime = System.nanoTime();
		Group root = this.getRoot();
		root.setPosition(root.getX() + thisX - lastX, root.getY());
		return super.touchDragged(screenX, screenY, pointer);
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(dragging){
			float thisTime = System.nanoTime();
			if(thisTime == lastTime){
				vel = (thisX - lastX) / ((lastTime - lastLastTime) / 1_000_000_000f);	
			}
			else{
				vel = (thisX - lastX) / ((thisTime - lastTime) / 1_000_000_000f);	
			}
			dragging = false;
			return super.touchUp(-1, -1, pointer, button);
		}
		return super.touchUp(screenX, screenY, pointer, button);
	}
}
