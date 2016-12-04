package com.se339.pixel_hockey.screens;

/**
 * Created by Zach on 11/30/2016.
 */


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
import com.se339.fileUtilities.UserReader;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.log.Log;
import com.se339.ui_elements.Hud;

public class MainMenuScreen extends Screens {
    Stage stage;
    Log log = new Log("MainMenu Screen");
    public MainMenuScreen(PixelHockeyGame game) {
        super(game);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        log.l("adding to Tile");
        Table table = new Table();
        Label titleLabel = new Label("Welcome To\nPixel Hockey", skin);
        titleLabel.setFontScale(6,6);
        table.add(titleLabel);
        stage.addActor(table);
        table.setSize(260, 195);
        table.setPosition(400, 1500);

        Table NameTable = new Table();
        final UserReader uReader = new UserReader();
        String name = uReader.readName();
        final Label nameLabel = new Label(name, skin);
        NameTable.add(nameLabel);
        nameLabel.setFontScale(4,4);
        NameTable.row();
        NameTable.row();
        NameTable.row();

        final TextField nameTxt = new TextField("", skin);
        TextField.TextFieldStyle style = nameTxt.getStyle();
        NameTable.add(nameTxt);
        NameTable.row();
        NameTable.row();
        stage.addActor(NameTable);
        NameTable.setSize(260, 195);
        NameTable.setPosition(400, 1000);



        // table.align(Align.right | Align.bottom);

//        table.debug();

        TextureRegion upRegion = skin.getRegion("default-slider-knob");
        TextureRegion downRegion = skin.getRegion("default-slider-knob");
        BitmapFont buttonFont = skin.getFont("default-font");

        TextButton button = new TextButton("Change Name", skin);
        button.getLabel().setFontScale(4,4);
        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                log.a("Change name button clicked");
                String name = nameTxt.getText();
                if(name.length()> 0){
                    uReader.writeName(name);
                    nameLabel.setText(name);
                    nameTxt.setText("");
                }
                return true;
            }
        });
        NameTable.add(button);


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
