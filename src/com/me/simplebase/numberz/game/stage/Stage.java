package com.me.simplebase.numberz.game.stage;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.me.simplebase.base.Game;
import com.me.simplebase.base.util.Stopwatch;
import com.me.simplebase.numberz.game.catcher.AutoCatcher;
import com.me.simplebase.numberz.game.catcher.Catcher;
import com.me.simplebase.numberz.game.num.Num;
import com.me.simplebase.numberz.screens.MainScreen;

public abstract class Stage extends InputAdapter{
		
	private StageData stageData;
	private ArrayList<InputAdapter> inputAdapters;
	
	public Stage(){
		stageData = new StageData();
		inputAdapters = new ArrayList<InputAdapter>();
	}
	
	/**
	 * Render the level/stage/world/whatever.
	 */
	public abstract void draw(SpriteBatch batch);
	
	/**
	 * Update the level/stage/world/whatever.
	 * @param delta
	 */
	public abstract void update(float delta);
	
	/**
	 * Initialise the level/stage/world/whatever.
	 * @param screenManager
	 */
	public abstract void init();
	
	/**
	 * Create a new instance of StageData for this Stage.
	 */
	public void resetStageData(){
		stageData = new StageData();
	}
	
	/**
	 * Get the stage data, which could include score, time taken, or
	 * or level/stage related information. 
	 * @return
	 */
	public StageData stageData(){
		return stageData;
	}
	
	public boolean addInputAdapter(InputAdapter inputAdapter){
		return inputAdapters.add(inputAdapter);
	}
	
	public boolean removeInputAdapter(InputAdapter inputAdapter){
		return inputAdapters.remove(inputAdapter);
	}
	
	public void clearInputAdapters(){
		inputAdapters.clear();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for(InputAdapter ia : inputAdapters){
			if(ia.touchDown(screenX, screenY, pointer, button)){
				return true;
			}
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		for(InputAdapter ia : inputAdapters){
			if(ia.touchDragged(screenX, screenY, pointer)){
				return true;
			}
		}
		return super.touchDragged(screenX, screenY, pointer);
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		for(InputAdapter ia : inputAdapters){
			if(ia.touchUp(screenX, screenY, pointer, button)){
				return true;
			}
		}
		return super.touchUp(screenX, screenY, pointer, button);
	}
	
	public static final Stage CatcherControlStage = new Stage(){
		
		private final long RESPAWNTIME = 1000; // in milliseconds
		
		private Catcher catcher;
		private ArrayList<Num> nums, numsToRemove;
		private Stopwatch numTimer;
		private BitmapFont font;
		private Rule rule;
		
		@Override
		public void init() {
			// initialising stuff
			clearInputAdapters();
			catcher = new Catcher();
			this.addInputAdapter(catcher.getController());
			nums = new ArrayList<>();
			numsToRemove = new ArrayList<>();
			numTimer = new Stopwatch();
			numTimer.start();
			font = new BitmapFont();
			font.setScale(3);
			resetStageData();
			rule = Rule.LT_TEN;
		}

		@Override
		public void draw(SpriteBatch batch) {
			// clear black background
	        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);        

	        // draw the "rule" in the bg
			String rule = this.rule.toString();
			font.setColor(Color.PINK);
			font.setScale(4);
			font.draw(batch, rule,
					Gdx.graphics.getWidth() / 2 - font.getSpaceWidth() * rule.length() / 2,
					Gdx.graphics.getHeight() / 2 + font.getCapHeight());
			font.setScale(1);
	        
			// draw catcher
			catcher.draw(batch);			

			// draw nums
			for(Num n : nums){
				n.draw(batch);
			}
			
			// draw score
			String score = "Score: " + stageData().score;
			font.setColor(Color.YELLOW);
			font.setScale(3);
			font.draw(batch, score,
					Gdx.graphics.getWidth() / 2 - font.getSpaceWidth() * score.length() / 2,
					Gdx.graphics.getHeight());
		}

		@Override
		public void update(float delta) {
			catcher.update(delta);
		
			// spawn new Num
			if(numTimer.time() > RESPAWNTIME){
				nums.add(new Num(catcher));
				numTimer.restart();
			}
			
			// update Nums, check for catcher overlap
			for(Num n : nums){
				n.update(delta);
				
				Rectangle num = new Rectangle(n.getX(), n.getY(), Num.WIDTH, Num.HEIGHT),
					cat = new Rectangle(catcher.getX(), catcher.getY(), Catcher.WIDTH, Catcher.HEIGHT);
				if(num.overlaps(cat)){
					numsToRemove.add(n);
					if(!catcher.isClosed()){
						n.getOp().op(catcher);
					}	
				}
			}
			
			// remove overlapped Nums
			for(Num n : numsToRemove){
				nums.remove(n);
			}
			
			int catcherToAdd = catcher.getToAdd();
			
			// randomize rule(s) after submission
			if(catcherToAdd > 0){
				if(Math.random() < 0.5){
					rule = Rule.LT_TEN;
				}
				else{
					rule = Rule.EVEN;
				}
			}
			
			// add catcher score
			stageData().score += catcherToAdd;
			
			// check if catcher satisfies rule
			if(!rule.isSatisfied(catcher)){
				stageData().lost = true;
			}
		}
	};
	
	public static final Stage NumTapStage = new Stage(){
		
		private final long RESPAWNTIME = 1000; // in milliseconds
		
		private AutoCatcher catcher;
		private ArrayList<Num> nums, numsToRemove;
		private Stopwatch numTimer;
		private BitmapFont font;
		private Rule rule;
		
		@Override
		public void init() {
			// initialising stuff
			clearInputAdapters();
			catcher = new AutoCatcher();
			nums = new ArrayList<>();
			numsToRemove = new ArrayList<>();
			numTimer = new Stopwatch();
			numTimer.start();
			font = new BitmapFont();
			resetStageData();
			rule = Rule.LT_TEN;
		}

		@Override
		public void draw(SpriteBatch batch) {
			// clear black background
	        Gdx.gl.glClearColor(Color.MAROON.r, Color.MAROON.g, Color.MAROON.b, Color.MAROON.a);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);        

	        // draw the "rule" in the bg
			String rule = this.rule.toString();
			font.setColor(Color.PINK);
			font.setScale(4);
			font.draw(batch, rule,
					Gdx.graphics.getWidth() / 2 - font.getSpaceWidth() * rule.length() / 2,
					Gdx.graphics.getHeight() / 2 + font.getCapHeight());
			font.setScale(1);
	        
			// draw catcher
			catcher.draw(batch);			

			// draw nums
			for(Num n : nums){
				n.draw(batch);
			}
			
			// draw score
			String score = "Score: " + stageData().score;
			font.setColor(Color.YELLOW);
			font.setScale(3);
			font.draw(batch, score,
					Gdx.graphics.getWidth() / 2 - font.getSpaceWidth() * score.length() / 2,
					Gdx.graphics.getHeight());
		}

		@Override
		public void update(float delta) {
			catcher.update(delta);
		
			// spawn new Num
			if(numTimer.time() > RESPAWNTIME){
				Num newNum = new Num(catcher);
				nums.add(newNum);
				this.addInputAdapter(newNum.getController());
				numTimer.restart();
			}
			
			// update Nums, check for catcher overlap
			for(Num n : nums){
				n.update(delta);
				
				// if overlaps with catcher
				Rectangle num = new Rectangle(n.getX(), n.getY(), Num.WIDTH, Num.HEIGHT),
					cat = new Rectangle(catcher.getX(), catcher.getY(), Catcher.WIDTH, Catcher.HEIGHT);
				if(num.overlaps(cat)){
					numsToRemove.add(n);
					if(!catcher.isClosed()){
						n.getOp().op(catcher);
					}	
				}
				// if is tapped
				else if(n.isTapped()){
					numsToRemove.add(n);					
				}
			}
			
			// remove overlapped/tapped Nums
			for(Num n : numsToRemove){
				this.removeInputAdapter(n.getController());
				nums.remove(n);
			}
			
			int catcherToAdd = catcher.getToAdd();
			
			// randomize rule(s) after submission
			if(catcherToAdd > 0){
				if(Math.random() < 0.5){
					rule = Rule.LT_TEN;
				}
				else{
					rule = Rule.EVEN;
				}
			}
			
			// add catcher score
			stageData().score += catcherToAdd;
			
			// check if catcher satisfies rule
			if(!rule.isSatisfied(catcher)){
				stageData().lost = true;
			}
		}
		
	};
}
