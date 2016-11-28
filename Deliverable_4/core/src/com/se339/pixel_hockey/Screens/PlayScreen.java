package com.se339.pixel_hockey.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.se339.pixel_hockey.Camera.PlayCamera;
import com.se339.pixel_hockey.Elements.Puck;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.Scenes.Hud;
import com.se339.pixel_hockey.Elements.Powerups.Powerup;
import com.se339.pixel_hockey.Elements.Powerups.PowerupDef;
import com.se339.pixel_hockey.Elements.Powerups.Speedup;
import com.se339.pixel_hockey.Stages.PlayStage;
import com.se339.pixel_hockey.Tools.B2WorldCreator;
import com.se339.pixel_hockey.Tools.WorldContactListener;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by se339.pixel_hockey on 8/14/15.
 */
public class PlayScreen extends ScreenParent {

    private TextureAtlas atlas;
    public static boolean alreadyDestroyed = false;

    //basic playscreen variables
    private PlayCamera playCam;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;

    //sprites
    private Puck player;

    private Music music;

    private Array<Powerup> powerups;
    private LinkedBlockingQueue<PowerupDef> powerupsToSpawn;


    public PlayScreen(PixelHockeyGame game){
        super(game);
        this.stage = new PlayStage();

        atlas = new TextureAtlas("Mario_and_Enemies.pack");

        //create cam used to follow mario through cam world
        playCam = new PlayCamera();

        //create our game HUD for scores/timers/level info
        hud = new Hud(game.batch);

        //Load our map and setup our map renderer
        maploader = new TmxMapLoader();
        map = maploader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1  / PixelHockeyGame.PPM);

        //initially set our gamcam to be centered correctly at the start of of map
        playCam.getCamera().position.set(playCam.getViewport().getWorldWidth() / 2, playCam.getViewport().getWorldHeight() / 2, 0);

        //create our Box2D world, setting no gravity in X, -10 gravity in Y, and allow bodies to sleep
        world = new World(new Vector2(0, -10), true);
        //allows for debug lines of our box2d world.
        b2dr = new Box2DDebugRenderer();

        creator = new B2WorldCreator(this);

        //create mario in our game world
        player = new Puck(this);

        world.setContactListener(new WorldContactListener());

        music = PixelHockeyGame.manager.get("audio/music/mario_music.ogg", Music.class);
        music.setLooping(true);
        music.setVolume(0.3f);
        //music.play();

        powerups = new Array<Powerup>();
        powerupsToSpawn = new LinkedBlockingQueue<PowerupDef>();

    }

    public void spawnPowerups(PowerupDef idef){
        powerupsToSpawn.add(idef);
    }


    public void handleSpawningPowerups(){
        if(!powerupsToSpawn.isEmpty()){
            PowerupDef idef = powerupsToSpawn.poll();
            if(idef.type == Speedup.class){
                powerups.add(new Speedup(this, idef.position.x, idef.position.y));
            }
        }
    }


    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {


    }

    public void handleInput(float dt){
        //control our player using immediate impulses
        if(player.currentState != Puck.State.DEAD) {


            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                player.jump();
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
                player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
                player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }

        //processLastAction();
    }

    public void update(float dt){
        //handle user input first
        handleInput(dt);
        handleSpawningPowerups();

        //takes 1 step in the physics simulation(60 times per second)
        //world.step(1 / 60f, 6, 2);

        player.update(dt);

        for(Powerup powerup : powerups)
            powerup.update(dt);

        hud.update(dt);

        //tell our renderer to draw only what our camera can see in our game world.
        renderer.setView(playCam.getCamera());
    }


    @Override
    public void render(float delta) {
        //separate our update logic from render
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        //renderer our Box2DDebugLines
        b2dr.render(world, playCam.getCamera().combined);

        game.batch.setProjectionMatrix(playCam.getCamera().combined);
        game.batch.begin();
        player.draw(game.batch);
        for (Powerup powerup : creator.getEnemies())
            powerup.draw(game.batch);
        for (Powerup powerup : powerups)
            powerup.draw(game.batch);
        game.batch.end();

        //Set our batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(gameOver()){
            game.setScreen(new GameOverScreen(game));
            dispose();
        }

    }

    public boolean gameOver(){
        if(player.currentState == Puck.State.DEAD && player.getStateTimer() > 3){
            return true;
        }
        return false;
    }

    @Override
    public void resize(int width, int height) {
        //updated our game viewport
        playCam.getViewport().update(width,height);

    }

    public TiledMap getMap(){
        return map;
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

    public Hud getHud(){ return hud; }
}
