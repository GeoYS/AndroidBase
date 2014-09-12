package com.me.simplebase.numberz.game.catcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.simplebase.base.util.Positionable;

/**
 * An orange(currently) box  that catches Num's.
 * @author GeoYS_2
 *
 */
public class Catcher extends Positionable{

	public static final float WIDTH = Gdx.graphics.getWidth(), HEIGHT = 100;
	
	private static final float ORIGINX = Gdx.graphics.getWidth() / 2 - Catcher.WIDTH / 2,
			ORIGINY = 0;
	
	private boolean isClosed;	
	private CatcherController controller;
	protected CatcherDrawer drawer;
	
	/**
	 * Current value of catcher and what has already been submitted but not added to score.
	 */
	private int value, addScore = 0;
	
	/**
	 * Variables for the intro animation of the catcher
	 * (ie. when it "pops" in).
	 */
	private float starting;
	private final float finished = 0.2f; // seconds
	
	/**
	 * Variable to make the catcher fly away when swiped.
	 * Represents y-velocity.
	 */
	private float vy;
	
	public Catcher() {
		super(ORIGINX, ORIGINY);
		this.drawer = new CatcherDrawer(this);
		this.controller = new CatcherController(this);
		init();
	}
	
	private void init(){
		this.isClosed = false;
		setX(ORIGINX);
		setY(ORIGINY);
		vy = 0;
		starting = 0;
		value = 0;
	}
	
	public void update(float delta){
		
		// intro animation
		if(starting < finished){
			starting += delta;
			if(starting > finished){
				starting = finished;
			}
		}
		
		// returning to original position
		if(!isClosed &&  /*Math.abs(ORIGINX - x) > 0.001 && 	// fix here so that it is less harsh when user
				Math.abs(ORIGINY - y) > 0.001 && */				// accidently doesn't swipe fast enough
				this.vy == 0){
			float dx = ORIGINX - this.getX(), dy = ORIGINY - this.getY();
			float returnConstant = 3;
			this.setX(getX() + dx * returnConstant * delta);
			this.setY(getY() + dy * returnConstant * delta);
		}
		
		// flying off
		this.setY(this.getY() + vy * delta);
		
		// when flown off, reset
		if(this.getY() > Gdx.graphics.getHeight()){
			reset();
		}
	}
	
	protected void reset(){
		addScore += value;
		init();
	}
	
	public int getToAdd(){
		int temp = addScore;
		addScore = 0;
		return temp;
	}
	
	public void draw(SpriteBatch batch){
		drawer.draw(batch);
	}
	
	public CatcherController getController() {
		return controller;
	}
	
	/**
	 * Used for the intro popping in.
	 * @return
	 */
	public float getScale(){
		return starting / finished;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(int val){
		value = val;
	}
	
	public void flyOff(float vy){
		// haven't decided if I should go with constant fly off speed, or drag speed (vy)
		float flyOffSpeed = 650; // px / s (same as threshold for flying off
		this.vy = flyOffSpeed;
		isClosed = true;
	}
	
	public void setClosed(boolean isClosed){
		this.isClosed = isClosed;
	}
	
	public boolean isClosed(){
		return isClosed;
	}
}
