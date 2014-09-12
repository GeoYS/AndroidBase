package com.me.simplebase.catcher.game.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.simplebase.catcher.Textures;
import com.me.simplebase.catcher.game.base.Vfx;

public class MirrorVfx implements Vfx{

	private boolean left;
	private Mirror mirror;
	
	public MirrorVfx(Mirror mirror) {
		this.left = mirror.left;
		this.mirror = mirror;
	}
	
	@Override
	public void update(float delta) {		
	}

	@Override
	public void draw(SpriteBatch batch) {
		float yVal;
		
		if(mirror.getY() > Gdx.graphics.getHeight()){
			return;
		}
		else if(mirror.getY() < Gdx.graphics.getHeight() && mirror.getY() > 0){
			yVal = mirror.getY();
		}
		else if(mirror.getY() + mirror.length < Gdx.graphics.getHeight()){
			yVal = -(Gdx.graphics.getHeight() - (mirror.getY() + mirror.length));
		}
		else{
			yVal = 0;
		}
		
		if(left){
			batch.draw(Textures.MIRROR, 0, yVal);
		}
		else{
			batch.draw(Textures.MIRROR, Gdx.graphics.getWidth()/2, yVal);			
		}
	}

	@Override
	public boolean isFinished() {
		return mirror.getY() + mirror.length < 0;
	}

}
