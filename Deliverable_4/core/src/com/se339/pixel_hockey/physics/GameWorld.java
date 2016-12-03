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

    Sprite sprite;
    Texture img;

    World world;

    Puck puck;
    Wall wall;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    BitmapFont font;

    float torque = 0.0f;
    boolean drawSprite = true;

    final float PIXELS_TO_METERS = 100f;

    private float elapsed = 0;

    public GameWorld() {

        log = new Log("GameWorld");

        img = new Texture(FileList.image_puck_blue);

        sprite = new Sprite(img);
        sprite.setPosition(PixelHockeyGame.pWidth / 2, PixelHockeyGame.pHeight / 2);

        world = new World(new Vector2(0, 0f),true);
        puck = new Puck(world, sprite, PIXELS_TO_METERS);
        wall = new Wall(world, sprite, PIXELS_TO_METERS);

        Gdx.input.setInputProcessor(this);

        debugRenderer = new Box2DDebugRenderer();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        camera.update();
        // Step the physics simulation forward at a rate of 60hz
        world.step(1f/60f, 6, 2);

        puck.body.applyTorque(torque,true);

        sprite.setPosition((puck.body.getPosition().x * PIXELS_TO_METERS) - sprite.
                        getWidth()/2 ,
                (puck.body.getPosition().y * PIXELS_TO_METERS) -sprite.getHeight()/2 );
        sprite.setRotation((float)Math.toDegrees(puck.body.getAngle()));

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS,
                PIXELS_TO_METERS, 0);
        batch.begin();

        if(drawSprite)
            batch.draw(sprite, sprite.getX(), sprite.getY(),sprite.getOriginX(),
                    sprite.getOriginY(),
                    sprite.getWidth(),sprite.getHeight(),sprite.getScaleX(),sprite.
                            getScaleY(),sprite.getRotation());

        font.draw(batch,
                "Restitution: " + puck.body.getFixtureList().first().getRestitution(),
                -Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()/2 );
        batch.end();

        debugRenderer.render(world, debugMatrix);
    }

    public void dispose() {
        img.dispose();
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
            sprite.setPosition(0f,0f);
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