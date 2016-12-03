package com.se339.elements.powerups;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.se339.elements.Element;

/**
 * Created by mweem_000 on 12/2/2016.
 */

public class Powerup extends Element {

    public Powerup(World world, Sprite sprite, float ptm) {
        super(world, sprite, ptm);
    }
}
