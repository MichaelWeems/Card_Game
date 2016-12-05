package com.se339.pixel_hockey.world;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.screens.GameScreen;

/**
 * Created by mweem_000 on 12/4/2016.
 */

public class WorldCreator {

    public WorldCreator(GameScreen screen){
        World world = screen.getWorld();

        // make puck
        //makeBody(world, PixelHockeyGame.getWidth() / 2,
        //        PixelHockeyGame.getHeight() / 2, 256 / 2);

        // make hockey stick
        //makeBody(world, PixelHockeyGame.getWidth() / 2,
        //        PixelHockeyGame.getHeight() / 4, 256 / 2);


    }

    private void makeBody(World world, int x, int y, int radius){
        //create body and fixture variables
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        Circle circ = new Circle();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(x, y);

        body = world.createBody(bdef);

        shape.setAsBox(circ.radius, circ.radius);
        fdef.shape = shape;
        body.createFixture(fdef);
    }
}
