package com.se339.pixel_hockey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.se339.log.Log;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.ui_elements.CSVReader;
import com.se339.ui_elements.Hud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.badlogic.gdx.graphics.g2d.ParticleEmitter.SpawnShape.line;
import static com.se339.fileUtilities.FileList.test_csv;

/**
 * Created by Zach on 12/3/2016.
 */

public class FriendScreen extends Screens {
    Stage stage;

    public FriendScreen(PixelHockeyGame game) {

        super(game);
        Log log = new Log("Friend Screen");


        log.l("Creating Stage");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        log.l("Constructing csv reader");
        CSVReader csvr = new CSVReader(test_csv);
        for(String[] a : csvr.getFriends()){
            System.out.print("Friend: ");
            for(String b : a){
                System.out.print(b+", ");
            }
            System.out.println();
        }


        log.l("adding to Tile");
        Table title = new Table();

        stage.addActor(title);
        title.setSize(stage.getWidth(), 120);
        title.setPosition(0, 0.8f);
        title.debug();

        log.l("Creating hud");
        Hud hud = new Hud(stage.getWidth(), game);
        stage.addActor(hud);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.3f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
//        skin.dispose();
    }


}
