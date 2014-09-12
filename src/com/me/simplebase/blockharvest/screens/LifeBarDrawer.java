package com.me.simplebase.blockharvest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.me.simplebase.base.g2d.BatchDrawer;

public class LifeBarDrawer extends BatchDrawer{

	private LifeBar bar;
	private ShapeRenderer renderer;
	
	public LifeBarDrawer(LifeBar bar){
		this.bar = bar;
		renderer = new ShapeRenderer();
	}
	
	@Override
	public void drawImage(SpriteBatch batch) {
		batch.end();
		
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		
		renderer.setColor(new Color(0,0, bar.getPercentage(),0));
		renderer.begin(ShapeType.Filled);
		renderer.rect(0, 0, Gdx.graphics.getWidth() * bar.getPercentage() * 4 / 5, Gdx.graphics.getHeight() / 25);
		renderer.end();		
		
		batch.begin();
	}

	@Override
	public void applyTransform(Matrix4 transform) {
		transform.translate(bar.getX(), bar.getY(), 0);
	}

}
