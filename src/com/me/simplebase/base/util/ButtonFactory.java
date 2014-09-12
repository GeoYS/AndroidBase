package com.me.simplebase.base.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ButtonFactory {
	
	private static Skin skin;
	
	static {
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
	}
	
	/**
	 * Make a new plain text button.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param text
	 * @param changeListener
	 * @return
	 */
	public static TextButton newTextButton(
			float x, float y, 
			float width, float height,
			String text, ChangeListener changeListener)
	{
		final TextButton newButton = new TextButton("Play", skin);
		
		newButton.setPosition(x, y);
		newButton.setWidth(width);
		newButton.setHeight(height);
		newButton.addListener(changeListener);	
		
		return newButton;
	}
}
