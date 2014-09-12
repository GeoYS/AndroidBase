package com.me.simplebase.catcher.screens.ui;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.me.simplebase.catcher.Textures;

public class LevelButtonStyle extends ImageButtonStyle{
	public static ImageButtonStyle LEVEL1 = new ImageButtonStyle(){
		{
			up = new NinePatchDrawable(new NinePatch(Textures.LEVEL1_UP));
			down = new NinePatchDrawable(new NinePatch(Textures.LEVEL1_DOWN));
			over = new NinePatchDrawable(new NinePatch(Textures.LEVEL1_OVER));
		}
	};
}
