package com.me.simplebase.catcher.game.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A visual effect. Essentially, something that 
 * needs to be drawn, but has no other affect other than
 * making pretty visuals. 
 * @author GeoYS_2
 *
 */
public interface Vfx {
	
	/**
	 * Update the animation of this effect.
	 * @param delta
	 */
	public void update(float delta);
	
	/**
	 * Draw this effect.
	 * @param batch
	 */
	public void draw(SpriteBatch batch);
	
	/**
	 * Return true if this effect has finished and should be removed.
	 * @return
	 */
	public boolean isFinished();
}
