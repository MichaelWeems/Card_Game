package com.se339.pixel_hockey.Elements;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.se339.pixel_hockey.Actors.PuckActor;
import com.se339.pixel_hockey.Elements.Powerups.*;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.Screens.PlayScreen;

/**
 * Created by se339.pixel_hockey on 8/27/15.
 */
public class Puck extends Sprite {

    public World world;
    public Body b2body;

    private TextureRegion marioStand;
    private boolean marioIsDead;
    private PlayScreen screen;
    private PuckActor puckActor;

    private boolean timeToRedefinePuck;

    public Puck(PlayScreen screen){
        //initialize default values
        this.screen = screen;
        this.world = screen.getWorld();


        Array<TextureRegion> frames = new Array<TextureRegion>();


        //create texture region for mario standing
        marioStand = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 0, 0, 16, 16);

        //define mario in Box2d
        definePuck();

        //set initial values for marios location, width and height. And initial frame as marioStand.
        setBounds(0, 0, 16 / PixelHockeyGame.PPM, 16 / PixelHockeyGame.PPM);
        setRegion(marioStand);

    }

    public void update(float dt){

        // time is up : too late mario dies T_T
        // the !isDead() method is used to prevent multiple invocation
        // of "die music" and jumping
        // there is probably better ways to do that but it works for now.
        if (screen.getHud().isTimeUp() && !isDead()) {
            die();
        }

        //update our sprite to correspond with the position of our Box2D body
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        //update sprite with the correct frame depending on marios current action
        setRegion(getFrame(dt));
        if(timeToRedefinePuck)
            redefinePuck();

    }

    public TextureRegion getFrame(float dt){
        TextureRegion region = marioStand;
        return region;
    }

    public void die() {

        if (!isDead()) {

            PixelHockeyGame.manager.get("audio/music/mario_music.ogg", Music.class).stop();
            PixelHockeyGame.manager.get("audio/sounds/mariodie.wav", Sound.class).play();
            marioIsDead = true;
            Filter filter = new Filter();
            filter.maskBits = PixelHockeyGame.NOTHING_BIT;

            for (Fixture fixture : b2body.getFixtureList()) {
                fixture.setFilterData(filter);
            }

            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
        }
    }

    public boolean isDead(){
        return marioIsDead;
    }

    public void hit(){
        b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
    }

    public void hit(Powerup powerup){
        if(powerup instanceof Speeddown && ((Speeddown) powerup).currentState == Speeddown.State.STANDING_SHELL)
            ((Speeddown) powerup).kick(powerup.b2body.getPosition().x > b2body.getPosition().x ? Speeddown.KICK_RIGHT : Speeddown.KICK_LEFT);
        else {
            if (marioIsBig) {
                marioIsBig = false;
                timeToRedefinePuck = true;
                setBounds(getX(), getY(), getWidth(), getHeight() / 2);
                PixelHockeyGame.manager.get("audio/sounds/powerdown.wav", Sound.class).play();
            } else {
                die();
            }
        }
    }

    public void redefinePuck(){
        Vector2 position = b2body.getPosition();
        world.destroyBody(b2body);

        BodyDef bdef = new BodyDef();
        bdef.position.set(position);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / PixelHockeyGame.PPM);
        fdef.filter.categoryBits = PixelHockeyGame.MARIO_BIT;
        fdef.filter.maskBits = PixelHockeyGame.GROUND_BIT |
                PixelHockeyGame.COIN_BIT |
                PixelHockeyGame.BRICK_BIT |
                PixelHockeyGame.ENEMY_BIT |
                PixelHockeyGame.OBJECT_BIT |
                PixelHockeyGame.ENEMY_HEAD_BIT |
                PixelHockeyGame.ITEM_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / PixelHockeyGame.PPM, 6 / PixelHockeyGame.PPM), new Vector2(2 / PixelHockeyGame.PPM, 6 / PixelHockeyGame.PPM));
        fdef.filter.categoryBits = PixelHockeyGame.MARIO_HEAD_BIT;
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData(this);

        timeToRedefinePuck = false;

    }

    public void definePuck(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / PixelHockeyGame.PPM, 32 / PixelHockeyGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / PixelHockeyGame.PPM);
        fdef.filter.categoryBits = PixelHockeyGame.MARIO_BIT;
        fdef.filter.maskBits = PixelHockeyGame.GROUND_BIT |
                PixelHockeyGame.COIN_BIT |
                PixelHockeyGame.BRICK_BIT |
                PixelHockeyGame.ENEMY_BIT |
                PixelHockeyGame.OBJECT_BIT |
                PixelHockeyGame.ENEMY_HEAD_BIT |
                PixelHockeyGame.ITEM_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / PixelHockeyGame.PPM, 6 / PixelHockeyGame.PPM), new Vector2(2 / PixelHockeyGame.PPM, 6 / PixelHockeyGame.PPM));
        fdef.filter.categoryBits = PixelHockeyGame.MARIO_HEAD_BIT;
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData(this);
    }

    public void draw(Batch batch){
        super.draw(batch);
    }
}
