package com.me.simplebase.base.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Particle {
	
	/**
	 * Update the particle
	 * @param delta
	 */
	public void update(float delta);
	
	/**
	 * Draw the particle
	 * @param batch
	 */
	public void draw(SpriteBatch batch);
	
	/**
	 * Has the particle lived its full lifespan?
	 * @return
	 */
	public boolean isFinished();
}
