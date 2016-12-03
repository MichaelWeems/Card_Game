package com.se339.pixel_hockey.screens;

/**
 * Created by Zach on 11/30/2016.
 */


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.se339.fileUtilities.FileList;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.fileUtilities.FileList;
import com.se339.log.Log;
import com.se339.ui_elements.Hud;
import com.se339.ui_elements.buttonIcon;

public class MainMenuScreen extends Screens {
    Stage stage;

    public MainMenuScreen(PixelHockeyGame game) {
        super(game);
        Log log = new Log("MainMenu Screen");


        log.l("Creating Stage");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);



//        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
//        style.up = skin.newDrawable("white", Color.LIGHT_GRAY);
//        style.down = skin.newDrawable("white", Color.DARK_GRAY);
////        style.imageOver = new TextureRegionDrawable(friendText);
//        style.imageUp = new TextureRegionDrawable(gameText);
//
//        log.a("Creating image button and action listener");
//        ImageButton friendBtn = new ImageButton(style);
//        friendBtn.addListener(new ChangeListener(){
//            public void changed (ChangeEvent event, Actor actor) {
//                setFriendScreen();
//            }
//        });

        log.l("adding to Tile");
        Table title = new Table();

        stage.addActor(title);
        title.setSize(stage.getWidth(),120);
        title.setPosition(0, 0.8f);
        title.debug();

        log.l("Creating hud");
        Hud hud = new Hud(stage.getWidth(), game);
        stage.addActor(hud);





    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.3f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();


    }
//    public void setFriendScreen(){
//        game.setScreen(new GameScreen(game));
//        dispose();
//    }
//
//    public void setStatScreen(){
//        game.setScreen(new GameScreen(game));
//        dispose();
//    }
//
//    public void findGame(){
//        game.setScreen(new GameScreen(game));
//        dispose();
//    }

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
