package com.me.simplebase.numberz.game.catcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class AutoCatcherDrawer extends CatcherDrawer{

	private AutoCatcher catcher;
	
	public AutoCatcherDrawer(AutoCatcher catcher) {
		super(catcher);
		this.catcher = catcher;
	}
	
	@Override
	protected void drawShape(SpriteBatch batch) {
		Color color = Color.ORANGE.cpy();		
		color.a = catcher.getPercent();
		
		getRenderer().setColor(color);
		getRenderer().begin(ShapeType.Filled);
	
		Gdx.gl.glEnable(GL20.GL_BLEND);
		
		getRenderer().setTransformMatrix(batch.getTransformMatrix());
		getRenderer().rect(0, 0, Catcher.WIDTH, Catcher.HEIGHT);
		getRenderer().end();
	}

}
