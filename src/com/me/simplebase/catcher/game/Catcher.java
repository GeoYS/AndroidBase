package com.me.simplebase.catcher.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.me.simplebase.base.g2d.BatchDrawer;
import com.me.simplebase.catcher.game.base.Controller;
import com.me.simplebase.catcher.game.base.Updater;
import com.me.simplebase.catcher.game.base.VfxThing;
import com.me.simplebase.catcher.game.obstacles.Mirror;

public class Catcher extends VfxThing{

	public static final float WIDTH = 64, HEIGHT = 64;	
	
	private Controller controller;
	private BatchDrawer drawer;
	private Updater updater;
	private boolean isEating;
	protected Mirror mirror = null;
	
	public Catcher() {
		super(Gdx.graphics.getWidth() / 2 - WIDTH / 2, 0);
		
		isEating = false;
		
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		
		// blank updater
		updater = new Updater(){
			@Override
			public void update(float delta) {
			}
		};
		
		controller = new Controller(){
			@Override
			public void control() {
				if(mirror != null && mirror.isActive()){
					if(mirror.isLeft()){
						if(getX() < Gdx.graphics.getWidth() / 2){
							store(new PoofVfx(getX(), getY()));
							setX(Gdx.graphics.getWidth() / 2);
						}
					}
					else{
						if(getX() + getWidth() > Gdx.graphics.getWidth() / 2){
							store(new PoofVfx(getX(), getY()));
							setX(Gdx.graphics.getWidth() / 2 - getWidth());
						}
					}
				}
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer,
					int button) {
				setEating(true);
				float newX = screenX - Catcher.WIDTH / 2;
				if(!(getX() <= screenX && screenX <= getX() + getWidth())){
					store(new PoofVfx(getX(), getY()));
				}
				setX(newX);
				if(mirror != null && mirror.isActive()){
					if(mirror.isLeft()){
						if(getX() < Gdx.graphics.getWidth() / 2){
							setX(Gdx.graphics.getWidth() / 2);
						}
					}
					else{
						if(getX() + getWidth() > Gdx.graphics.getWidth() / 2){
							setX(Gdx.graphics.getWidth() / 2 - getWidth());
						}
					}
				}
				return false;
			}
			
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				setX(screenX - Catcher.WIDTH / 2);
				if(mirror != null && mirror.isActive()){
					if(mirror.isLeft()){
						if(getX() < Gdx.graphics.getWidth() / 2){
							setX(Gdx.graphics.getWidth() / 2);
						}
					}
					else{
						if(getX() + getWidth() > Gdx.graphics.getWidth() / 2){
							setX(Gdx.graphics.getWidth() / 2 - getWidth());
						}
					}
				}
				return false;
			}
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer,
					int button) {
				setEating(false);
				return false;
			}
		};
		
		drawer = new CatcherDrawer(this);
	}
	
	public void setMirror(Mirror mirror){
		this.mirror = mirror;
	}
	
	public Mirror getMirror() {
		return mirror;
	}
	
	@Override
	public Updater getUpdater() {
		return updater;
	}

	@Override
	public Controller getController() {
		return controller;
	}

	@Override
	public BatchDrawer getDrawer() {
		return drawer;
	}

	@Override
	public Rectangle getCollisionBox() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	public boolean isEating(){
		return isEating;
	}
	
	public void setEating(boolean isEating){
		this.isEating = isEating;
	}
}
