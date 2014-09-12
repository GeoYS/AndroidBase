package com.me.simplebase.numberz.game.num;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.GeometryUtils;
import com.badlogic.gdx.math.MathUtils;
import com.me.simplebase.base.util.Geom;

public class NumController extends InputAdapter{
	
	private Num num;
	
	public NumController(Num num) {
		this.num = num;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(Geom.recContains(num.getX(), num.getY(),
				num.getWidth(), num.getHeight(),
				screenX,  Gdx.graphics.getHeight() - screenY)){
			num.tap();
			return true;
		}
		return false;
	}
}
