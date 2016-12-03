package com.se339.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
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
        super(world, sprite, ptm);

        shape = new CircleShape();
        tex = new Texture(imagefile);
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
