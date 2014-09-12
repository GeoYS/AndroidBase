package com.me.simplebase.catcher.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.me.simplebase.catcher.Textures;
import com.me.simplebase.catcher.game.base.Hud;
import com.me.simplebase.catcher.game.base.StageData;

public class LevelHud extends Hud{

	private StageData stageData;
	private BitmapFont font;
	private TextureRegion coin;
	
	private final int scale, posX, posY ; // bottom left corner pos
	private final int imageWidth, imageHeight;
	private final Matrix4 transform;
	private final Matrix4 oldTransform = new Matrix4();
	
	{
		scale = 2;
		imageHeight = scale * 32;
		imageWidth = scale * 32;
		posX = Gdx.graphics.getWidth() - 200;
		posY = Gdx.graphics.getHeight() - 100;
		transform = new Matrix4().translate(posX, posY, 0).scl(scale)
				;
	}
	
	public LevelHud(StageData stageData){
		this.stageData = stageData;
		font = new BitmapFont();
		coin = new TextureRegion(Textures.COIN, 0, 0, 32, 32);
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		String scoreText = "X " + stageData.coinsCollected;
		oldTransform.set(batch.getTransformMatrix());
		batch.setTransformMatrix(transform);
		font.draw(batch, scoreText, imageWidth, font.getCapHeight() * scale);
		batch.draw(coin,0,0);
		batch.setTransformMatrix(oldTransform);
	}

}
