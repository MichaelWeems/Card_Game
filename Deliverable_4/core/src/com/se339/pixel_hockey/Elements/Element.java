package com.se339.pixel_hockey.Elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.Screens.ScreenParent;

/**
 * Created by se339.pixel_hockey on 9/24/15.
 */
public abstract class Element extends Sprite {
    protected ScreenParent screen;
    protected Vector2 velocity;
    protected boolean toDestroy;
    protected boolean destroyed;
    protected Body body;

    public Element(ScreenParent screen, float x, float y){
        this.screen = screen;
        toDestroy = false;
        destroyed = false;

        setPosition(x, y);
        setBounds(getX(), getY(), 16 / PixelHockeyGame.PPM, 16 / PixelHockeyGame.PPM);
        defineItem();
    }

    public abstract void defineItem();
    public abstract void use(Puck puck);

    public void update(float dt){
        if(toDestroy && !destroyed){
            screen.getWorld().destroyBody(body);
            destroyed = true;
        }
    }

    public void draw(Batch batch){
        if(!destroyed)
            super.draw(batch);
    }

    public void destroy(){
        toDestroy = true;
    }
    public void reverseVelocity(boolean x, boolean y){
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }
}
