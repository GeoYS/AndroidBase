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

public class Coin extends Thing implements VfxSupplier{
	
	private final float WIDTH = 32, HEIGHT = 32;
	/**
	 * New Coins always start at the top.
	 * @param x
	 */
	public Coin(float x) {
		super(x, Gdx.graphics.getHeight());
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		
		setUpdater(new Updater(){
			private final float FALLSPEED = 400; // px/s
			@Override
			public void update(float delta) {
				setY(getY() - delta * FALLSPEED);
			}
		});
		
		// blank controller
		setController(new Controller(){
			@Override
			public void control() {
			}
		});
		
		setDrawer(new CoinDrawer(this));
	}

	@Override
	public Rectangle getCollisionBox() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	@Override
	public void kill() {
		if(!isDead()){
			store(new CoinVfx(this.getX() + this.getWidth() / 2, this.getY()));
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
