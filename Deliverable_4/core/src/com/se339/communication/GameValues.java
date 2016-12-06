package com.se339.communication;

import com.badlogic.gdx.math.Vector2;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.world.ContactBits;

/**
 *
 * Class to forward information to the websocket
 *
 * Created by Michael Weems on 12/5/2016.
 */
public class GameValues {

    // player information
    private String username;
    private String opponentname;

    private int userscore;
    private int opponentscore;

    // puck information
    private Vector2 puckVelocity;
    private float xPos;
    private float yPos;

    /*
     * Construct a Gamevalues Object
     */
    public GameValues(String p1name, String p2name, float x, float y){
        username = p1name;
        opponentname = p2name;

        userscore = 0;
        opponentscore = 0;

        puckVelocity = new Vector2(0f, 0f);
        xPos = x;
        yPos = y;
    }

    /*
     * Updates the velocity and position
     */
    public void update(Vector2 v, float x, float y){
        updateVelocity(v);
        updatePosition(x, y);
    }

    /*
     * Update the velocity of the puck
     */
    public void updateVelocity(Vector2 v){
        puckVelocity = v;
    }

    /*
     * Update the x and y positions
     */
    public void updatePosition(float x, float y){
        xPos = x;
        yPos = y;
    }

    /*
     * Increment the score of a player
     */
    public void updateScore(PixelHockeyGame game){
        userscore++;
        game.getSocket().sendGoal();
    }

    /*
     * Get the puck position
     */
    public float[] getPosition(){
        float arr[] = {xPos, yPos};
        return arr;
    }

    /*
     * Get the puck velocity
     */
    public Vector2 getPuckVelocity(){
        return puckVelocity;
    }

    /*
     * Get the current score
     */
    public int[] getScore(){
        int score[] = {userscore, opponentscore};
        return score;
    }

    /*
     * Get player1name
     */
    public String getPlayer1name(){
        return username;
    }

    /*
     * Get player2name
     */
    public String getPlayer2name() {
        return opponentname;
    }
}
