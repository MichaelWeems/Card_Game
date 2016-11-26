package com.se339.pixel_hockey.Elements;

import com.badlogic.gdx.math.Vector2;
import com.se339.pixel_hockey.Screens.PlayScreen;

/**
 * Created by mweem_000 on 11/23/2016.
 */

public class Puck extends Element {


    public Puck(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        //setRegion(screen.getAtlas().findRegion("puck"), 0,0, 10, 10);
        velocity = new Vector2(0, 0);
    }

    @Override
    public void defineElement() {

    }

    @Override
    public void use() {

    }

    @Override
    public void update(float dt) {
        super.update(dt);
        //setPosition(body.getPosition().x - getWidth() / 2, body.getPosition);
    }
}
