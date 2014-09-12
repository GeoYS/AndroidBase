package com.me.simplebase.catcher.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Matrix4;
import com.me.simplebase.base.g2d.BatchDrawer;
import com.me.simplebase.base.g2d.SpriteSheet;
import com.me.simplebase.base.util.Positionable;
import com.me.simplebase.base.util.Stopwatch;
import com.me.simplebase.catcher.Textures;

public class CatcherDrawer extends BatchDrawer{
	
	private final float ANIMATION_FRAME_SPEED = 0.05f;
	
	protected Catcher catcher;
	protected Animation catcherAnimation;
	protected Stopwatch animationTimer;
	
	public CatcherDrawer(Catcher catcher){
		this.catcher = catcher;
		animationTimer = new Stopwatch();
		animationTimer.start();
		
		Texture texture = Textures.CATCHER;
		int regionWidth, regionHeight;
		
		regionWidth = regionHeight = 64;
		
		SpriteSheet sprites = new SpriteSheet(texture, regionWidth, regionHeight);
		
		catcherAnimation = new Animation(ANIMATION_FRAME_SPEED,
				sprites.getFrames());
		catcherAnimation.setPlayMode(PlayMode.LOOP);
	}
	
	protected TextureRegion getImage() {
		if(catcher.isEating()){
			float stateTime = animationTimer.time() / 1000f;
			return catcherAnimation.getKeyFrame(stateTime);
		}
		else{
			return catcherAnimation.getKeyFrames()[0];
		}
	}
	
	@Override
	public void drawImage(SpriteBatch batch) {
		batch.draw(getImage(), 0, 0);
	}

	@Override
	public void applyTransform(Matrix4 transform) {
		transform.translate(catcher.getX(), catcher.getY(), 0);
	}

}
