package com.me.simplebase;

import com.badlogic.gdx.graphics.FPSLogger;
import com.me.simplebase.base.GameApp;
import com.me.simplebase.base.Game;
import com.me.simplebase.blockharvest.BlockHarvestGame;
import com.me.simplebase.catcher.CatcherGame;
import com.me.simplebase.numberz.NumberzGame;

public class MainClass extends GameApp {
	
	/**
	 * Constants for the application width and height for desktop and html5.
	 */
	public static final int SCREEN_WIDTH = 480, SCREEN_HEIGHT = 640;
	
	private FPSLogger logger = new FPSLogger();
	
	@Override
	public Game game() {
		return new CatcherGame();
	}
	
	@Override
	public void render() {
		super.render();
		logger.log();
	}
}
