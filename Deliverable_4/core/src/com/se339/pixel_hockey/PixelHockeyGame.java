package com.se339.pixel_hockey;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.se339.pixel_hockey.Screens.PlayScreen;

public class PixelHockeyGame extends Game {

    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
    public SpriteBatch batch; // very memory intensive. Only have one at a time

    @Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render(); // delegate render method to the currently active screen (PlayScreen)
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
