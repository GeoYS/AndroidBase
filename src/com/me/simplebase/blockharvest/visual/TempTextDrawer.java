package com.me.simplebase.blockharvest.visual;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.me.simplebase.base.g2d.BatchDrawer;

public class TempTextDrawer extends BatchDrawer{	
	
	private long time, startTime;
	private String text;
	private float x, y, scale;
	private BitmapFont font;
	
	public TempTextDrawer(String text, float x, float y, float scale, long time){
		this.time = time;
		this.startTime = System.currentTimeMillis();
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.text = text;
		font = new BitmapFont();
	}

	@Override
	public void drawImage(SpriteBatch batch) {
		font.setColor(Color.GREEN);
		font.setScale(scale);
		font.draw(batch, text, 0, 0);
	}

	@Override
	public void applyTransform(Matrix4 transform) {
		transform.translate(x, y, 0);
	}
	
	public boolean isFinished(){
		return System.currentTimeMillis() - startTime > time;
	}
}
