package com.me.simplebase.blockharvest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.simplebase.base.InputScreen;
import com.me.simplebase.base.Game;
import com.me.simplebase.blockharvest.blocks.Block;
import com.me.simplebase.blockharvest.visual.TempTextDrawer;

public class GameScreen extends InputScreen{

	public static int ID = 3;
	
	private Block[][] blocks;
	private SpriteBatch batch;
	private int blocksActive, score, combo;
	private int maxBlocksActive;
	private BitmapFont font;
	private String lastHarvest;
	private boolean lost;
	private Game sm;
	private float timeCounter, lostTimer;
	private TempTextDrawer text = null;
	public static LifeBar lifeBar;
	
	@Override
	public void init(Game game) {
		sm = game;
		batch = new SpriteBatch();
		blocks = new Block[4][4];
		for(int y = 0; y < 4; y ++){
			for(int x = 0; x < 4; x ++)
				blocks[y][x] = new Block(Gdx.graphics.getWidth() * x / 4f,
						Gdx.graphics.getHeight() * y / 4,
						Gdx.graphics.getWidth() / 4,
						Gdx.graphics.getHeight() / 4);
		}
		blocksActive = 0;
		score = 0;
		maxBlocksActive = 1;
		font = new BitmapFont();
		lost = false;
		lastHarvest = "";
		timeCounter = 0;
		lostTimer = 0;
		lifeBar = new LifeBar();
		combo = 1;
		text = null;
	}
	
	@Override
	public void update(float delta) {
		if(!lost){
			lifeBar.removeTime(delta);
			
			// increase in difficulty
			timeCounter += delta;
			if(timeCounter > 15){
				if(Math.random() < 0.5){
					maxBlocksActive ++;
				}
				else{
					for(int y = 0; y < 4; y ++){
						for(int x = 0; x < 4; x ++)
							blocks[y][x].setTimeToReach(blocks[y][x].getTimeToReach() * 0.9f);
					}
				}
				timeCounter = 0;
			}
			
			// find number of blocks started
			blocksActive = 0;
			for(int y = 0; y < 4; y ++){
				for(int x = 0; x < 4; x ++)
					blocksActive += blocks[y][x].hasStarted() ? 1 : 0;
			}
			
			//set new blocks to be active
			int tempActivated = 0;
			for(int y = 0; y < 4; y ++){
				for(int x = 0; x < 4; x ++){
					if(tempActivated >= 1){
						continue;
					}
					if(blocksActive < maxBlocksActive 
							&& !blocks[y][x].hasStarted()
							// on the beat
							//&& System.currentTimeMillis() % (int)(blocks[y][x].getTimeToReach() * 1000) / 4 < 8 // 16 ms interval for 60 fps
							&& Math.random() < 1f / 16){
						blocks[y][x].start();
						blocksActive ++;
						tempActivated ++;
					}
				}
			}
			
			// update blocks
			for(int y = 0; y < 4; y ++){
				for(int x = 0; x < 4; x ++){
					blocks[y][x].update(delta);
					
					if(blocks[y][x].timeElapsed() > blocks[y][x].getTimeToReach()){
						blocks[y][x].reset();
						lifeBar.removeTime(5);
					}
					
					int accuracy = blocks[y][x].getAccuracy();
					
					handleAccuracy(accuracy);
					
					int points = blocks[y][x].getPoints();
					if (points > 0){
						score += points * combo;
					}
				}
			}
			
			if(!lifeBar.hasTimeLeft()){
				lost = true;
			}
		}
		else{
			lostTimer += delta;
			if(lostTimer > 4){
				Highscores.addScore(score);
				sm.enterScreen(MainMenuScreen.ID);
			}
		}
	}
	
	private void handleAccuracy(int accuracy){		

		if(accuracy == -1){
			lastHarvest = "";
			return;
		}
		else if(accuracy < 45){
			lastHarvest =  "OMG BAD";
		}
		else if(accuracy < 55){
			lastHarvest =  "Terrible";
		}
		else if(accuracy < 65){
			lastHarvest =  "Bad";
		}
		else if(accuracy < 70){
			lastHarvest =  "Okay";
		}
		else if(accuracy < 80){
			lastHarvest =  "Good";
		}
		else if(accuracy < 85){
			lastHarvest =  "Very good";
		}
		else if(accuracy < 90){
			lastHarvest =  "Excellent";
		}
		else{
			lastHarvest =  "PERFECT!" + " x" + combo;
		}
		
		if(!lastHarvest.isEmpty()){
			text = new TempTextDrawer(lastHarvest,
					Gdx.graphics.getWidth() / 5,
					Gdx.graphics.getHeight() / 2,
					4 * Gdx.graphics.getWidth() / 480,
					500);
		}
		
		if(accuracy < 65){
			lifeBar.removeTime(5);
		}
		else if(accuracy >= 80){
			lifeBar.addTime(2);
		}
		
		if(accuracy >= 90){
			combo ++;
		}
		else{
			combo = 1;
		}
		
	}

	@Override
	public void render() {
		batch.begin();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		for(int y = 0; y < 4; y ++){
			for(int x = 0; x < 4; x ++)
				blocks[y][x].render(batch);
		}		
		
		font.setColor(Color.WHITE);
		font.setScale(Gdx.graphics.getWidth() / 480);
		font.draw(batch, "Score: " + score,
				Gdx.graphics.getWidth() / 10,
				Gdx.graphics.getHeight() / 10);
		
		//lifeBar.draw(batch);
		
		/*font.setColor(Color.GREEN);
		font.setScale(4 * Gdx.graphics.getWidth() / 480);
		font.draw(batch, lastHarvest + (lastHarvest.equals("PERFECT!") ? " x" + (combo - 1) : ""),
				Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 2);*/
		
		if(text == null){}
		else if(text.isFinished()){
			text = null;
		}
		else{
			text.draw(batch);
		}
		
		batch.end();
	}
	
    @Override
    public void show() {
    	blocks = new Block[4][4];
		for(int y = 0; y < 4; y ++){
			for(int x = 0; x < 4; x ++)
				blocks[y][x] = new Block(Gdx.graphics.getWidth() * x / 4f,
						Gdx.graphics.getHeight() * y / 4,
						Gdx.graphics.getWidth() / 4,
						Gdx.graphics.getHeight() / 4);
		}
		blocksActive = 0;
		score = 0;
		maxBlocksActive = 1;
		font = new BitmapFont();
		lost = false;
		lastHarvest = "";
		timeCounter = 0;
		lostTimer = 0;
		lifeBar = new LifeBar();
		combo = 1;
		text = null;
    }
	
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for(int y = 0; y < 4; y ++){
			for(int x = 0; x < 4; x ++)
				blocks[y][x].touchDown(screenX, screenY, pointer, button);
		}
		return true;
	}

	/**
	 * Might be useful for the player to be able to drag and collect? When there are a lot maybe?
	 */
	/*@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		for(int y = 0; y < 4; y ++){
			for(int x = 0; x < 4; x ++)
				blocks[y][x].touchDragged(screenX, screenY, pointer);
		}
		return true;
	}*/
	
	@Override
	public int getID() {
		return ID;
	}

}
