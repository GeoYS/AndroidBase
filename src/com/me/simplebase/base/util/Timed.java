package com.me.simplebase.base.util;

/**
 * Might be useless once I master the Universal Tween Engine.
 * @author GeoYS_2
 *
 */
public interface Timed {
	public void start();
	public float getPerentageFinished();
	public boolean isFinished();
	public void reset();
}
