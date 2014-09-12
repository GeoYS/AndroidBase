package com.me.simplebase.catcher.game.obstacles;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.me.simplebase.base.g2d.BatchDrawer;
import com.me.simplebase.catcher.game.base.Controller;
import com.me.simplebase.catcher.game.base.Thing;
import com.me.simplebase.catcher.game.base.Updater;
import com.me.simplebase.catcher.game.base.Vfx;
import com.me.simplebase.catcher.game.base.VfxSupplier;

public class Brick extends Thing implements VfxSupplier{
	
	public static final float WIDTH = 32, HEIGHT = 32;

	private Updater updater;
	private Controller controller;
	private BatchDrawer drawer;
	
	public Brick(float x) {
		super(x, Gdx.graphics.getHeight());
		
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		
		drawer = new BrickDrawer(this);
		
		updater = new Updater(){
			private final float FALLSPEED = 400; // px/s
			@Override
			public void update(float delta) {
				setY(getY() - delta * FALLSPEED);
			}			
		};
		
		// blank controller
		controller = new Controller() {			
			@Override
			public void control() {
			}
		};
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
	public void kill() {
		if(!isDead()){
			store(new BrickVfx(this.getX() + this.getWidth() / 2, this.getY()));
		}
		super.kill();
	}
	
	// I should probably reconsider how Things handle Vfx
	// if I have to repeat this code for every Thing...	
	private ArrayList<Vfx> effects = new ArrayList<>(),
			effectsCopy = new ArrayList<>();
	
	@Override
	public void store(Vfx vfx) {
		effects.add(vfx);
	}

	@Override
	public ArrayList<Vfx> release() {
		effectsCopy.clear();
		effectsCopy.addAll(effects);
		effects.clear();
		if(!effectsCopy.isEmpty()){
		}
		return effectsCopy;
	}
}
