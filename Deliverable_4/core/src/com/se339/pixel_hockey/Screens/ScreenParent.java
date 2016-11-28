package com.se339.pixel_hockey.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.Stages.StageParent;

/**
 * Created by mweem_000 on 11/25/2016.
 */

public class ScreenParent implements Screen {

    //Reference to our Game, used to set Screens
    protected PixelHockeyGame game;
    protected StageParent stage;
    protected World world;

    public ScreenParent(PixelHockeyGame game){
        this.stage = new StageParent();
        this.game = game;
    }

    public World getWorld(){
        return world;
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
