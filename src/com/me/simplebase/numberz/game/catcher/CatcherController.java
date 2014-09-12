package com.me.simplebase.numberz.game.catcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class CatcherController extends InputAdapter{
	
	private Catcher catcher;
	private int lastDragX, lastDragY,
			last2X, last2Y;
	
	public CatcherController(Catcher catcher){
		this.catcher = catcher;
		resetDrag();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		catcher.setClosed(true);
		return true; // consumed
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(lastDragX == -1 && lastDragY == -1){
			setLastDrag(screenX, screenY);
		}
		else{
			float x = catcher.getX(), y = catcher.getY(),
					dx = screenX - lastDragX, dy = screenY - lastDragY;
			catcher.setX(x + dx);
			catcher.setY(y - dy);
			setLastDrag(screenX, screenY);
		}
		return true; // consumed
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		float flyOffThreshold = 650; // px / s (same as flyOffSpeed at the moment)
		float dy = (last2Y - screenY) / Gdx.graphics.getDeltaTime(); // px / s
		if(dy > flyOffThreshold){
			catcher.flyOff(dy);
		}
		else{
			catcher.setClosed(false);
		}
		resetDrag();
		return true; // consumed
	}
	
	private void setLastDrag(int lastX, int lastY){
		last2X = lastDragX;
		last2Y = lastDragY;
		lastDragY = lastY;
		lastDragX = lastX;
	}
	
	private void resetDrag(){
		lastDragX = -1;
		lastDragY = -1;
	}
}
