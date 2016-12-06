package com.se339.pixel_hockey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.se339.Client.WebSocket;
import com.se339.fileUtilities.UserReader;
import com.se339.log.Log;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.ui_elements.Hud;


/**
 * Created by Zach on 12/3/2016.
 */

public class StatScreen extends Screens{
    Stage stage;

    public StatScreen(PixelHockeyGame game) {
        super(game);

        Log log = new Log("Stat Screen");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        log.l("adding to Tile");
        Table title = new Table();
        Label titleLabel = new Label("Stats List", skin);
        titleLabel.setFontScale(4,4);
        title.add(titleLabel);
        stage.addActor(title);
        title.setSize(260, 195);
        title.setPosition(400, 1700);
//        title.debug();

        Table stats = new Table();
        UserReader ur = new UserReader();

        Label winsLabel = new Label("Wins: "+ur.getWins(), skin);
        winsLabel.setFontScale(4,4);
        stats.add(winsLabel);
        stats.row();
        Label lossesLabel = new Label("Losses: "+ur.getLosses(), skin);
        lossesLabel.setFontScale(4,4);
        stats.add(lossesLabel);
        stats.row();
        Label gamesLabel = new Label("Total Games Played: "+ur.getTotalGames(), skin);
        gamesLabel.setFontScale(4,4);
        stats.add(gamesLabel);
        stats.row();

        stats.setPosition(550,1000);
        stage.addActor(stats);




        log.l("Creating hud");
        Hud hud = new Hud(stage.getWidth(), game, false);
        stage.addActor(hud);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.5f, 0.2f, 1);
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
