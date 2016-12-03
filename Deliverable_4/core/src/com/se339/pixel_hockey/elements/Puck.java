package com.se339.pixel_hockey.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.se339.fileUtilities.FileList;

/**
 * Created by mweem_000 on 12/2/2016.
 */
public class Puck extends Element{

    CircleShape shape;
    Texture tex;

    String imagefile = FileList.image_puck_blue;

    public Puck(World world, Sprite sprite, float ptm) {
        super();
        dynamic = true;

        this.body = world.createBody(
                initBody(world, sprite, ptm));

        shape = new CircleShape();
        tex = new Texture(imagefile);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public boolean changeColor(String color) {
        if (color.equals("blue"))
            tex = new Texture(FileList.image_puck_blue);
        else if (color.equals("red"))
            tex = new Texture(FileList.image_puck_red);
        else
            return false;
        return true;
    }

    public void setBody(Body body){
        this.body = body;
    }

}
