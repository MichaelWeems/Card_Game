package com.se339.ui_elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.se339.fileUtilities.FileList;
import com.se339.log.Log;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.screens.FriendScreen;
import com.se339.pixel_hockey.screens.GameScreen;
import com.se339.pixel_hockey.screens.MainMenuScreen;
import com.se339.pixel_hockey.screens.StatScreen;

/**
 * Created by Zach on 12/3/2016.
 */

public class Hud extends Table {
    Log log;
    Table hud;
    PixelHockeyGame game;
    public Hud(float width, PixelHockeyGame game){
        this.game = game;
        log = new Log("Hud Class");

        log.a("Initializing buttonIcons");
        buttonIcon friendIcon = new buttonIcon(FileList.image_friends_icon);
        buttonIcon statIcon = new buttonIcon(FileList.image_stat_icon);
        buttonIcon gameIcon = new buttonIcon(FileList.image_game_icon);
        buttonIcon homeIcon = new buttonIcon(FileList.image_home_icon);

        log.a("adding to hud");
//        hud = new Table();
        ImageButton homeBtn = homeIcon.getBtn();
        homeBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setHomePage();
            }
        });

        ImageButton friendBtn = friendIcon.getBtn();
        friendBtn.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
                setFriendScreen();
            }
        });

        ImageButton gameBtn = gameIcon.getBtn();
        gameBtn.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
                findGame();
            }
        });

        ImageButton statBtn = statIcon.getBtn();
        statBtn.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
                setStatScreen();
            }
        });

        this.add(homeBtn);
        this.add(friendBtn);
        this.add(gameBtn);
        this.add(statBtn);
//        stage.addActor(hud);
        this.setSize(width,120);
        this.setPosition(0, 0);
        this.setColor(Color.LIGHT_GRAY);
    }

//    public Table getTable(){
//        return hud;
//    }

    public void setHomePage(){
        log.a("Relocating to home page");
        game.setScreen(new MainMenuScreen(game));
//        game.dispose();
    }

    public void setFriendScreen(){
        log.a("Relocating to friends page");
        game.setScreen(new FriendScreen(game));
//        game.dispose();
    }

    public void setStatScreen(){
        log.a("Relocating to stat screen");
        game.setScreen(new StatScreen(game));
//        game.dispose();
    }

    public void findGame(){
        log.a("Finding Game");
        game.setScreen(new GameScreen(game));
        game.dispose();
    }



}
