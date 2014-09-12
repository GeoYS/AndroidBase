package com.me.simplebase.blockharvest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.me.simplebase.base.Game;

public class MainMenu extends Stage{
	public MainMenu(final Game game){
		super();
		
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
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		//textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		// Create a table that fills the screen. Everything else will go inside this table.
		/*Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);*/

		final TextButton play = new TextButton("Play", skin), highscores = new TextButton("Highscores", skin);
		
		play.setPosition(0, 0);
		play.setWidth(Gdx.graphics.getWidth() / 4);
		play.setHeight(Gdx.graphics.getHeight() / 5);
		play.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				game.enterScreen(GameScreen.ID);
			}
		});
		
		highscores.setPosition(0, -play.getHeight() * 1.25f);
		highscores.setWidth(Gdx.graphics.getWidth() / 4);
		highscores.setHeight(Gdx.graphics.getHeight() / 5);
		highscores.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				game.enterScreen(HighscoreScreen.ID);
			}
		});
		
		this.addActor(play);
		this.addActor(highscores);
	}
}
