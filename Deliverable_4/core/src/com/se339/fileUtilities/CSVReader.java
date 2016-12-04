package com.se339.fileUtilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.badlogic.gdx.graphics.g2d.ParticleEmitter.SpawnShape.line;

/**
 * Created by Zach on 12/3/2016.
 */

public class CSVReader {

    private String fileName;
    private ArrayList<String[]> friends;
    private static String line = "";
    private static String split = ",";

    public ArrayList<String> getFriends(){
        ArrayList<String> arr = new ArrayList<String>();
        Preferences pref = Gdx.app.getPreferences("Friend Info");
        int i = 0;
        boolean loop = true;
        while(loop){
            String temp = pref.getString("player"+i);
            if(temp != null){
                int win = pref.getInteger("win"+i);
                int lose = pref.getInteger("lose"+i);
                temp = temp+"\t"+win+"\t"+lose;
                i++;
                arr.add(temp);
            }
        }
        return arr;
    }

    public void init(){
        Preferences pref = Gdx.app.getPreferences("Friend Info");
        int i = 30;
        while(--i >= 0){
            pref.putString("player"+i, "ztwild");
            pref.putInteger("win", 4);
            pref.putInteger("lose", 3);
        }
        pref.flush();
    }


    public void shift(){
        Preferences pref = Gdx.app.getPreferences("Friend Info");
        for(int i = 0; i < 29; i++){
            String name = pref.getString("player"+(i+1));
            pref.putString("player"+i, name);
            int win = pref.getInteger("win"+(i+1));
            pref.putInteger("win"+i, win);
            int lose = pref.getInteger("lose"+(i+1));
            pref.putInteger("lose"+i, win);

        }
    }
}
