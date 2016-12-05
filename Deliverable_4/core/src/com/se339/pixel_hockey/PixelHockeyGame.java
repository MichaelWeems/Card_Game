package com.se339.pixel_hockey;

/**
 * Created by Zach on 11/30/2016.
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.se339.pixel_hockey.screens.MainMenuScreen;

public class PixelHockeyGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    private static int pHeight = 0;
    private static int pWidth = 0;

    public void create() {
        batch = new SpriteBatch();
        // Use LibGDX's default Arial font.
        font = new BitmapFont();

        pHeight = Gdx.graphics.getHeight();
        pWidth = Gdx.graphics.getWidth();

        this.setScreen(new MainMenuScreen(this));
    }

    public static int getWidth(){
        return pWidth;
    }

    public static int getHeight(){
        return pHeight;
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        font.dispose();
    }
}