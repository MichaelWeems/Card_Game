package com.se339.pixel_hockey.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.se339.fileUtilities.FileList;
import com.se339.log.Log;

/**
 * Created by mweem_000 on 12/2/2016.
 */

public class Wall extends Element {

    CircleShape shape;
    Texture tex;

    String imagefile = FileList.image_puck_blue;

    public Wall(World world, Sprite sprite, float ptm) {
        super();

        log = new Log("Wall");

        this.body = world.createBody(
                initBody(world, sprite, ptm));

        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;
        float w = Gdx.graphics.getWidth()/ptm;
        // Set the height to just 50 pixels above the bottom of the screen so we can see the edge in the
        // debug renderer
        float h = Gdx.graphics.getHeight()/ptm- 50/ptm;

        log.l("Graphics width: " + Gdx.graphics.getWidth() + "\nGraphics Height: " + Gdx.graphics.getHeight());

        //bodyDef2.position.set(0,
//                h-10/PIXELS_TO_METERS);

        bodyDef2.position.set(0,0);
        FixtureDef fixtureDef2 = new FixtureDef();

        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(-w/2,-h/2,w/2,-h/2);
        fixtureDef2.shape = edgeShape;

        body = world.createBody(bodyDef2);
        body.createFixture(fixtureDef2);
        edgeShape.dispose();

        tex = new Texture(imagefile);
    }
}
