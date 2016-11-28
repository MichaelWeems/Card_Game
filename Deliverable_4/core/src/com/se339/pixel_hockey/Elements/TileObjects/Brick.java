package com.se339.pixel_hockey.Elements.TileObjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.se339.pixel_hockey.Elements.Puck;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.Scenes.Hud;
import com.se339.pixel_hockey.Screens.PlayScreen;

/**
 * Created by se339.pixel_hockey on 8/28/15.
 */
public class Brick extends InteractiveTileObject {
    public Brick(PlayScreen screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(PixelHockeyGame.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Puck puck) {
        if(puck.isBig()) {
            setCategoryFilter(PixelHockeyGame.DESTROYED_BIT);
            getCell().setTile(null);
            Hud.addScore(200);
            PixelHockeyGame.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
        }
        PixelHockeyGame.manager.get("audio/sounds/bump.wav", Sound.class).play();
    }

}
