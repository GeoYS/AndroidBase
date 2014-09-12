package com.me.simplebase.base.util;

public class Geom {
	
	/**
	 * Returns whether or not point (pX, pY) is contained in the rectangle given.
	 * It is inclusive of the edges.
	 * (recX, recY) reps the bottom left corner.
	 * @param recX
	 * @param recY
	 * @param recW
	 * @param recH
	 * @param pX
	 * @param pY
	 * @return
	 */
	public static boolean recContains(float recX, float recY, float recW, float recH, float pX, float pY){
		return recX <= pX && pX <= recX + recW &&
				recY <= pY && pY <= recY + recH;
	}
}
