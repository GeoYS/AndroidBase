package com.me.simplebase.base;

import com.badlogic.gdx.Screen;

/**
 * A Screen that handles transition-ing in and out of this screen.
 * Fully optional, you can equally use an InputScreen instead,
 * it will just result in an instant transition.
 * @author GeoYS_2
 *
 */
public abstract class GameScreen extends InputScreen{
	
	/**
	 * Begin transition out of this screen.
	 * @param otherScreen Might be useful for fancy transitions.
	 */
	public void startOutTransition(Screen otherScreen){
	}
	
	/**
	 * Has this screen finished transition-ing out?
	 * @return
	 */
	public boolean outTransitionFinished(){
		return true;
	}
	
	/**
	 * Draw what should be drawn when transition-ing out.
	 */
	public void renderOutTransition(){
	}
	
	/**
	 * Update the transition out.
	 */
	public void updateOutTransition(float delta){
	}
	
	public final void renderOut(float delta){
		this.renderOutTransition();
		this.updateOutTransition(delta);
	}
	
	/**
	 * Begin transition in of this screen.
	 * @param otherScreen Might be useful for fancy transitions.
	 */
	public void startInTransition(Screen otherScreen){
	}
	
	/**
	 * Has this screen finished transition-ing in?
	 * @return
	 */
	public boolean inTransitionFinished(){
		return true;
	}
	
	/**
	 * Draw what should be drawn when transition-ing in.
	 */
	public void renderInTransition(){
	}
	
	/**
	 * Update the transition in.
	 */
	public void updateInTransition(float delta){
	}	
	
	public final void renderIn(float delta){
		this.renderInTransition();
		this.updateInTransition(delta);
	}
}
