package com.me.simplebase.catcher.game.obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.me.simplebase.base.g2d.BatchDrawer;
import com.me.simplebase.catcher.Textures;

public class BrickDrawer extends BatchDrawer{

	private Brick brick;
	
	public BrickDrawer(Brick brick) {
		this.brick = brick;
	}
	
	@Override
	public void drawImage(SpriteBatch batch) {
		batch.draw(Textures.BRICK, 0, 0);
	}

	@Override
	public void applyTransform(Matrix4 transform) {
		transform.translate(brick.getX(), brick.getY(), 0);
	}

}
