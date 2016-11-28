package com.se339.pixel_hockey.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.se339.pixel_hockey.Elements.Puck;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.Elements.Powerups.Powerup;
import com.se339.pixel_hockey.Elements.TileObjects.InteractiveTileObject;

/**
 * Created by se339.pixel_hockey on 9/4/15.
 */
public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case PixelHockeyGame.MARIO_HEAD_BIT | PixelHockeyGame.BRICK_BIT:
            case PixelHockeyGame.MARIO_HEAD_BIT | PixelHockeyGame.COIN_BIT:
                if(fixA.getFilterData().categoryBits == PixelHockeyGame.MARIO_HEAD_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit((Puck) fixA.getUserData());
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit((Puck) fixB.getUserData());
                break;
            case PixelHockeyGame.ENEMY_HEAD_BIT | PixelHockeyGame.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == PixelHockeyGame.ENEMY_HEAD_BIT)
                    ((Powerup)fixA.getUserData()).hitOnHead((Puck) fixB.getUserData());
                else
                    ((Powerup)fixB.getUserData()).hitOnHead((Puck) fixA.getUserData());
                break;
            case PixelHockeyGame.ENEMY_BIT | PixelHockeyGame.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == PixelHockeyGame.ENEMY_BIT)
                    ((Powerup)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Powerup)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case PixelHockeyGame.MARIO_BIT | PixelHockeyGame.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == PixelHockeyGame.MARIO_BIT)
                    ((Puck) fixA.getUserData()).hit((Powerup)fixB.getUserData());
                else
                    ((Puck) fixB.getUserData()).hit((Powerup)fixA.getUserData());
                break;
            case PixelHockeyGame.ENEMY_BIT | PixelHockeyGame.ENEMY_BIT:
                ((Powerup)fixA.getUserData()).hitByEnemy((Powerup)fixB.getUserData());
                ((Powerup)fixB.getUserData()).hitByEnemy((Powerup)fixA.getUserData());
                break;
            case PixelHockeyGame.ITEM_BIT | PixelHockeyGame.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == PixelHockeyGame.ITEM_BIT)
                    ((Powerup)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Powerup)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case PixelHockeyGame.ITEM_BIT | PixelHockeyGame.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == PixelHockeyGame.ITEM_BIT)
                    ((Powerup)fixA.getUserData()).use((Puck) fixB.getUserData());
                else
                    ((Powerup)fixB.getUserData()).use((Puck) fixA.getUserData());
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
