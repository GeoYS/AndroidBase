package com.me.simplebase.catcher.game.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.me.simplebase.base.g2d.BatchDrawer;
import com.me.simplebase.base.g2d.SpriteSheet;
import com.me.simplebase.base.util.Stopwatch;
import com.me.simplebase.catcher.Textures;

public class CoinDrawer extends BatchDrawer{

	private final float ANIMATION_FRAME_SPEED = 0.25f;
	
	private Coin coin;
	private Animation coinAnimation;
	private Stopwatch animationTimer;
	
	public CoinDrawer(Coin coin) {
		this.coin = coin;		

		animationTimer = new Stopwatch();
		animationTimer.start();
		
		Texture texture = Textures.COIN;
		int regionWidth, regionHeight;
		
		regionWidth = regionHeight = 32;
		
		SpriteSheet sprites = new SpriteSheet(texture, regionWidth, regionHeight);
		
		coinAnimation = new Animation(ANIMATION_FRAME_SPEED,
				sprites.getFrames());
		coinAnimation.setPlayMode(PlayMode.LOOP);
	}
	
	@Override
	public void drawImage(SpriteBatch batch) {
		batch.draw(coinAnimation.getKeyFrame(animationTimer.time() / 1000f), 0, 0);		
	}

	@Override
	public void applyTransform(Matrix4 transform) {
		transform.translate(coin.getX(), coin.getY(), 0);
	}

}
