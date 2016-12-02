package com.se339.pixel_hockey.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.se339.log.Log;
import com.se339.pixel_hockey.PixelHockeyGame;

/**
 * Created by mweem_000 on 12/1/2016.
 */

public class Screens implements Screen {

    PixelHockeyGame game;
    OrthographicCamera camera;

    protected final int sHeight = 1920;
    protected final int sWidth = 1080;

    Log log;

    public Screens(PixelHockeyGame game){
        this.game = game;

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, sHeight, sWidth);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
