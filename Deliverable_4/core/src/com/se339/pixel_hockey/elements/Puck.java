package com.se339.pixel_hockey.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.se339.fileUtilities.FileList;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.physics.GameWorld;

/**
 * Created by mweem_000 on 12/2/2016.
 */
public class Puck extends Element{

    CircleShape shape;
    String imagefile = FileList.image_puck_blue;

    public Puck(World world) {
        super();
        dynamic = true;
        drawSprite = true;

        this.body = world.createBody(
                initBody(world));

        shape = new CircleShape();
        tex = new Texture(imagefile);
        sprite = new Sprite(tex);
        sprite.setPosition(PixelHockeyGame.getWidth() / 2, PixelHockeyGame.getHeight() / 2);

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


    public void setPosition(){

        sprite.setPosition((body.getPosition().x) - sprite.getWidth()/2,
                (body.getPosition().y) - sprite.getHeight()/2 );

//        log.v(body.getPosition().x, "Body position x");
//        log.v(body.getPosition().y, "Body position y");
//        log.v(sprite.getX(), "Sprite position x");
//        log.v(sprite.getY(), "Sprite position y");
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x,y);
    }

    public void drawPuck(SpriteBatch batch){

        if(drawSprite)
            batch.draw(sprite, sprite.getX(), sprite.getY(),sprite.getOriginX(),
                    sprite.getOriginY(),
                    sprite.getWidth(),sprite.getHeight(),sprite.getScaleX(),sprite.
                            getScaleY(),sprite.getRotation());

        font.draw(batch,
                "Restitution: " + body.getFixtureList().first().getRestitution(),
                -Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()/2 );
    }

}
