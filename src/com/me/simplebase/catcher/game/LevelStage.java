package com.me.simplebase.catcher.game;

import java.util.ArrayDeque;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.simplebase.base.g2d.BatchDrawer;
import com.me.simplebase.base.util.Stopwatch;
import com.me.simplebase.catcher.Textures;
import com.me.simplebase.catcher.game.Level.Obstacle;
import com.me.simplebase.catcher.game.base.Controller;
import com.me.simplebase.catcher.game.base.Stage;
import com.me.simplebase.catcher.game.base.Thing;
import com.me.simplebase.catcher.game.base.Vfx;
import com.me.simplebase.catcher.game.base.VfxLayer;
import com.me.simplebase.catcher.game.base.VfxSupplier;
import com.me.simplebase.catcher.game.obstacles.Mirror;

public class LevelStage extends Stage{

	private Level level;
	private Catcher catcher, mirror;
	private CollisionBlackBox collisions;
	private Stopwatch levelTimer;
	private VfxLayer backgroundVfx, foregroundVfx;
	
	public LevelStage(Level level) {
		super();
		this.level = level;
	}
	
	@Override
	public void init() {
		clearStage();
		catcher = new Catcher();
		this.addThing(catcher);
		mirror = new Catcher(){
			private Controller controller = new Controller(){			
				
				@Override
				public void control() {
					setX(Gdx.graphics.getWidth() - catcher.getX() - getWidth());
					setEating(catcher.isEating());
				}		
				
				@Override
				public boolean touchDown(int screenX, int screenY, int pointer,
						int button) {
					catcher.getController().touchDown(screenX, screenY, pointer, button);
					float newX = Gdx.graphics.getWidth() - catcher.getX() - getWidth();
					if(!(getX() <= newX + getWidth() / 2 && newX + getWidth() / 2 <= getX() + getWidth())){
						store(new PoofVfx(getX(), getY()));
					}
					return super.touchDown(screenX, screenY, pointer, button);
				}
			};
			public Controller getController() {
				return controller;
			};
			
			private BatchDrawer drawer = new MirrorCatcherDrawer(this);
			
			@Override
			public BatchDrawer getDrawer() {
				return drawer;
			}
		};
		mirror.setActive(false);
		this.addThing(mirror);
		collisions = new CollisionBlackBox(catcher, mirror, this.stageData());
		initObstacles();
		foregroundVfx = new VfxLayer();
		backgroundVfx = new VfxLayer();
		backgroundVfx.addVfx(new SmokeVfx());
		levelTimer = new Stopwatch();
		levelTimer.start();
	}
	
	private void initObstacles(){
		float currentY = Gdx.graphics.getHeight();
		ArrayDeque<Obstacle> obstacles;
		obstacles = level.getObstacleQueue();
		for(Obstacle o : obstacles){
			Thing obstacle = o.thing;
			currentY += o.interval;
			obstacle.setY(currentY);
			if(obstacle instanceof Mirror){
				((Mirror) obstacle).setCatchers(catcher, mirror);
			}
			this.addThing(obstacle);
			collisions.addObstacle(obstacle);
		}
	}
	
	@Override
	protected void preUpdate(ArrayList<Thing> things, float delta) {
		collisions.checkAndHandle();
	}
	
	@Override
	protected void postUpdate(ArrayList<Thing> things, float delta) {
		for(Thing thing : things){
			if(thing instanceof VfxSupplier){
				VfxSupplier supplier = (VfxSupplier) thing;
				foregroundVfx.addVfx(supplier.release());
			}
		}
		foregroundVfx.update(delta);
		backgroundVfx.update(delta);
	}
	
	@Override
	protected void backgroundDraw(SpriteBatch batch) {
		batch.draw(Textures.BACKGROUND, 0, 0);
		backgroundVfx.draw(batch);
	}
	
	@Override
	protected void foregroundDraw(SpriteBatch batch) {
		foregroundVfx.draw(batch);
	}
	
	public void pause(){
		super.pause();
		levelTimer.stop();
	}
	
	public void resume(){
		super.resume();
		levelTimer.start();
	}
}
