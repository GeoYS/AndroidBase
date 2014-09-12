package com.me.simplebase.numberz.game.num;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.me.simplebase.base.util.Positionable;
import com.me.simplebase.numberz.game.catcher.Catcher;

/**
 * A number item that spawns and moves towards the catcher.
 * @author GeoYS_2
 *
 */
public class Num extends Positionable{
	
	public static final float WIDTH = 50, HEIGHT = 50;
	
	private final float SPEED = 300; // px / s
	
	private Catcher catcher; 
	private Op op;
	private NumDrawer drawer;
	private boolean tapped;
	private NumController controller;

	public Num(Catcher catcher) {
		super((float) Math.random() * Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.catcher = catcher;
		drawer = new NumDrawer(this);
		
		// Generate random op
		if(Math.random() > 0.5f){
			op = Op.ADD_ONE;
		}
		else if(Math.random() > 0.5f){
			op = Op.ADD_TWO;
		}
		else if(Math.random() > 0.5f){
			op = Op.MIN_THREE;
		}
		else {
			op = Op.MUL_TWO;
		}		
		tapped = false;
		controller = new NumController(this);
		setHeight(HEIGHT);
		setWidth(WIDTH);
	}

	public void draw(SpriteBatch batch){
		drawer.draw(batch);
	}
	
	public void update(float delta){
		Vector2 vel, dir;
		dir = new Vector2(catcher.getX() + Catcher.WIDTH / 2 - this.getX(), catcher.getY() + Catcher.HEIGHT / 2 - this.getY()).nor();
		vel = dir.scl(SPEED * delta);
		this.setX(this.getX() + vel.x);
		this.setY(this.getY() + vel.y);		
	}
	
	public InputAdapter getController(){
		return controller;
	}
	
	public Op getOp(){
		return op;
	}
	
	public void tap(){
		tapped = true;
	}
	
	public boolean isTapped(){
		return tapped;
	}
}
