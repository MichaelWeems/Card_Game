package com.se339.pixel_hockey.Elements.TileObjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import com.se339.pixel_hockey.Elements.Powerups.Speedup;
import com.se339.pixel_hockey.Elements.Puck;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.Scenes.Hud;
import com.se339.pixel_hockey.Screens.PlayScreen;
import com.se339.pixel_hockey.Elements.Powerups.PowerupDef;

/**
 * Created by se339.pixel_hockey on 8/28/15.
 */
public class Coin extends InteractiveTileObject {
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;

    public Coin(PlayScreen screen, MapObject object){
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(PixelHockeyGame.COIN_BIT);
    }

    @Override
    public void onHeadHit(Puck puck) {
        if(getCell().getTile().getId() == BLANK_COIN)
            PixelHockeyGame.manager.get("audio/sounds/bump.wav", Sound.class).play();
        else {
            if(object.getProperties().containsKey("mushroom")) {
                screen.spawnItem(new PowerupDef(new Vector2(body.getPosition().x, body.getPosition().y + 16 / PixelHockeyGame.PPM),
                        Speedup.class));
                PixelHockeyGame.manager.get("audio/sounds/powerup_spawn.wav", Sound.class).play();
            }
            else
                PixelHockeyGame.manager.get("audio/sounds/coin.wav", Sound.class).play();
            getCell().setTile(tileSet.getTile(BLANK_COIN));
            Hud.addScore(100);
        }
    }
}
