package com.me.simplebase.blockharvest;

import com.me.simplebase.base.Game;
import com.me.simplebase.blockharvest.screens.GameScreen;
import com.me.simplebase.blockharvest.screens.HighscoreScreen;
import com.me.simplebase.blockharvest.screens.Highscores;
import com.me.simplebase.blockharvest.screens.MainMenuScreen;

public class BlockHarvestGame extends Game{
	
	public BlockHarvestGame(){
		super("Block Harvest");
	}
	
	 @Override
	public void create() {
		this.addScreen(new GameScreen());
		this.addScreen(new MainMenuScreen());
		this.addScreen(new HighscoreScreen());
		this.initScreens();
		this.enterScreen(MainMenuScreen.ID);
		Highscores.load();
		System.out.println("Highscores loaded.");
	}
	 
	 @Override
	public void pause() {
		super.pause();
		Highscores.save();
		System.out.println("Highscores saved.");
	}
}
