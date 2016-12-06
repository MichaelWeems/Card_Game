package com.se339.Client;

import com.badlogic.gdx.math.Vector2;
import com.se339.pixel_hockey.world.ContactBits;

/**
 * Created by mweem_000 on 12/5/2016.
 */

public class GameValues {

    // player information
    private String player1name;
    private String player2name;

    private int player1score;
    private int player2score;

    // puck information
    private Vector2 puckVelocity;
    private float xPos;
    private float yPos;



    public GameValues(String p1name, String p2name, float x, float y){
        player1name = p1name;
        player2name = p2name;

        player1score = 0;
        player2score = 0;

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
    public void updateScore(short player){
        if (player == ContactBits.PLAYER1)
            player1score++;
        else
            player2score++;
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
        int score[] = {player1score, player2score};
        return score;
    }

    /*
     * Get player1name
     */
    public String getPlayer1name(){
        return player1name;
    }

    /*
     * Get player2name
     */
    public String getPlayer2name() {
        return player2name;
    }
}
