package com.se339.pixel_hockey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.se339.Client.SearchListener;
import com.se339.Client.WebSocket;
import com.se339.log.Log;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.ui_elements.Hud;

import java.io.IOException;

/**
 * Created by Zach on 12/5/2016.
 */

public class SearchScreen extends MenuScreen {

    public SearchScreen(PixelHockeyGame game) {
        super(game);

        log = new Log("SearchScreen");

        log.l("adding to Tile");
        Table table = new Table();
        Label titleLabel = new Label("Searching for\nGame", skin);
        titleLabel.setFontScale(6,6);
        table.add(titleLabel);
        stage.addActor(table);
        table.setSize(260, 195);
        table.setPosition(400, 1350);


        createHUD();
        setRenderColor(0.1f, 0.3f, 0.5f, 1);
    }

    public void searchGame(){
        log.l("Searching for game");
        game.getSocket().joinGame();
        try{
            game.getSocket().read();
        }catch(IOException e){
            System.out.println("Could not recieve message from search");
        }


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        searchGame();
    }

}
