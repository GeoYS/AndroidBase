package com.me.simplebase.catcher.game.base;

import java.util.ArrayList;

/**
 * A Thing that supplies Vfx's.
 * @author GeoYS_2
 *
 */
public class VfxThing extends Thing implements VfxSupplier{

	private ArrayList<Vfx> effects = new ArrayList<>(),
			effectsCopy = new ArrayList<>();
	
	public VfxThing(float x, float y) {
		super(x, y);
	}

	@Override
	public void store(Vfx vfx) {
		effects.add(vfx);
	}

	@Override
	public ArrayList<Vfx> release() {
		effectsCopy.clear();
		effectsCopy.addAll(effects);
		effects.clear();
		if(!effectsCopy.isEmpty()){
		}
		return effectsCopy;
	}

}
