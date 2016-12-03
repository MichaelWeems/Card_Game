package com.se339.pixel_hockey.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.se339.log.Log;
import com.se339.pixel_hockey.PixelHockeyGame;

/**
 * Created by mweem_000 on 12/2/2016.
 */

public class Element {

    public Body body;
    public Vector2 position;
    //public Rectangle bounds;
    public Vector2 velocity;
    public Vector2 accel;

    protected Log log;

    protected boolean dynamic = false;

    public Element() {
        this.body = null;
        velocity = new Vector2();
        accel = new Vector2();
        log = new Log("Element");
    }

    protected BodyDef initBody(World world, Sprite sprite, float ptm){
        BodyDef bodyDef = new BodyDef();

        if (dynamic)
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        else
            bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(PixelHockeyGame.pWidth / 2,
                PixelHockeyGame.pHeight / 2);

        return bodyDef;
    }

    public boolean applyForce(float xForce, float yForce){
        body.applyForceToCenter(xForce, yForce, true);
        return true;
    }

    public boolean setVelocity(float xVel, float yVel){
        body.setLinearVelocity(xVel, yVel);
        return true;
    }

}
