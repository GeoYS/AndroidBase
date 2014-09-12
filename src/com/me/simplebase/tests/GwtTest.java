package com.me.simplebase.tests;

import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

public class GwtTest extends ApplicationAdapter {
	ShaderProgram shader;
	Mesh mesh;
	Matrix4 matrix = new Matrix4();
	SpriteBatch batch;
	Texture texture;
	List<Vector2> positions;
	Sprite sprite;
	BitmapFont font;
	BitmapFontCache cache;
	TextureAtlas atlas;
	int numSprites;

	@Override
	public void create () {
		batch = new SpriteBatch();
		texture = new Texture(new Pixmap(Gdx.files.internal("data/libgdx.png")), true);
		texture.setFilter(TextureFilter.MipMap, TextureFilter.Linear);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(texture, 0, 0);
		batch.end();
	}

	@Override
	public void resume () {
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void pause () {
	}

	@Override
	public void dispose () {
	}
}