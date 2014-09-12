package com.me.simplebase.catcher.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.me.simplebase.catcher.game.base.Stage;
import com.me.simplebase.catcher.game.base.StageData;
import com.me.simplebase.catcher.game.base.Thing;
import com.me.simplebase.catcher.game.obstacles.Brick;
import com.me.simplebase.catcher.game.obstacles.Coin;

/**
 * Detects and handles collisions between the Catchers (main and mirror) and other Things.
 * @author GeoYS_2
 *
 */
public class CollisionBlackBox {
	
	private Catcher main, mirror;
	private ArrayList<Thing> obstacles;
	private StageData stageData;
	
	public CollisionBlackBox(Catcher catcher, Catcher mirror, StageData stageData) {
		main = catcher;
		this.mirror = mirror;
		obstacles = new ArrayList<Thing>();
		this.stageData = stageData;
	}
	
	/**
	 * Define what occurs when some Thing collides with the catcher
	 * @param obstacle
	 * @param catcher
	 */
	private void handle(Thing obstacle, Catcher catcher){
		if(obstacle instanceof Coin){
			if(catcher.isEating()){
				obstacle.kill();
				stageData.coinsCollected += 1;
			}
		}
		else if(obstacle instanceof Brick){
			if(!catcher.isEating()){
				obstacle.kill();
			}
			else{
				catcher.kill();
			}
		}
	}
	
	public void addObstacle(Thing thing){
		obstacles.add(thing);
	}
	
	/**
	 * Call to check and handle any collisions.
	 */
	public void checkAndHandle(){
		if(!main.isDead()){
			for(Thing obstacle : obstacles){
				if(obstacle.getCollisionBox().overlaps(main.getCollisionBox())){
					handle(obstacle, main);
				}
			}
		}
		
		removeDead();
		
		if(mirror.isActive() && !mirror.isDead()){
			for(Thing obstacle : obstacles){
				if(obstacle.getCollisionBox().overlaps(mirror.getCollisionBox())){
					handle(obstacle, mirror);
				}
			}
		}		

		removeDead();
	}
	
	/**
	 * Removes obstacles that have been "kill()"ed.
	 */
	private void removeDead(){
		for(int i = obstacles.size() - 1; i >= 0; i --){
			if(obstacles.get(i).isDead()){
				obstacles.remove(i);
			}
		}
	}
	
}
