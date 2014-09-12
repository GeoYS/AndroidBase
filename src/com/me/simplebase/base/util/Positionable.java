package com.me.simplebase.base.util;

/**
 * Represents something with a 2D position.
 * This was to save reduce the amount of getters and setters
 * I'd have to write.
 * @author GeoYS_2
 *
 */
public class Positionable {
	
	private float x, y;
	private float width, height;
	
	public Positionable(float x, float y){
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
}
