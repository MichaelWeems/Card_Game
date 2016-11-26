package com.se339.pixel_hockey.Actors;

/**
 * Created by mweem_000 on 11/25/2016.
 */

public class PuckActor extends ActorParent {

    public PuckActor(int startX, int startY, int startWidth, int startHeight, int endX, int speed)
    {
        super(startX, startY, startWidth, startHeight, endX, speed);
    }

    public void hitPuck(){
        // invert y-velocity
    }
}
