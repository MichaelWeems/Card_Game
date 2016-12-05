package com.se339.pixel_hockey.input;

import com.badlogic.gdx.InputProcessor;
import com.se339.log.Log;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.screens.GameScreen;
import com.se339.pixel_hockey.sprites.Sprites;
import com.badlogic.gdx.Input;

/**
 * Created by mweem_000 on 12/4/2016.
 */

public class InputHandler implements InputProcessor {

    private GameScreen screen;

    private Log log;

    public InputHandler(GameScreen screen){
        this.screen = screen;
        log = new Log("Input");
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {


//        if(keycode == Input.Keys.RIGHT)
//            body.setLinearVelocity(1f, 0f);
//        if(keycode == Input.Keys.LEFT)
//            body.setLinearVelocity(-1f,0f);
//
//        if(keycode == Input.Keys.UP)
//            body.applyForceToCenter(0f,10f,true);
//        if(keycode == Input.Keys.DOWN)
//            body.applyForceToCenter(0f, -10f, true);
//
//        // On brackets ( [ ] ) apply torque, either clock or counterclockwise
//        if(keycode == Input.Keys.RIGHT_BRACKET)
//            torque += 0.1f;
//        if(keycode == Input.Keys.LEFT_BRACKET)
//            torque -= 0.1f;
//
//        // Remove the torque using backslash /
//        if(keycode == Input.Keys.BACKSLASH)
//            torque = 0.0f;

        // If user hits spacebar, reset everything back to normal
        if(keycode == Input.Keys.SPACE) {

            int i = 0;
            float x = (PixelHockeyGame.getWidth() / 2) / screen.getPPM();
            float y = (PixelHockeyGame.getHeight() / 2) / screen.getPPM();
            float z = (PixelHockeyGame.getHeight() / 4) / screen.getPPM();
            for (Sprites s : screen.getSprites()) {
                s.body.setLinearVelocity(0f, 0f);

                if (i == 1) s.getSprite().setPosition(x, y);
                else s.getSprite().setPosition(x,z);

                s.body.setTransform(0f, 0f, 0f);
                i++;
            }
        }

//        if(keycode == Input.Keys.COMMA) {
//            body.getFixtureList().first().setRestitution(body.getFixtureList().first().getRestitution()-0.1f);
//        }
//        if(keycode == Input.Keys.PERIOD) {
//            body.getFixtureList().first().setRestitution(body.getFixtureList().first().getRestitution()+0.1f);
//        }
//        if(keycode == Input.Keys.ESCAPE || keycode == Input.Keys.NUM_1)
//            drawSprite = !drawSprite;

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    // On touch we apply force from the direction of the users touch.
    // This could result in the object "spinning"
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //log.g(screenX, screenY, "screenX", "screenY", "User Input - Touch Down");
        return doTouchDownAction(screenX, screenY);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //log.l("User Input - Touch Drag");
        //log.g(screenX, screenY, "screenX", "screenY", "User Input - Touch Dragged");
        return doTouchDownAction(screenX, screenY);
    }

    private boolean doTouchDownAction(int x, int y){

        float xy[] = convertToMeters(x,y);
        if (!acceptableInput(xy)) {
            //log.l("Input is not within player bounds");
            return false;
        }

        xy = checkForCollision(xy);
        xy = modifyForBounds(xy);

        //log.g(xy[0], xy[1], "modified x", "modified y", "User Input (now within bounds)");

        xy[1] = PixelHockeyGame.getHeight() / screen.getPPM() - xy[1];
        //log.g(xy[0], xy[1], "x", "y", "Inverted Y");

        screen.getPlayer().move(xy);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private float[] modifyForBounds(float xy[]){
        float mod[] = xy;
        float size = screen.getPlayer().getSize();

        if (mod[0] < size )
            mod[0] = size;
        else if (mod[0] > PixelHockeyGame.getWidth() / screen.getPPM() - size)
            mod[0] = PixelHockeyGame.getWidth() / screen.getPPM() - size;

        if (mod[1] < size)
            mod[1] = size;
        else if (mod[1] > PixelHockeyGame.getHeight() / screen.getPPM() - size)
            mod[1] = PixelHockeyGame.getHeight() / screen.getPPM() - size;

        return mod;
    }

    private float[] checkForCollision(float xy[]) {
        float pos[] = screen.getPuckPosition();
        float radii = screen.getPlayer().getSize() + screen.getPuck().getSize();
        boolean left = false;
        boolean right = false;
        boolean up = false;
        boolean down = false;

        double x2 = Math.pow(Math.abs(pos[0] - xy[0]),2);
        double y2 = Math.pow(Math.abs(pos[1] - xy[1]),2);

        if (Math.sqrt(x2 + y2) < radii) {
//            log.g(radii, Math.sqrt(x2 + y2), "radii", "distance", "Collision");
//
//            log.g(pos[0], xy[0], "Puck x", "Stick x", "");
//            log.g(pos[1], xy[1], "Puck y", "Stick y", "");

            //log.l("Puck-Stick Collision");
            if (pos[0] >= xy[0]) {
                right = true;
                xy[0] -= screen.getPuck().getSize() / 4;
            } else {
                left = true;
                xy[0] += screen.getPuck().getSize() / 4;
            }

            if (pos[1] >= xy[1]) {
                up = true;
                xy[1] -= screen.getPuck().getSize() / 4;
            } else {
                down = true;
                xy[1] += screen.getPuck().getSize() / 4;
            }

            float xVel = 0f;
            float yVel = 0f;

            if (left || right)
                xVel = 5f;
            if (left)
                xVel *= -1f;
            if (down || up)
                yVel = 5f;
            if (down)
                yVel *= -1f;

            log.g(xVel, yVel, "xVel", "yVel", "Applying velocity to puck");
            log.l("Puck Collision");
//            log.d();
//            log.d();
            screen.getPuck().setVelocity(xVel, yVel);
        }

        return xy;
    }

    /*
     * Define the acceptable range of input for the player
     */
    private boolean acceptableInput(float xy[]){
        if (xy[1] > PixelHockeyGame.getHeight() / 2)
            return true;
        //return false;
        return true;
    }

    private float[] convertToMeters(int x, int y){
        float m[] = {x / screen.getPPM(), y / screen.getPPM()};
        return m;
    }
}
