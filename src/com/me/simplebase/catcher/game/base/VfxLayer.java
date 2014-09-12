package com.me.simplebase.catcher.game.base;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VfxLayer {
	
	private ArrayList<Vfx> effects; 
	
	public VfxLayer() {
		effects = new ArrayList<>();
	}
	
	public void addVfx(Vfx vfx){
		effects.add(vfx);
	}
	
	public void addVfx(Collection<Vfx> effects){
		this.effects.addAll(effects);
	}
	
	public void update(float delta){
		for(Vfx vfx : effects){
			vfx.update(delta);
		}
		for(int i = effects.size() - 1; i >= 0; i --){
			if(effects.get(i).isFinished()){
				effects.remove(i);
			}
		}
	}
	
	public void draw(SpriteBatch batch){
		for(Vfx vfx : effects){
			vfx.draw(batch);
		}
	}
}
