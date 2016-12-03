package com.se339.pixel_hockey.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.se339.log.Log;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.elements.Puck;
import com.se339.fileUtilities.FileList;
import com.se339.pixel_hockey.elements.Wall;

public class GameWorld implements InputProcessor {

    Log log;

    World world;

    Puck puck;
    Wall wall;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;

    float torque = 0.0f;
    boolean drawSprite = true;

    public final static float PIXELS_TO_METERS = 100f;

    private float elapsed = 0;

    public GameWorld() {

        log = new Log("GameWorld");

        world = new World(new Vector2(0, 0f),true);
        puck = new Puck(world);
        wall = new Wall(world);

        Gdx.input.setInputProcessor(this);

        debugRenderer = new Box2DDebugRenderer();

    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        camera.update();
        // Step the physics simulation forward at a rate of 60hz
        world.step(1f/60f, 6, 2);

        puck.setPosition();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS,
                PIXELS_TO_METERS, 0);
        batch.begin();
        puck.drawPuck(batch);
        batch.end();

        debugRenderer.render(world, debugMatrix);
    }

    public void dispose() {
        puck.dispose();
        world.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {


        if(keycode == Input.Keys.RIGHT)
            puck.body.setLinearVelocity(1f, 0f);
        if(keycode == Input.Keys.LEFT)
            puck.body.setLinearVelocity(-1f,0f);

        if(keycode == Input.Keys.UP)
            puck.body.applyForceToCenter(0f,10f,true);
        if(keycode == Input.Keys.DOWN)
            puck.body.applyForceToCenter(0f, -10f, true);

        // On brackets ( [ ] ) apply torque, either clock or counterclockwise
        if(keycode == Input.Keys.RIGHT_BRACKET)
            torque += 0.1f;
        if(keycode == Input.Keys.LEFT_BRACKET)
            torque -= 0.1f;

        // Remove the torque using backslash /
        if(keycode == Input.Keys.BACKSLASH)
            torque = 0.0f;

        // If user hits spacebar, reset everything back to normal
        if(keycode == Input.Keys.SPACE|| keycode == Input.Keys.NUM_2) {
            puck.body.setLinearVelocity(0f, 0f);
            puck.body.setAngularVelocity(0f);
            torque = 0f;
            puck.setPosition(0f,0f);
            puck.body.setTransform(0f,0f,0f);
        }

        if(keycode == Input.Keys.COMMA) {
            puck.body.getFixtureList().first().setRestitution(puck.body.getFixtureList().first().getRestitution()-0.1f);
        }
        if(keycode == Input.Keys.PERIOD) {
            puck.body.getFixtureList().first().setRestitution(puck.body.getFixtureList().first().getRestitution()+0.1f);
        }
        if(keycode == Input.Keys.ESCAPE || keycode == Input.Keys.NUM_1)
            drawSprite = !drawSprite;

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
        puck.body.applyForce(1f,1f,screenX,screenY,true);

        log.l("Screen TouchDown:\n\t\tx-coord: " + screenX + "\n\t\ty-coord: " + screenY);

        //puck.body.applyTorque(0.4f,true);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}