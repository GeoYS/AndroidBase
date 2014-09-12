package com.me.simplebase.catcher.screens.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.me.simplebase.base.Game;
import com.me.simplebase.catcher.Textures;
import com.me.simplebase.catcher.screens.LevelScreen;

public class MainMenu extends Stage{
	
	private Image background, title;
	private Button play;
	
	public MainMenu(final Game sm) {
		super();
		background = new Image(Textures.TITLE_BACKGROUND);
		this.addActor(background);
		title = new Image(Textures.TITLE);
		title.setPosition(Gdx.graphics.getWidth() / 2 - title.getWidth() / 2, Gdx.graphics.getHeight() * 4 / 5);
		this.addActor(title);
		play = new ImageButton(PlayButtonStyle.instance);
		play.setPosition(Gdx.graphics.getWidth() / 2 - play.getWidth() / 2, Gdx.graphics.getHeight() / 4);
		play.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				sm.enterScreen(LevelScreen.ID);
			}
		});
		this.addActor(play);
	}

}
