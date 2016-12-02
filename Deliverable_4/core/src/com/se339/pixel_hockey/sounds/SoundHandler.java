package com.se339.pixel_hockey.sounds;

import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;


/**
 * Created by mweem_000 on 12/1/2016.
 */

public class SoundHandler {

    private final String mDir = "music/";
    private ArrayList<String> musiclist = new ArrayList<String>();
    private final String music_firework = mDir + "Firework.wav";
    private final String music_harambe = mDir + "Harambe.mp3";

    private int music_index = 0;

    private Music music;

    public SoundHandler(){
        //music = new Music();
    }


}
