package com.me.simplebase.catcher.game.base;

import java.util.ArrayList;

public interface VfxSupplier {
	
	/**
	 * Add a Vfx to be later released to a VfxLayer to be handled and drawn.
	 * @param vfx
	 */
	public void store(Vfx vfx);
	
	/**
	 * Release all stored Vfx to a VfxLayer to be updated and drawn 
	 * (ie. live its life-cycle).
	 * @return
	 */
	public ArrayList<Vfx> release();
}
