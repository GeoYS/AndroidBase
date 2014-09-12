package com.me.simplebase.numberz.game.num;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.me.simplebase.base.g2d.BatchDrawer;

public class NumDrawer extends BatchDrawer{

	private Num num;
	private BitmapFont font;
	private ShapeRenderer renderer;
	
	public NumDrawer(Num num) {
		this.num = num;
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		renderer = new ShapeRenderer();
		renderer.setColor(0.5f,0.5f,0.5f,1f);
	}
	
	@Override
	public void drawImage(SpriteBatch batch) {		
		batch.end();
		
		renderer.begin(ShapeType.Filled);
		renderer.rect(num.getX(), num.getY(), num.getWidth(), num.getHeight());
		renderer.end();
		
		batch.begin();
		
		font.setScale(3);
		font.draw(batch, num.getOp().toString(),
				num.getWidth() / 2 - font.getSpaceWidth() * num.getOp().toString().length() / 1.5f,
				num.getHeight() / 2 + font.getCapHeight() / 2);
		font.setScale(1);
	}

	@Override
	public void applyTransform(Matrix4 transform) {
		transform.translate(num.getX(), num.getY(), 0);
	}

}
