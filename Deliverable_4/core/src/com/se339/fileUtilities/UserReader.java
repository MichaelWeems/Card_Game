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
}
