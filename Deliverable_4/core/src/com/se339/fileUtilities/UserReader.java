package com.se339.fileUtilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.se339.log.Log;

/**
 * Created by Zach on 12/4/2016.
 */

public class UserReader {
    Log log = new Log("Starting txt reader");

    public void writeName(String name){
        Preferences pref = Gdx.app.getPreferences("User Info");
        pref.putString("name", name);
        pref.flush();
    }

    public String readName(){
        Preferences pref = Gdx.app.getPreferences("User Info");
        String name = pref.getString("name");
        return name;
    }

    public int getWins(){
        Preferences pref = Gdx.app.getPreferences("User Info");
        int wins = pref.getInteger("wins");
        return wins;
    }

    public int getLosses(){
        Preferences pref = Gdx.app.getPreferences("User Info");
        int losses = pref.getInteger("losses");
        return losses;
    }

    //Can be uncompleted games such as a timed out game or disconnect
    public int getTotalGames(){
        Preferences pref = Gdx.app.getPreferences("User Info");
        int games = pref.getInteger("games");
        return games;
    }
}
