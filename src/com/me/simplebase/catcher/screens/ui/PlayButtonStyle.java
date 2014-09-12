package com.me.simplebase.catcher.screens.ui;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.me.simplebase.catcher.Textures;

public class PlayButtonStyle extends ImageButtonStyle{
	
	public static final PlayButtonStyle instance = new PlayButtonStyle();
	
	private PlayButtonStyle() {
		this.up = new NinePatchDrawable(new NinePatch(Textures.PLAY_UP));
		this.down = new NinePatchDrawable(new NinePatch(Textures.PLAY_DOWN));
		this.over = new NinePatchDrawable(new NinePatch(Textures.PLAY_HOVER));
	}
}
