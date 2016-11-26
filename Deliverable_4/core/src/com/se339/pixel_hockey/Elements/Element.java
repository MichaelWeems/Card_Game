package com.se339.pixel_hockey.Elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.Screens.PlayScreen;

/**
 * Created by mweem_000 on 11/23/2016.
 */

public abstract class Element extends Sprite {
    protected PlayScreen screen;
    protected World world;
    protected Vector2 velocity;
    protected boolean toDestroy;
    protected boolean destroyed;
    protected Body body;

    public Element(PlayScreen screen, float x, float y) {
        this.screen = screen;
        //this.world = screen.getWorld();
        setPosition(x,y);
        //setBounds(getX(), getY(), 10 / PixelHockeyGame.PPM, 10 / PixelHockeyGame.PPM);
        defineElement();
        toDestroy = false;
        destroyed = false;
    }

    public abstract void defineElement();
    public abstract void use();

    public void update(float dt){
        if(toDestroy && !destroyed){
            world.destroyBody(body);
            destroyed = true;
        }
    }

    public void draw(Batch batch){
        if (!destroyed){
            super.draw(batch);
        }
    }

    public void destroy() {
        toDestroy = true;
    }
}
