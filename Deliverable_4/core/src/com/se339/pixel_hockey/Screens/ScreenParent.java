package com.se339.pixel_hockey.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.se339.pixel_hockey.PixelHockeyGame;

/**
 * Created by mweem_000 on 11/25/2016.
 */

public class ScreenParent implements Screen {

    //Reference to our Game, used to set Screens
    protected PixelHockeyGame game;
    protected Stage stage;

    public ScreenParent(PixelHockeyGame game){
        this.stage = new Stage();
        this.game = game;
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
