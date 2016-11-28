package com.se339.pixel_hockey.Stages;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.se339.pixel_hockey.Actors.PuckActor;
import com.sun.org.apache.bcel.internal.Constants;

/**
 * Created by mweem_000 on 11/25/2016.
 */

public class MenuStage extends StageParent implements ContactListener {

    private PuckActor puckActor; //Custom actor object.
    public MenuStage()
    {
        //super(new ScalingViewport(Scaling.fill, Constants.APP_WIDTH, Constants.APP_HEIGHT, new OrthographicCamera(Constants.APP_WIDTH, Constants.APP_HEIGHT)));
        super();
        //setUpWorld(); //Code to setup the world. added background and other actors. none of them are touchable.
        addActor();
    }

    private void addActor()
    {
        puckActor = new PuckActor(100, 100, 100, 100, 1000, 0);
        puckActor.setTouchable(Touchable.enabled);
        puckActor.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button)
            {
                actorTouched(x,y,pointer,button);
                return true;
            }
        });
        addActor(puckActor);
    }

    private void actorTouched(float x, float y, int pointer, int button){
        puckActor.hitPuck();
    }

    @Override
    public void beginContact(Contact contact) {

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
