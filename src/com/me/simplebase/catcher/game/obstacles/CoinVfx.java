package com.me.simplebase.catcher.game.obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.simplebase.base.util.Positionable;
import com.me.simplebase.catcher.Textures;
import com.me.simplebase.catcher.game.base.Vfx;

public class CoinVfx implements Vfx{

	private Positionable p1, //bottom left
		p2, // bottom right
		p3, // top left
		p4; // top right
	private float vxLeft, vxRight, vyTop, vyBottom;
	
	public CoinVfx(float centreX, float centreY) {
		p1 = new Positionable(centreX, centreY);
		p2 = new Positionable(centreX, centreY);
		p3 = new Positionable(centreX, centreY);
		p4 = new Positionable(centreX, centreY);
		vxLeft = -50 - (float) (Math.random() * 30);
		vxRight = 50 + (float) (Math.random() * 30);
		vyTop = 50 + (float) (Math.random() * 30);
		vyBottom = 0  + (float) (Math.random() * 30);
	}
	
	@Override
	public void update(float delta) {
		updatePos(p1, vxLeft, vyBottom, delta);
		updatePos(p2, vxRight, vyBottom, delta);
		updatePos(p3, vxLeft, vyTop, delta);
		updatePos(p4, vxRight, vyTop, delta);
		
		float gravity = -300f; // px/s/s
		vyBottom += gravity * delta;
		vyTop += gravity * delta;
		
		vxLeft *= 1 - delta / 10;
		vxRight *= 1 - delta / 10;
	}
	
	private void updatePos(Positionable p, float vx, float vy, float delta){
		p.setX(p.getX() + vx * delta);
		p.setY(p.getY() + vy * delta);
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(Textures.COIN_PARTICLE, p1.getX(), p1.getY());
		batch.draw(Textures.COIN_PARTICLE, p2.getX(), p2.getY());
		batch.draw(Textures.COIN_PARTICLE, p3.getX(), p3.getY());
		batch.draw(Textures.COIN_PARTICLE, p4.getX(), p4.getY());
	}

	@Override
	public boolean isFinished() {
		return p3.getY() < -100;
	}

}
