package com.se339.pixel_hockey.screens;

/**
 * Created by Zach on 11/30/2016.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.se339.fileUtilities.Directory;
import com.se339.fileUtilities.DirectoryList;
import com.se339.fileUtilities.FileList;
import com.se339.log.*;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.input.InputHandler;
import com.se339.pixel_hockey.sprites.Puck;
import com.se339.pixel_hockey.sprites.Sprites;
import com.se339.pixel_hockey.sprites.Stick;
import com.se339.pixel_hockey.sounds.SoundHandler;
import com.se339.pixel_hockey.world.ContactBits;
import com.se339.pixel_hockey.world.WorldContactListener;
import com.se339.pixel_hockey.world.WorldCreator;

import java.io.File;
import java.util.ArrayList;

public class GameScreen extends Screens {

    //Reference to our Game, used to set Screens
    private PixelHockeyGame game;
    public static boolean alreadyDestroyed = false;

    //basic playscreen variables
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    private WorldCreator creator;
    private float ppm;

    //sprites
    private ArrayList<Sprites> sprites;
    private Stick player;
    private Puck puck;

    private Music music;

//    private Array<Item> items;
//    private LinkedBlockingQueue<ItemDef> itemsToSpawn;


    public GameScreen(PixelHockeyGame game){
        super(game);

        log = new Log("GameScreen");

        this.game = game;
        ppm = 100;

        //create cam used to follow mario through cam world
        gamecam = new OrthographicCamera();

        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(PixelHockeyGame.getWidth() / ppm, PixelHockeyGame.getHeight() / ppm, gamecam);
        //log.g(PixelHockeyGame.getWidth() / ppm, PixelHockeyGame.getHeight() / ppm, "GamePort Width", "GamePort Height", "Setting GamePort Dimensions");

        //initially set our gamcam to be centered correctly at the start of of map
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        //log.g(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, "GameCam X", "GameCam Y", "Setting Gamecam Position");

        //create our Box2D world, setting no gravity in X, -10 gravity in Y, and allow bodies to sleep
        world = new World(new Vector2(0, 0), false);
        //allows for debug lines of our box2d world.
        b2dr = new Box2DDebugRenderer();
        creator = new WorldCreator(this);

        //create mario in our game world
        player = new Stick(this, ContactBits.PLAYER1, FileList.image_stick_blue);
        puck = new Puck(this);

        sprites = new ArrayList<Sprites>();
        sprites.add(player);
        sprites.add(puck);

        world.setContactListener(new WorldContactListener());

        Gdx.input.setInputProcessor(new InputHandler(this));


//        music = MarioBros.manager.get("audio/music/mario_music.ogg", Music.class);
//        music.setLooping(true);
//        music.setVolume(0.3f);
        //music.play();

//        items = new Array<Item>();
//        itemsToSpawn = new LinkedBlockingQueue<ItemDef>();
    }

//    public void spawnItem(ItemDef idef){
//        itemsToSpawn.add(idef);
//    }


//    public void handleSpawningItems(){
//        if(!itemsToSpawn.isEmpty()){
//            ItemDef idef = itemsToSpawn.poll();
//            if(idef.type == Mushroom.class){
//                items.add(new Mushroom(this, idef.position.x, idef.position.y));
//            }
//        }
//    }

    public Stick getPlayer(){
        return player;
    }
    public Puck getPuck() { return puck; }
    public float[] getPuckPosition() {
        float xy[] = {0,0};
        xy[0] =  puck.body.getPosition().x;
        xy[1] =  puck.body.getPosition().y;
        return xy;
    }

    @Override
    public void show() {


    }

//    public void handleInput(float dt){
//        //control our player using immediate impulses
//        if(player.currentState != Mario.State.DEAD) {
//            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
//                player.jump();
//            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
//                player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
//            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
//                player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
//            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
//                player.fire();
//        }
//
//    }

    public void update(float dt){
        //handle user input first
        //handleInput(dt);
        //handleSpawningItems();

        //takes 1 step in the physics simulation(60 times per second)
        //world.step(1 / 120f, 6, 2);
        world.step(dt, 6, 2);
        player.update(dt);
        puck.update(dt);
//        for(Enemy enemy : creator.getEnemies()) {
//            enemy.update(dt);
//            if(enemy.getX() < player.getX() + 224 / MarioBros.PPM) {
//                enemy.b2body.setActive(true);
//            }
//        }

//        for(Item item : items)
//            item.update(dt);
//
//        hud.update(dt);

        //update our gamecam with correct coordinates after changes
        gamecam.update();
    }


    @Override
    public void render(float delta) {
        //separate our update logic from render
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //renderer our Box2DDebugLines
        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        for (Sprites s : sprites)
            s.draw(game.batch);
//        player.draw(game.batch);
//        puck.draw(game.batch);
//        for (Enemy enemy : creator.getEnemies())
//            enemy.draw(game.batch);
//        for (Item item : items)
//            item.draw(game.batch);
        game.batch.end();

        //Set our batch to now draw what the Hud camera sees.
        //game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
//        hud.stage.draw();

        if(gameOver()){
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

    }

    public boolean gameOver(){
//        if(player.currentState == Mario.State.DEAD && player.getStateTimer() > 3){
//            return true;
//        }
        return false;
    }

    @Override
    public void resize(int width, int height) {
        //updated our game viewport
        gamePort.update(width,height);

    }

    public World getWorld(){
        return world;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //dispose of all our opened resources
//        map.dispose();
//        renderer.dispose();
        world.dispose();
        b2dr.dispose();
//        hud.dispose();
    }

    public float getPPM(){
        return ppm;
    }

    public ArrayList<Sprites> getSprites(){
        return sprites;
    }

}