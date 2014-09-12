package com.me.simplebase.numberz;

import com.me.simplebase.base.Game;
import com.me.simplebase.numberz.screens.GameScreen;
import com.me.simplebase.numberz.screens.MainScreen;

public class NumberzGame extends Game{
	public NumberzGame(){
		super("Numberz");
	}

	@Override
	public void create() {
		this.addScreen(new GameScreen());
		this.addScreen(new MainScreen());
		this.initScreens();
		this.enterScreen(MainScreen.ID);
	}
}
