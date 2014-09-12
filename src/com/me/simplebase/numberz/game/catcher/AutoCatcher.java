package com.me.simplebase.numberz.game.catcher;

import com.me.simplebase.base.util.Stopwatch;

public class AutoCatcher extends Catcher{
	
	private final float SUBMIT_TIME = 10_000; // milliseconds
	
	private Stopwatch collectTimer;
	
	public AutoCatcher() {
		super();
		collectTimer = new Stopwatch();
		collectTimer.start();
		drawer = new AutoCatcherDrawer(this);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		if(getPercent() >= 1){
			this.flyOff(0 /* value of no importance*/);
		}
	}
	
	@Override
	protected void reset() {
		super.reset();
		collectTimer.restart();
	}
	
	/**
	 * Percent of time elapsed until next submit.
	 * @return
	 */
	public float getPercent(){
		float percent = collectTimer.time() / SUBMIT_TIME;
		percent = percent > 1 ? 1 : percent;
		return percent;
	}
}
