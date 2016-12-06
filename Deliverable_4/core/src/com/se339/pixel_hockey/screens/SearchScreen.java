package com.se339.pixel_hockey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.se339.Client.webSocket;
import com.se339.fileUtilities.UserReader;
import com.se339.log.Log;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.ui_elements.Hud;

/**
 * Created by Zach on 12/5/2016.
 */

public class SearchScreen extends Screens {
    Stage stage;
    Log log = new Log("Search Screen");
    webSocket wb;
    public SearchScreen(PixelHockeyGame game, webSocket wb) {
        super(game);
        this.wb = wb;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        log.l("adding to Tile");
        Table table = new Table();
        Label titleLabel = new Label("Searching for\nGame", skin);
        titleLabel.setFontScale(6,6);
        table.add(titleLabel);
        stage.addActor(table);
        table.setSize(260, 195);
        table.setPosition(400, 1350);

        log.l("Creating hud");
        Hud hud = new Hud(stage.getWidth(), game, wb, true);
        stage.addActor(hud);

    }

    public void searchGame(){
        log.l("Searching for game");
        wb.joinGame();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.3f, 0.5f, 1);
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
