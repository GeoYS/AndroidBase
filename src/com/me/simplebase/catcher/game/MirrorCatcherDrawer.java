package com.me.simplebase.catcher.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;

public class MirrorCatcherDrawer extends CatcherDrawer{
	
	public MirrorCatcherDrawer(Catcher catcher) {
		super(catcher);
	}

	@Override
	public void drawImage(SpriteBatch batch) {
		float mirrorY = catcher.getMirror().getY(), mirrorLength = catcher.getMirror().getLength();
		TextureRegion image = new TextureRegion(this.getImage());
		if(mirrorY > 0 && mirrorY < catcher.getHeight()){
			image.setRegionY(0);
			image.setRegionHeight((int) (catcher.getHeight() - mirrorY));
		}
		else if(mirrorY + mirrorLength < catcher.getHeight()){
			image.setRegionY((int) (catcher.getHeight() - (mirrorY + mirrorLength)));
			image.setRegionHeight((int) (mirrorY + mirrorLength));
		}
		else{
			image.setRegionY(0);
			image.setRegionHeight((int) catcher.getHeight());
		}
		batch.draw(image, 0, 0);
	}

	@Override
	public void applyTransform(Matrix4 transform) {
		transform.translate(catcher.getX(), 0, 0);
		float mirrorY = catcher.getMirror().getY();
		if(mirrorY > 0 && mirrorY < catcher.getHeight()){
			transform.translate(0, mirrorY, 0);
		}
	}

}
