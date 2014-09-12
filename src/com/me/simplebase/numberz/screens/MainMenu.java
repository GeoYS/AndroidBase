package com.me.simplebase.numberz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.me.simplebase.base.Game;

public class MainMenu extends Stage{
	public MainMenu(final Game game){
		super();		

		this.getRoot().setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8,
				Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 10);
		
		Skin skin;
		
		skin = new Skin();

		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		// Store the default libgdx font under the name "default".
		skin.add("default", new BitmapFont());

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.GREEN);
		textButtonStyle.down = skin.newDrawable("white", Color.GREEN);
		//textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", new Color(0.4f, .9f, 0.2f, 1));
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		// Create a table that fills the screen. Everything else will go inside this table.
		/*Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);*/

		final TextButton play = new TextButton("Play", skin);
		
		play.setPosition(0, 0);
		play.setWidth(Gdx.graphics.getWidth() / 4);
		play.setHeight(Gdx.graphics.getHeight() / 5);
		play.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				game.enterScreen(GameScreen.ID);
			}
		});
		this.addActor(play);
	}
}
