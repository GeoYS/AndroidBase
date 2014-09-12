package com.me.simplebase.catcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Textures {

	/**
	 * In game textures.
	 */
	public static Texture COIN, CATCHER, BRICK, BACKGROUND,
	COIN_PARTICLE, DEBRIS_PARTICLE, POOF, MIRROR, SMOKE_PARTICLE;
	
	/**
	 * Menu textures.
	 */
	public static Texture TITLE, TITLE_BACKGROUND, PLAY_UP, PLAY_HOVER, PLAY_DOWN, LEVEL1_UP, LEVEL1_DOWN, LEVEL1_OVER;
	
	static{
		// in game textures
		COIN = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/coin.png")), true);
		System.out.println("Coin texture loaded");
		CATCHER = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/catcher.png")), true);
		System.out.println("Catcher texture loaded");
		BRICK = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/brick.png")), true);
		System.out.println("Brick texture loaded");
		BACKGROUND = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/background.png")), true);
		System.out.println("Background texture loaded");
		COIN_PARTICLE = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/coinparticle.png")), true);
		System.out.println("Coin particle texture loaded");		
		DEBRIS_PARTICLE = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/debrisparticle.png")), true);
		System.out.println("Debris particle texture loaded");	
		POOF = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/poof.png")), true);
		System.out.println("Poof texture loaded");		
		MIRROR = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/mirror.png")), true);
		System.out.println("Mirror texture loaded");		
		SMOKE_PARTICLE = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/smokeparticle.png")), true);
		System.out.println("Smoke particle texture loaded");	
		
		// menu textures
		TITLE = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/title.png")), true);
		System.out.println("Title texture loaded");		
		TITLE_BACKGROUND = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/titlebackground.png")), true);
		System.out.println("Title background texture loaded");	
		PLAY_UP = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/playbuttonup.png")), true);
		System.out.println("Play up texture loaded");	
		PLAY_DOWN = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/playbuttondown.png")), true);
		System.out.println("Play down texture loaded");	
		PLAY_HOVER = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/playbuttonhover.png")), true);
		System.out.println("Play hover texture loaded");	
		LEVEL1_UP = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/level1up.png")), true);
		System.out.println("LEVEL1 up texture loaded");	
		LEVEL1_DOWN = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/level1down.png")), true);
		System.out.println("LEVEL1 down texture loaded");	
		LEVEL1_OVER = new Texture(new Pixmap(Gdx.files.internal("Catcher/data/level1over.png")), true);
		System.out.println("LEVEL1 hover texture loaded");
	}
}
