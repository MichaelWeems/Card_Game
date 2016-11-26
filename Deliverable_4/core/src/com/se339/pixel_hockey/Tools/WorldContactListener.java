package com.se339.pixel_hockey.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.Sprites.Enemies.Enemy;
import com.se339.pixel_hockey.Sprites.Items.Item;
import com.se339.pixel_hockey.Sprites.Mario;
import com.se339.pixel_hockey.Sprites.TileObjects.InteractiveTileObject;

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
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit((Mario) fixA.getUserData());
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit((Mario) fixB.getUserData());
                break;
            case PixelHockeyGame.ENEMY_HEAD_BIT | PixelHockeyGame.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == PixelHockeyGame.ENEMY_HEAD_BIT)
                    ((Enemy)fixA.getUserData()).hitOnHead((Mario) fixB.getUserData());
                else
                    ((Enemy)fixB.getUserData()).hitOnHead((Mario) fixA.getUserData());
                break;
            case PixelHockeyGame.ENEMY_BIT | PixelHockeyGame.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == PixelHockeyGame.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case PixelHockeyGame.MARIO_BIT | PixelHockeyGame.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == PixelHockeyGame.MARIO_BIT)
                    ((Mario) fixA.getUserData()).hit((Enemy)fixB.getUserData());
                else
                    ((Mario) fixB.getUserData()).hit((Enemy)fixA.getUserData());
                break;
            case PixelHockeyGame.ENEMY_BIT | PixelHockeyGame.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).hitByEnemy((Enemy)fixB.getUserData());
                ((Enemy)fixB.getUserData()).hitByEnemy((Enemy)fixA.getUserData());
                break;
            case PixelHockeyGame.ITEM_BIT | PixelHockeyGame.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == PixelHockeyGame.ITEM_BIT)
                    ((Item)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Item)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case PixelHockeyGame.ITEM_BIT | PixelHockeyGame.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == PixelHockeyGame.ITEM_BIT)
                    ((Item)fixA.getUserData()).use((Mario) fixB.getUserData());
                else
                    ((Item)fixB.getUserData()).use((Mario) fixA.getUserData());
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
