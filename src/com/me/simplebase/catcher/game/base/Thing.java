package com.me.simplebase.catcher.game.base;

import com.badlogic.gdx.math.Rectangle;
import com.me.simplebase.base.g2d.BatchDrawer;
import com.me.simplebase.base.util.Positionable;

public class Thing extends Positionable{
	
	private boolean isDead;
	private boolean isActive;
	private Controller controller;
	private BatchDrawer drawer;
	private Updater updater;
	
	public Thing(float x, float y) {
		super(x, y);
		isDead = false;
		isActive = true;
	}
	
	public Updater getUpdater() {
		return updater;
	}
	
	public BatchDrawer getDrawer() {
		return drawer;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public void setUpdater(Updater updater) {
		this.updater = updater;
	}
	
	public void setDrawer(BatchDrawer drawer) {
		this.drawer = drawer;
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public Rectangle getCollisionBox(){
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	/**
	 * Determines whether or not this Thing should be removed from the Stage
	 * (ie. removed from game).
	 * @return
	 */
	public boolean isDead(){
		return isDead;
	}
	
	/**
	 * Set this Thing as dead and inactive.
	 */
	public void kill(){
		isDead = true;
		isActive = false;
	}
	
	public void setActive(boolean isActive){
		this.isActive = isActive;
	}
	
	/**
	 * Determines whether or not this Thing should be updated, controlled, and drawn.
	 * @return
	 */
	public boolean isActive(){
		return isActive;
	}
}
