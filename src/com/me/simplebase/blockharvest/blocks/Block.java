package com.me.simplebase.blockharvest.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.me.simplebase.base.g2d.BatchDrawer;

public class Block extends InputAdapter{
	
	private final float DEFAULT_TTR = 3.5f;
	
	private int pointsToAward, lastAccuracy = -1;
	private float x, y, width, height;
	private float timeToReach;
	private long startingTime;
	private boolean started;
	private BatchDrawer drawable;
	private Rectangle rectangle;
	
	public Block(float x, float y, float width, float height){
		this.y = y;
		this.x = x;
		this.width = width;
		this.height = height;
		started = false;
		timeToReach = DEFAULT_TTR; // default time to reach
		drawable = new BlockDrawer(this);
		rectangle = new Rectangle(x, y, width, height);
	}
	
	public float timeElapsed(){
		return started ? (float)(System.currentTimeMillis() - startingTime) / (float)1000 : 0;
	}
	
	public int getAccuracy(){
		int tempPoints = lastAccuracy;
		lastAccuracy = -1;
		return tempPoints;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	
	public float getPercentage(){
		return timeElapsed() / timeToReach;
	}
	
	public void render(SpriteBatch batch){
		drawable.draw(batch);
	}
	
	public void update(float delta){
	}
	
	/**
	 * 0 if not started and tapped.
	 * 100 > if started and tapped.
	 * -1 if waited too long.
	 * 		
	 */
	public int getPoints(){
		int points = pointsToAward;
		pointsToAward = 0;
		return 100 * points;
	}
	
	public void setTimeToReach(float timeToReach){
		this.timeToReach = timeToReach;
	}
	
	public float getTimeToReach(){
		return this.timeToReach;
	}
	
	public void start(){
		started = true;
		startingTime = System.currentTimeMillis();
	}
	
	public boolean hasStarted(){
		return started;
	}
	
	public void reset(){
		started = false;
	}
	
	public float accuracy(){
		return (float) (timeElapsed() > timeToReach || !started ?
				0 : (float) 2 * (50 - Math.abs(50 - 100 * timeElapsed() / timeToReach)));
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// highest accuracy after half the timeToReach has elapsed
		if(started && rectangle.contains(screenX, -screenY + Gdx.graphics.getHeight())){
			float newAccuracy = accuracy();
			pointsToAward ++;
			lastAccuracy = (int)newAccuracy;
			reset();
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		touchDown(screenX, screenY, pointer, 0);
		lastAccuracy += lastAccuracy == -1 ? 0 : 3;
		return false;
	}
}
