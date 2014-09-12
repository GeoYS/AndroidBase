package com.me.simplebase.catcher.game.base;

import java.util.ArrayList;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Stage extends InputAdapter{
		
	private StageData stageData;
	private ArrayList<Thing> things;
	private boolean isPaused, isResuming;
	
	public Stage(){
		stageData = new StageData();
		things = new ArrayList<>();
		isPaused = false;
		isResuming = false;
	}
	
	/**
	 * Render all Things in this stage, which can represent 
	 * the player, enemies, or the environment. Things are drawn in the
	 * order they are added to the Stage.
	 */
	public final void draw(SpriteBatch batch){
		backgroundDraw(batch);
		for(Thing thing : things){
			if(thing.isActive()){
				thing.getDrawer().draw(batch);
			}
		}
		foregroundDraw(batch);
	}
	
	/**
	 * Called before the Stage's Things are drawn.
	 * Draw things like the background image by overriding this.
	 * @param batch
	 */
	protected void backgroundDraw(SpriteBatch batch){
		
	}
	
	/**
	 * Called after the Stage's Things are drawn.
	 * Draw things that belong in the foreground by overriding this.
	 * @param batch
	 */
	protected void foregroundDraw(SpriteBatch batch){
		
	}
	
	/**
	 * Update the level/stage/world/whatever.
	 * First controls all the Things (altering state),
	 * then updates the Things based on their state
	 * (preUpdate and postUpdate called here),
	 * and finally removes dead Things.
	 * @param delta Time in seconds since last update
	 */
	public final void update(float delta){
		if(!isPaused && !isResuming){
			for(Thing thing : things){
				if(thing.isActive()){
					thing.getController().control();
				}
			}
			preUpdate(things, delta);
			for(Thing thing : things){
				if(thing.isActive()){
					thing.getUpdater().update(delta);
				}
			}
			postUpdate(things, delta);
			for(int i = things.size() - 1; i >= 0; i --){
				if(things.get(i).isDead()){
					things.remove(i);
				}
			}
		}
		else if(isPaused && isResuming){
			isPaused = false;			
		}
		else if(isResuming){
			isResuming = false;
		}
	}
	
	/**
	 * Called before the main update code is run.
	 * @param things All things in this stage
	 * @param delta Time in seconds since last update
	 */
	protected void preUpdate(ArrayList<Thing> things, float delta){
		
	}
	
	/**
	 * Called after the main update code is run, but before 
	 * dead Things are removed.
	 * @param things All things in this stage
	 * @param delta Time in seconds since last update
	 */
	protected void postUpdate(ArrayList<Thing> things, float delta){
		
	}
	
	/**
	 * Create a new instance of StageData for this Stage
	 * and removes all things.
	 */
	protected final void clearStage(){
		stageData = new StageData();
		things.clear();
	}
	
	public final void addThing(Thing thing){
		things.add(thing);
	}
	
	public final void removeThing(Thing thing){
		things.remove(thing);
	}
	
	/**
	 * Initialise the level/stage/world/whatever.
	 * @param screenManager
	 */
	public abstract void init();
	
	/**
	 * Create a new instance of StageData for this Stage.
	 */
	public final void resetStageData(){
		stageData = new StageData();
	}
	
	/**
	 * Get the stage data, which could include score, time taken, or
	 * or level/stage related information. 
	 * @return
	 */
	public final StageData stageData(){
		return stageData;
	}
	
	/**
	 * Stops the Stage from updating.
	 */
	public void pause(){
		isPaused = true;
	}
	
	/**
	 * Resumes the Stage's updating.
	 * Loops through update once doing nothing to
	 * reset delta time.
	 */
	public void resume(){
		isResuming = true;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for(Thing thing : things){
			if(thing.isActive() && thing.getController().touchDown(screenX, screenY, pointer, button)){
				return true;
			}
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		for(Thing thing : things){
			if(thing.isActive() && thing.getController().touchDragged(screenX, screenY, pointer)){
				return true;
			}
		}
		return super.touchDragged(screenX, screenY, pointer);
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		for(Thing thing : things){
			if(thing.isActive() && thing.getController().touchUp(screenX, screenY, pointer, button)){
				return true;
			}
		}
		return super.touchUp(screenX, screenY, pointer, button);
	}
}
