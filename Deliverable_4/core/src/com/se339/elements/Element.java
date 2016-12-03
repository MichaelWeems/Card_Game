package com.se339.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by mweem_000 on 12/2/2016.
 */

public class Element {

    public Body body;
    public Vector2 position;
    //public Rectangle bounds;
    public Vector2 velocity;
    public Vector2 accel;

    public Element(World world, Sprite sprite, float ptm) {

        this.body = world.createBody(
                initBody(world, sprite, ptm));

        velocity = new Vector2();
        accel = new Vector2();
    }

    private BodyDef initBody(World world, Sprite sprite, float ptm){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((sprite.getX() + sprite.getWidth()/2) /
                        ptm,
                (sprite.getY() + sprite.getHeight()/2) / ptm);

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
