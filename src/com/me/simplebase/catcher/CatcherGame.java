package com.me.simplebase.catcher;

import com.me.simplebase.base.Game;
import com.me.simplebase.catcher.screens.GameScreen;
import com.me.simplebase.catcher.screens.LevelScreen;
import com.me.simplebase.catcher.screens.MainScreen;

public class CatcherGame extends Game{
	public CatcherGame() {
		super("Catcher");
	}
	@Override
	public void create() {
		this.addScreen(new GameScreen());
		this.addScreen(new MainScreen());
		this.addScreen(new LevelScreen());
		this.initScreens();
		this.enterScreen(MainScreen.ID);
	}
}
