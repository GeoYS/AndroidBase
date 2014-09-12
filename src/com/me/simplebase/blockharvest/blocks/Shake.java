package com.me.simplebase.blockharvest.blocks;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Shake {
	public static void rotate(Block b, Matrix4 transform){
		if(b.accuracy() >= 90){
			transform.translate(b.getWidth() / 2, b.getHeight() / 2, 0);
			transform.rotate(Vector3.Z, 100 - b.accuracy() - 5);
			transform.translate(-b.getWidth() / 2, -b.getHeight() / 2, 0);
		}
	}
}
