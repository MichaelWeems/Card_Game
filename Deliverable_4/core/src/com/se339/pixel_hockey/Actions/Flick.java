package com.se339.pixel_hockey.Actions;

/**
 * Created by mweem_000 on 11/25/2016.
 */

public class Flick extends Action {

    private float velocityX;
    private float velocityY;

    public Flick(float vX, float vY){
        super();
        velocityX = vX;
        velocityY = vY;
    }

    public float getVX(){
        return velocityX;
    }

    public float getVY(){
        return velocityY;
    }

    /*
     * Returns an array of the velocity values of this Flick Action
     *   0: velocityX
     *   1: velocityY
     */
    public float[] getVelocity(){
        float[] ret = {velocityX, velocityY};
        return ret;
    }
}
