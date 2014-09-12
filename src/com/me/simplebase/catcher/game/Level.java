package com.me.simplebase.catcher.game;

import java.util.ArrayDeque;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.me.simplebase.catcher.game.base.Thing;
import com.me.simplebase.catcher.game.obstacles.Brick;
import com.me.simplebase.catcher.game.obstacles.Coin;
import com.me.simplebase.catcher.game.obstacles.Mirror;

public class Level {
	
	public static final Level TEST_LEVEL = new Level("Catcher/data/testlevel.txt");
	
	private ArrayDeque<ObstacleData> obstacleQueue;
	private float totalTime;
	
	public Level(String levelPath){
		obstacleQueue = new ArrayDeque<>();
		
		FileHandle levelFile = Gdx.files.internal(levelPath);
		String text = levelFile.readString();

		Scanner levelScanner = new Scanner(text);
		
		totalTime = levelScanner.nextFloat();
		
		while(levelScanner.hasNext()){
			float yDiff = levelScanner.nextFloat(), xPercent = levelScanner.nextFloat();
			String type = levelScanner.next();
			String[] properties = levelScanner.nextLine().trim().split(" ");
			obstacleQueue.add(new ObstacleData(yDiff, xPercent, type, properties));
		}
		
		levelScanner.close();
	}
	
	/**
	 * Returns an deque of Obstacles, which wrap an Thing
	 * and the time it should be released.
	 * @return
	 */
	public ArrayDeque<Obstacle> getObstacleQueue(){
		ArrayDeque<Obstacle> obstacles = new ArrayDeque<>();
		for(ObstacleData obstacleData : obstacleQueue){
			obstacles.add(new Obstacle(obstacleData));
		}
		return obstacles;
	}
	
	/**
	 * Returns the total time in seconds this level lasts.
	 * @return
	 */
	public float getTotalTime(){
		return totalTime;
	}
	
	public class Obstacle{
		
		/**
		 * Vertical distance from last release.
		 */
		public float interval;
		
		/**
		 * Actual Thing representing obstacle.
		 */
		public Thing thing;
		
		public Obstacle(ObstacleData obstacleData) {
			this.interval = obstacleData.interval;
			
			switch(obstacleData.type){
				case "coin" : {
					thing = new Coin(Gdx.graphics.getWidth() * obstacleData.xPercent);
					break;
				}
				case "brick" : {
					thing = new Brick(Gdx.graphics.getWidth() * obstacleData.xPercent);
					break;
				}
				case "mirror" : {
					thing = new Mirror(obstacleData.properties[0].equals("left"),
							Float.parseFloat(obstacleData.properties[1]));
					break;
				}
			}
		}
	}
	
	private class ObstacleData{
		
		/**
		 * Y (vertical) distance from last release.
		 */
		public float interval;
		
		/**
		 * Where horizontally the obstacle starts,
		 * based on percent of the screen width.
		 */
		public float xPercent;
		
		/**
		 * Type of obstacle (eg. brick, coin, etc.)
		 */
		public String type;
		
		public String[] properties;
		
		public ObstacleData(float interval, float xPercent, String type, String[] properties) {
			this.interval = interval;
			this.type = type;
			this.xPercent = xPercent;
			this.properties = properties;
		}
	}
}
