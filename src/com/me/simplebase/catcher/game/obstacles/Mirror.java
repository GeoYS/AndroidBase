package com.me.simplebase.catcher.game.obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.me.simplebase.base.g2d.BatchDrawer;
import com.me.simplebase.catcher.game.Catcher;
import com.me.simplebase.catcher.game.base.Controller;
import com.me.simplebase.catcher.game.base.Updater;
import com.me.simplebase.catcher.game.base.VfxThing;

public class Mirror extends VfxThing{
	
	private Catcher main, mirror;
	boolean isActivated;
	final boolean left;
	final float length;
	
	public Mirror(final boolean left, final float length) {
		super(0, 0);
		
		isActivated = false;
		this.left = left;
		this.length = length;
		
		store(new MirrorVfx(this));
		
		setUpdater(new Updater() {
			private final float FALLSPEED = 400;
			@Override
			public void update(float delta) {
				setY(getY() - delta * FALLSPEED);
				if(isActivated){
					if(getY() + length < 0){
						deactivateAndKill();
					}
				}
				else if(getY() < Catcher.WIDTH){
					activate();
				}
			}
		});
		
		setController(new Controller() {
			
			@Override
			public void control() {
			}
		});
		
		setDrawer(new BatchDrawer() {
			
			@Override
			public void drawImage(SpriteBatch batch) {
			}
			
			@Override
			public void applyTransform(Matrix4 transform) {
			}
		});
	}
	
	public float getLength(){
		return length;
	}
	
	public boolean isLeft(){
		return left;
	}
	
	private void activate(){
		mirror.setActive(true);
		main.setMirror(this);
		mirror.setMirror(this);
		// update position to mirror mains
		main.getController().control();
		mirror.getController().control();
		isActivated = true;
	}
	
	private void deactivateAndKill(){
		mirror.setActive(false);
		this.kill();
	}
	
	/**
	 * It is important to give the mirror references to the two Catchers.
	 * @param main
	 */
	public void setCatchers(Catcher main, Catcher mirror) {
		this.main = main;
		this.mirror = mirror;
	}
}
