package com.me.simplebase.numberz.game.catcher;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.me.simplebase.base.g2d.BatchDrawer;

public class CatcherDrawer extends BatchDrawer{
	
	private Catcher catcher;
	private ShapeRenderer renderer;
	private BitmapFont font;
	
	public CatcherDrawer(Catcher catcher) {
		this.catcher = catcher;
		this.renderer = new ShapeRenderer();
		this.font = new BitmapFont();
	}
	
	@Override
	public void drawImage(SpriteBatch batch) {
		
		batch.end();
		drawShape(batch);
		batch.begin();
		
		drawString(batch);
	}

	protected void drawShape(SpriteBatch batch){
		renderer.setColor(Color.ORANGE);
		renderer.begin(catcher.isClosed() ? ShapeType.Filled : ShapeType.Line);
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.rect(0, 0, Catcher.WIDTH, Catcher.HEIGHT);
		renderer.end();
	}
	
	protected void drawString(SpriteBatch batch) {

		String value = catcher.getValue() + "";
		font.setColor(catcher.isClosed() ? Color.BLACK : Color.ORANGE);
		font.setScale(5);
		font.draw(batch,
				value,
				Catcher.WIDTH / 2 - font.getSpaceWidth() * value.length() / 2,
				Catcher.HEIGHT / 2 + font.getCapHeight() / 2);
	}
	
	@Override
	public void applyTransform(Matrix4 transform) {
		transform.translate(catcher.getX(), catcher.getY(), 0);
		transform.scale(catcher.getScale(), catcher.getScale(), 0);
	}
	
	public ShapeRenderer getRenderer() {
		return renderer;
	}

}
