package com.me.simplebase.blockharvest.blocks;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.me.simplebase.base.g2d.BatchDrawer;
import com.me.simplebase.blockharvest.screens.GameScreen;

public class BlockDrawer extends BatchDrawer{

	private ShapeRenderer renderer;
	private Block block;
	
	public BlockDrawer(Block block){
		renderer = new ShapeRenderer();
		this.block = block;
	}
	
	@Override
	public void drawImage(SpriteBatch batch) {
		batch.end();
					  // on the beat flicker
		Color color = !block.hasStarted() ?
				new Color(GameScreen.lifeBar.getPercentage(),
						GameScreen.lifeBar.getPercentage(),
						GameScreen.lifeBar.getPercentage(),
						0)://!block.hasStarted() && System.currentTimeMillis() % (int)(block.getTimeToReach() * 1000 / 4) < 16 /*60 fps */? Color.CYAN : //randomColor() :
				new Color(block.getPercentage() >= 0.5f ? (block.getPercentage() - 0.5f) * 2 : 0,
				block.getPercentage() < 0.5f ? block.getPercentage() * 2 : (1 - block.getPercentage()) * 2,
				block.getPercentage() < 0.5 ? 0.1f * block.getPercentage() : 0,
				1);
		
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		
		renderer.setColor(color);
		renderer.begin(ShapeType.Filled);
		renderer.rect(0, 0, block.getWidth(), block.getHeight());
		renderer.end();		

		batch.begin();
	}
	
	public Color randomColor(){
		if(Math.random() < 0.1){
			return Color.RED;
		}
		if(Math.random() < 0.1){
			return Color.BLUE;
		}
		if(Math.random() < 0.1){
			return Color.PINK;
		}
		if(Math.random() < 0.1){
			return Color.WHITE;
		}
		if(Math.random() < 0.1){
			return Color.CYAN;
		}
		return Color.BLACK;
	}

	@Override
	public void applyTransform(Matrix4 transform) {
		transform.translate(block.getX(), block.getY(), 0);
		Shake.rotate(block, transform);
	}

}
