package com.se339.pixel_hockey.Camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.se339.pixel_hockey.PixelHockeyGame;

/**
 * Created by mweem_000 on 11/26/2016.
 */

public class CameraParent {

    private OrthographicCamera camera;
    private Viewport viewport;

    public CameraParent(){
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(PixelHockeyGame.V_WIDTH / PixelHockeyGame.PPM, PixelHockeyGame.V_HEIGHT / PixelHockeyGame.PPM, camera);
    }

    public OrthographicCamera getCamera(){
        return camera;
    }

    public Viewport getViewport(){
        return viewport;
    }

}
