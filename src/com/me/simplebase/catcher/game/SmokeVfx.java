package com.me.simplebase.catcher.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.me.simplebase.catcher.game.base.Vfx;

/**
 * Background chimney smoke.
 * @author GeoYS_2
 *
 */
public class SmokeVfx implements Vfx{

	ParticleEffectPool bombEffectPool;
	Array<PooledEffect> effects = new Array();
	
	public SmokeVfx() {
		ParticleEffect bombEffect = new ParticleEffect();
		bombEffect.load(Gdx.files.internal("Catcher/data/smoke.p"), Gdx.files.internal("Catcher/data"));
		bombEffectPool = new ParticleEffectPool(bombEffect, 1, 2);
		

		PooledEffect effect = bombEffectPool.obtain();
		effect.setPosition(265, 320);
		effects.add(effect);
	}
	
	@Override
	public void update(float delta) {
		for (int i = effects.size - 1; i >= 0; i--) {
		    PooledEffect effect = effects.get(i);
		    effect.update(delta);
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		for (int i = effects.size - 1; i >= 0; i--) {
		    PooledEffect effect = effects.get(i);
		    effect.draw(batch);
		    if (effect.isComplete()) {
		        effect.free();
		        effects.removeIndex(i);
		    }
		}
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
