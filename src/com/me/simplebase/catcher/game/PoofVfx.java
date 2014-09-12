package com.me.simplebase.catcher.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.simplebase.base.g2d.SpriteSheet;
import com.me.simplebase.base.util.Stopwatch;
import com.me.simplebase.catcher.Textures;
import com.me.simplebase.catcher.game.base.Vfx;

public class PoofVfx implements Vfx{

	private SpriteSheet poofSpriteSheet;
	private Animation poofAnimation;
	private Stopwatch stateTimer;
	private float x, y;
	
	public PoofVfx(float x, float y) {
		poofSpriteSheet = new SpriteSheet(Textures.POOF, 64, 64);
		poofAnimation = new Animation(0.25f, poofSpriteSheet.getFrames());
		stateTimer = new Stopwatch();
		this.y = y;
		this.x = x;
	}
	
	@Override
	public void update(float delta) {
	}

	@Override
	public void draw(SpriteBatch batch) {
		if(!stateTimer.isRunning()){
			stateTimer.start();
		}
		batch.draw(poofAnimation.getKeyFrame(stateTimer.time() / 1000f), x, y);
	}

	@Override
	public boolean isFinished() {
		return poofAnimation.isAnimationFinished(stateTimer.time() / 1000f);
	}

}
