package com.me.simplebase.blockharvest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LifeBar {
	
	private final float TOTAL_LIFE = 60; // SECONDS
	private float currentLife, x, y;
	private LifeBarDrawer drawer;
	
	public LifeBar(){
		this.setX(Gdx.graphics.getWidth() / 25); 
		this.setY(Gdx.graphics.getHeight()  * 23 / 25);
		currentLife = TOTAL_LIFE;
		drawer = new LifeBarDrawer(this);
	}
	
	public void draw(SpriteBatch batch){
		drawer.draw(batch);
	}
	
	public void removeTime(float delta){
		currentLife -= delta;
	}
	
	public void addTime(float delta){
		currentLife += delta;	
		if(currentLife > TOTAL_LIFE){
			currentLife = TOTAL_LIFE;
		}
	}
	
	public float getPercentage(){
		return currentLife / TOTAL_LIFE;
	}
	
	public boolean hasTimeLeft(){
		return currentLife > 0;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
